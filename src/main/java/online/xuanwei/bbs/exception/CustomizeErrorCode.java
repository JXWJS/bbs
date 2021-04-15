package online.xuanwei.bbs.exception;

/**
 * lxw
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode{

QUESTION_NOT_FOUND("找不到文章！"),
    PAGE_NOT_FOUND("找不到该页面，请重新输入");

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
