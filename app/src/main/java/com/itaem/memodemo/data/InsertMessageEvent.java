package com.itaem.memodemo.data;

// 传递信息
public class InsertMessageEvent {
    public final String message;

    public InsertMessageEvent(String message) {
        this.message = message;
    }
}
