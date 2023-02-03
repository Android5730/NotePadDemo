package com.itaem.memodemo.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.itaem.memodemo.data.NoteEntity;

public class DiffDemoCallback extends DiffUtil.ItemCallback<NoteEntity> {

    /**
     * 判断是否是同一个item
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    @Override
    public boolean areItemsTheSame(@NonNull NoteEntity oldItem, @NonNull NoteEntity newItem) {
        return oldItem.getId() == newItem.getId();
    }

    /**
     * 当是同一个item时，再判断内容是否发生改变
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    @Override
    public boolean areContentsTheSame(@NonNull NoteEntity oldItem, @NonNull NoteEntity newItem) {
        return oldItem.getNote_title().equals(newItem.getNote_title())
                && oldItem.getNote_content().equals(newItem.getNote_content())
                && oldItem.getNote_time().equals(newItem.getNote_time())
                && oldItem.isHeader() == newItem.isHeader();
    }
    /**
     * 可选实现
     * 如果需要精确修改某一个view中的内容，请实现此方法。
     * 如果不实现此方法，或者返回null，将会直接刷新整个item。
     *
     * @param oldItem Old data
     * @param newItem New data
     * @return Payload info. if return null, the entire item will be refreshed.
     */
    @Override
    public Object getChangePayload(@NonNull NoteEntity oldItem, @NonNull NoteEntity newItem) {
        return null;
    }
}