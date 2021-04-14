package online.xuanwei.bbs.mapper;

import online.xuanwei.bbs.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert({"insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})"})
    public void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    public List<Question> list(Integer offset, Integer size);

    @Select("select count(*) from question")
    public Integer getCount();

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    public List<Question> listByUserId(Integer userId,Integer offset, Integer size);

    @Select("select count(*) from question where creator = #{userId}")
    public Integer getMyCount(Integer userId);

    @Select("select * from question where id = #{id}")
    public Question getById(Integer id);

    @Update("update question set title = #{title},description = #{description},tag=#{tag},gmt_modified = #{gmtModified} where id = #{id}")
    void update(Question question);


}
