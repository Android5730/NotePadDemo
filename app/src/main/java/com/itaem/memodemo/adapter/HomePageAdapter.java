package com.itaem.memodemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.itaem.memodemo.R;
import com.itaem.memodemo.data.NoteEntity;

import java.util.List;

public class HomePageAdapter extends BaseQuickAdapter<NoteEntity, BaseViewHolder> {
    public HomePageAdapter(List<NoteEntity> data) {
        super(R.layout.item_home_page, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, NoteEntity item) {
        // 取笔记标题显示文字，有标题显示标题，无标题显示内容部分
        String title;
        if (!item.getNote_title().equals(""))
            title = item.getNote_title();
        else
            title = item.getNote_content();
        helper.setText(R.id.item_content,title)
                .setText(R.id.item_time,item.getNote_time());
    }
}
