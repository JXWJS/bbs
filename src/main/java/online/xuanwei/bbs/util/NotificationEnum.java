package online.xuanwei.bbs.util;

public enum NotificationEnum {
    REPLY_QUESTION(1,1,"回复了您的问题"),
    REPLY_COMMENTS(2,1,"回复了您的评论");

    private  int type;
    private int status;
    private String content;

    NotificationEnum(int type,int status, String content) {
        this.type = type;
        this.status = status;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
