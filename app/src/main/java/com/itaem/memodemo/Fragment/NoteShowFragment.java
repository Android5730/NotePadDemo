package com.itaem.memodemo.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.itaem.memodemo.R;
import com.itaem.memodemo.data.Constant;
import com.itaem.memodemo.data.NoteEntity;
import com.itaem.memodemo.databinding.FragmentNoteShowBinding;
import com.itaem.memodemo.utility.EditMemory;
import com.itaem.memodemo.viewModel.NoteShowViewModel;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteShowFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoteShowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteShowFragment newInstance(String param1, String param2) {
        NoteShowFragment fragment = new NoteShowFragment();
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
    private NoteShowViewModel viewModel;
    private FragmentNoteShowBinding binding;
    private EditMemory editMemory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(NoteShowViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_note_show, container, false);
        initSetData();
        initTouch();
        Log.d("TAG", "onCreateView: Show");
        return binding.getRoot();
    }

    /**
     * 监听事件
     */
    private void initTouch() {
        // 回退至首页
        binding.toolbarNoteShow.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(v);
                // 出栈，回退
                navController.popBackStack();
            }
        });

        // toolbar菜单监听
        binding.toolbarNoteShow.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                CharSequence title = item.getTitle();
                if (title.equals("收藏")) {
                    Toast.makeText(getContext(), "标记成功", Toast.LENGTH_LONG).show();
                } else if (title.equals("添加")) {
                    if (getArguments()!=null&&getArguments().get("Update").equals(Constant.NOTE_UPDATE)){
                        // 更新
                        viewModel.update(viewModel.getUpdateNote().getId(),new NoteEntity(binding.editNoteTitle.getText().toString()
                        ,binding.editNoteContent.getText().toString(),String.valueOf(new Date())));
                        Toast.makeText(getContext(), "更新成功", Toast.LENGTH_LONG).show();
                    }else {
                        // 添加
                        viewModel.insert(new NoteEntity(binding.editNoteTitle.getText().toString(),
                                binding.editNoteContent.getText().toString(),
                                String.valueOf(new Date())));
                        Toast.makeText(getContext(), "添加成功", Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            }
        });
/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (binding.editNoteContent.getFocusable()== View.FOCUSABLE){
                binding.toolbarNoteShow.getMenu().getItem(0).setVisible(true);
                binding.toolbarNoteShow.getMenu().getItem(1).setVisible(false);
            }else {
                binding.toolbarNoteShow.getMenu().getItem(0).setVisible(false);
                binding.toolbarNoteShow.getMenu().getItem(1).setVisible(true);
            }
        }*/
    }
    /**
     * 编辑功能：获取数据
     */
    private void initSetData() {
        editMemory = new EditMemory(binding.editNoteContent);
        if (getArguments()!=null){
            viewModel.setUpdateNote((NoteEntity) getArguments().getSerializable("note"));
            binding.editNoteTitle.setText(viewModel.getUpdateNote().getNote_title());
            binding.editNoteContent.setText(viewModel.getUpdateNote().getNote_content());
        }
    }


    /**
     * 动态更换menu
     * @param menu
     */
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
    //    MenuItem aboutMenuItem = menu.findItem(R.id.menu_collect);
    }
}