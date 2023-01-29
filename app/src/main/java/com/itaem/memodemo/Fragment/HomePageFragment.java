package com.itaem.memodemo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.itaem.memodemo.R;
import com.itaem.memodemo.adapter.HomePageAdapter;
import com.itaem.memodemo.databinding.FragmentHomePageBinding;
import com.itaem.memodemo.viewModel.HomePageViewModel;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(HomePageViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page,container,false);
        // 设置观察者
        binding.setLifecycleOwner(this);
        // xml回绑数据
        binding.setHomePageData(viewModel);
        initView();
        return binding.getRoot();
    }

    /**
     * 对View进行设置适配器和布局管理器等操作
     */
    private void initView() {
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(new HomePageAdapter(viewModel.queryAll()));
    }
}