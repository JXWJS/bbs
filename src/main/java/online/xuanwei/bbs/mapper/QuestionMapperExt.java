package online.xuanwei.bbs.mapper;

import online.xuanwei.bbs.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapperExt {
    int incView(Question record);

    int updateContentByPrimaryKey(Question record);

    int incCommentCount(Question question);
}
