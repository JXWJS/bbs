package online.xuanwei.bbs.dto;

import lombok.Data;
import online.xuanwei.bbs.model.User;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private Integer commentCount;
    private User user;

}
