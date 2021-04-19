package online.xuanwei.bbs.util;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private int value;

    public int getValue(){
        return value;
    }

    CommentTypeEnum(int i) {
        this.value = i;
    }
}
