package com.itaem.memodemo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.itaem.memodemo.R;
import com.itaem.memodemo.data.NoteEntity;
import com.itaem.memodemo.databinding.FragmentNoteShowBinding;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(NoteShowViewModel.class);

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_note_show, container, false);
        initSetData();
        initTouch();
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
                navController.navigate(R.id.action_noteShowFragment_to_mainFragment);
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

    private void initSetData() {

    }

    /**
     * toolbar菜单监听
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CharSequence title = item.getTitle();
        if (title.equals("收藏")) {
        } else if (title.equals("添加")) {
            viewModel.insert(new NoteEntity(binding.editNoteTitle.getText().toString(),
                    binding.editNoteContent.getText().toString(),
                    String.valueOf(new Date())));
            Toast.makeText(getContext(), "添加成功", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
    //    MenuItem aboutMenuItem = menu.findItem(R.id.menu_collect);

    }

    /**
     * 注册菜单
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_note_show,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}