package com.itaem.memodemo.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itaem.memodemo.R;
import com.itaem.memodemo.adapter.DiffDemoCallback;
import com.itaem.memodemo.adapter.HomePageAdapter;
import com.itaem.memodemo.data.Constant;
import com.itaem.memodemo.data.NoteEntity;
import com.itaem.memodemo.databinding.FragmentMainBinding;
import com.itaem.memodemo.databinding.FragmentSearchBinding;
import com.itaem.memodemo.viewModel.HomePageViewModel;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
    private FragmentSearchBinding binding;
    private HomePageViewModel viewModel;
    private HomePageAdapter adapter;
    private List<NoteEntity> list ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(HomePageViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false);
        initView();
        initTouch();
        return binding.getRoot();
    }



    private void initView() {
        binding.searchView.requestFocus();
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomePageAdapter(viewModel.findNoteList("").getValue());
    }
    private void initTouch() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.popBackStack();
            }
        });
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.findNoteList(newText).observe(getViewLifecycleOwner(), new Observer<List<NoteEntity>>() {
                    @Override
                    public void onChanged(List<NoteEntity> noteEntities) {
                        adapter = new HomePageAdapter(noteEntities);
                        binding.rv.setAdapter(adapter);
                    }
                });
                return false;
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                // 编辑——跳转笔记显示页面
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("note",list.get(position));
                        bundle.putString("Update", Constant.NOTE_UPDATE);
                        NavController controller = Navigation.findNavController(binding.rv);
                        // 传入动作，或者碎片id
                        controller.navigate(R.id.action_searchFragment_to_noteShowFragment,bundle);
                    }
                });
            }
        });
    }
}