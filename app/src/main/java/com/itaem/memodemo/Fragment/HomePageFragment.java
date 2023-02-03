package com.itaem.memodemo.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.itaem.memodemo.R;
import com.itaem.memodemo.adapter.DiffDemoCallback;
import com.itaem.memodemo.adapter.HomePageAdapter;
import com.itaem.memodemo.data.Constant;
import com.itaem.memodemo.data.NoteEntity;
import com.itaem.memodemo.databinding.FragmentHomePageBinding;
import com.itaem.memodemo.viewModel.HomePageViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 日期：2023.1.16
 * 页面：主页
 * 作者：chen
 */
public class HomePageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private  static HomePageFragment fragment = new HomePageFragment();
// public static HomePageFragment getInstance(){return fragment;}
    public HomePageFragment() {

        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    // viewModel
    private HomePageViewModel viewModel;
    // DataBinding
    private FragmentHomePageBinding binding;
    // adapter
    private HomePageAdapter adapter;
    private List<NoteEntity> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(HomePageViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page,container,false);
        // 设置观察者
        binding.setLifecycleOwner(this);
        // xml回绑数据
        binding.setHomePageData(viewModel);
     //   list = viewModel.getNoteList().getValue();
        initView();
        Log.d("TAG", "onCreateView: Home");
        initTouch();
        return binding.getRoot();
    }
    /**
     * 对View进行设置适配器和布局管理器等操作
     */
    private void initView() {
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomePageAdapter(list);
        adapter.setDiffCallback(new DiffDemoCallback());

        // 添加自动检测数据更新
        viewModel.getNoteList().observe(getViewLifecycleOwner(), new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                if (noteEntities!=null){
                    list.addAll(initIsTop(noteEntities));
                    // 当数据加载完毕在获取监听事件
                    adapter.setDiffNewData(list);
                    binding.rv.setAdapter(adapter);

                }
            }
        });
    }
    // 头部归位
    private List<NoteEntity> initIsTop(List<NoteEntity> noteEntities){
        if (list!=null){
            list.clear();
        }
        List<NoteEntity> head = new ArrayList<>();
        for (int i =0 ;i<noteEntities.size();i++){
            NoteEntity note = noteEntities.get(i);
            if (note.isHeader()){
                head.add(note);
                noteEntities.remove(note);
            }
        }
        for (int i =0 ;i<head.size();i++){
            noteEntities.add(i,head.get(i));
        }
        list = noteEntities;
        return noteEntities;
    }
    /**
     * 事件监听
     */
    private void initTouch() {
        // 新增——跳转笔记显示页面
        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                // 传入动作，或者碎片id
                controller.navigate(R.id.action_mainFragment_to_noteShowFragment);
            }
        });
        // 编辑——跳转笔记显示页面
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("note",list.get(position));
                bundle.putString("Update", Constant.NOTE_UPDATE);
                NavController controller = Navigation.findNavController(binding.rv);
                // 传入动作，或者碎片id
                controller.navigate(R.id.action_mainFragment_to_noteShowFragment,bundle);
            }
        });
        // 长按——1.删除，2.多选，3.收藏
        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                // 悬浮菜单
                if(!list.get(position).isHeader()){
                    PopupMenu menu = new PopupMenu(getActivity(),binding.rv.getChildAt(position));
                    menu.getMenuInflater().inflate(R.menu.menu_item_popup,menu.getMenu());
                    menu.show();
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getTitle().equals("删除")){
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("删除笔记")
                                        .setMessage("是否确定删除")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                NoteEntity noteEntity = list.get(position);
                                                viewModel.delete(noteEntity.getId(),noteEntity);
                                                // 更新
                                                //                  viewModel.getNoteList().getValue();
                                            }
                                        }).setNegativeButton("取消",null).show().create();
                            }else if (item.getTitle().equals("加密")){
                            }else if (item.getTitle().equals("置顶")){
                                NoteEntity noteEntity = list.get(position);
                                noteEntity.setHeader(true);
                                viewModel.update(noteEntity.getId(),noteEntity);
                            }
                            return true;
                        }
                    });
                }else {
                    PopupMenu menu = new PopupMenu(getActivity(),binding.rv.getChildAt(position));
                    menu.getMenuInflater().inflate(R.menu.menu_item_popup_istop,menu.getMenu());
                    menu.show();
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getTitle().equals("删除")){
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("删除笔记")
                                        .setMessage("是否确定删除")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                NoteEntity noteEntity = list.get(position);
                                                viewModel.delete(noteEntity.getId(),noteEntity);
                                            }
                                        }).setNegativeButton("取消",null).show().create();
                            }else if (item.getTitle().equals("加密")){
                            }else if (item.getTitle().equals("取消置顶")){
                                NoteEntity noteEntity = list.get(position);
                                noteEntity.setHeader(false);
                                viewModel.update(noteEntity.getId(),noteEntity);
                            }
                            return true;
                        }
                    });
                }
                return true;
            }
        });

    }
}