package com.itaem.memodemo.adapter;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.itaem.memodemo.R;
import com.itaem.memodemo.data.NoteEntity;

import java.util.List;

public class HomePageAdapter extends BaseQuickAdapter<NoteEntity, BaseViewHolder> {
    public HomePageAdapter(@Nullable List<NoteEntity> data) {
        super(R.layout.item_home_page, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, NoteEntity item) {
        // 取笔记标题显示文字，有标题显示标题，无标题显示内容部分
        String title;
        if (!item.getNote_title().equals(""))
            title = item.getNote_title();
        else
            title = item.getNote_content();

        baseViewHolder.setText(R.id.item_content,title)
                .setText(R.id.item_time,item.getNote_time());
    }
/*    public HomePageAdapter(@Nullable List<NoteEntity> data) {
        super(R.layout.item_home_page, data);
        // 设置普通item布局（如果item类型只有一种，使用此方法）
        setNormalLayout(R.layout.item_home_page);
        // 注册需要点击的子view id
        //  addChildClickViewIds(R.id.more);
    }




    @Override
    protected void convertHeader(@NonNull BaseViewHolder baseViewHolder, @NonNull NoteEntity item) {
        String title;
        if (!item.getNote_title().equals(""))
            title = item.getNote_title();
        else
            title = item.getNote_content();
        if (item.isHeader()) {
            baseViewHolder.setText(R.id.item_content,title)
                    .setText(R.id.item_time,item.getNote_time());
        }
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, NoteEntity item) {
        // 取笔记标题显示文字，有标题显示标题，无标题显示内容部分
        String title;
        if (!item.getNote_title().equals(""))
            title = item.getNote_title();
        else
            title = item.getNote_content();
        baseViewHolder.setText(R.id.item_content,title)
                .setText(R.id.item_time,item.getNote_time());
    }*/
}
