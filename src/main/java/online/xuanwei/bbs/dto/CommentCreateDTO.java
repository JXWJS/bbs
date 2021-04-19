package online.xuanwei.bbs.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long parentId; //questionId
    private String content;
    private Integer type; //判断二级评论


}
