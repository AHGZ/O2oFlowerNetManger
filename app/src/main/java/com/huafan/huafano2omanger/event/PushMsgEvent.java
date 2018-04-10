package com.huafan.huafano2omanger.event;

/**
 * author: zhangkun
 * created on: 2017/12/27 下午2:15
 * description:
 */
public class PushMsgEvent {
    private final String description;
    String title;
    String content;

    public PushMsgEvent( String title, String content,String description) {
        this.title = title;
        this.content = content;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
