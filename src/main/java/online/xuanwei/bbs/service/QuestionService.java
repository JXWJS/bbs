package online.xuanwei.bbs.service;

import online.xuanwei.bbs.dto.QuestionDTO;
import online.xuanwei.bbs.mapper.QuestionMapper;
import online.xuanwei.bbs.mapper.QuestionMapperExt;
import online.xuanwei.bbs.mapper.UserMapper;
import online.xuanwei.bbs.model.Question;
import online.xuanwei.bbs.model.QuestionExample;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionMapperExt questionMapperExt;

    List<QuestionDTO> questionDTOList;

    public List<QuestionDTO> list(Integer page, Integer size) {
        Integer offset = size*(page-1);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        questionDTOList = new ArrayList<>();
        for (Question question:questionList
             ) {
            List<User> users = getByUserKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(users.get(0));
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public Integer getCount(){
       return (int)questionMapper.countByExample(new QuestionExample());
    }

    public Integer getMyCount( Integer userId){
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
                return  (int)questionMapper.countByExample(questionExample);}

    public List<QuestionDTO> getMYQuestionDTOList(Integer userId,Integer page, Integer size) {
        Integer offset = size*(page-1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        questionDTOList = new ArrayList<>();
        for (Question question:questionList
        ) {
            List<User> users = getByUserKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(users.get(0));
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public QuestionDTO getById(Integer id){
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(getByUserKey(question.getCreator()).get(0));
        return questionDTO;
    }

    public  List<User> getByUserKey(Integer id){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        return userMapper.selectByExample(userExample);
    }

    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        questionMapperExt.incView(question);

    }

    public  void incCommentCount(Question question){
        questionMapperExt.incCommentCount(question);
    }
}
