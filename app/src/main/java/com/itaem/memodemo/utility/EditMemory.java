package com.itaem.memodemo.utility;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.Stack;

// 编辑器记忆功能——仿装饰者模式
public class EditMemory {
    Stack<MemoryEditBean> lastStack = new Stack<>();
    Stack<MemoryEditBean> nextStack = new Stack<>();
    // 修改缓冲
    SpannableStringBuilder spannableStringBuilder;
    private final AppCompatEditText editText;
    public EditMemory(AppCompatEditText editText) {
        this.editText = editText;
        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            // start：开始 count：减少变化数 after：新增变化数
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 删除
                if (!(lastStack.isEmpty())&&count>0&&after==0){
                    lastStack.push(new MemoryEditBean(s.toString().substring(start,start+count),start,start+count,-1));
                }

            }
            @Override
            // start：开始位置 before：删除数量;count:新增数量
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 当前栈顶为-1，即为删除状态
                if ((lastStack.size()!=0&&before>0)){
                }else {
                    // 增加或者修改
                    lastStack.push(new MemoryEditBean(s.toString().substring(start,count+start),start,start+count,0));
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    // 撤销功能
    public void rollBack(){
        if (lastStack.size() == 0){
            return;
        }
        MemoryEditBean temp = lastStack.pop();
        spannableStringBuilder = new SpannableStringBuilder(editText.getText());
        nextStack.push(temp);
        if (temp.getState()==-1){
            // 删除状态
            spannableStringBuilder.insert(temp.getLastStart(),temp.getLastEdit());
            editText.setText(spannableStringBuilder);
            editText.setSelection(temp.getLastEnd());
        }else if (temp.getState() == 0){
            // 新增状态
            // 覆盖text的长度
            Log.d("TAG", "rollBack: "+editText.getText().length());
            spannableStringBuilder.delete(temp.getLastStart(),temp.getLastEnd());
            editText.setText(spannableStringBuilder);
            editText.setSelection(editText.getText().length());
        }
    }
    // 仿撤销功能
    public void rollNext(){
        if (nextStack.size() == 0){
            return;
        }
        MemoryEditBean pop = nextStack.pop();
        lastStack.push(pop);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(editText.getText());
        if (pop.getState()==-1){
            spannableStringBuilder.delete(pop.getLastStart(),pop.getLastEnd());
            editText.setText(spannableStringBuilder);
            editText.setSelection(pop.getLastStart());
        }else if (pop.getState()==0){
            spannableStringBuilder.insert(pop.getLastStart(),pop.getLastEdit());
            editText.setText(spannableStringBuilder);
            editText.setSelection(editText.getText().length());
        }
    }


    class MemoryEditBean{
        // 修改文本
        private String lastEdit;
        // 修改起始位置,从0数起
        private int lastStart;
        // 修改末位置
        private int lastEnd;
        private int state;

        public MemoryEditBean(String lastEdit, int lastStart, int lastEnd, int state) {
            this.lastEdit = lastEdit;
            this.lastStart = lastStart;
            this.lastEnd = lastEnd;
            this.state = state;
        }

        public String getLastEdit() {
            return lastEdit;
        }

        public int getLastStart() {
            return lastStart;
        }

        public int getLastEnd() {
            return lastEnd;
        }

        public int getState() {
            return state;
        }
    }
}
