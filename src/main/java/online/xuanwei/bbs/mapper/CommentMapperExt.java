package online.xuanwei.bbs.mapper;

import online.xuanwei.bbs.model.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapperExt {
    void incCommentCount(Comment comment);
}
