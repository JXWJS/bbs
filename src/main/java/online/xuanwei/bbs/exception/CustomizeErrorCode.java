package online.xuanwei.bbs.exception;

/**
 * lxw
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode{

QUESTION_NOT_FOUND("找不到文章Id！"),
    PAGE_NOT_FOUND("找不到该页面，请重新输入"),
    COMMENT_FAIL("评论失败"),
    USERID_NOT_FOUND("找不到用户ID"),
    ;

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
