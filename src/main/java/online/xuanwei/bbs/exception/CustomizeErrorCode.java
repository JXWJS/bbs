package online.xuanwei.bbs.exception;

/**
 * lxw
 */

public enum CustomizeErrorCode implements  ICustomizeErrorCode{

QUESTION_NOT_FOUND("找不到文章Id！"),
    PAGE_NOT_FOUND("找不到该页面，请重新输入"),
    COMMENT_FAIL("评论失败"),
    USERID_NOT_FOUND("找不到用户ID"),
    SEARCH_NOT_FOUND("搜便了，找不到该内容!")
    ;

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
