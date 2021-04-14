package online.xuanwei.bbs.service;

import online.xuanwei.bbs.dto.QuestionDTO;
import online.xuanwei.bbs.mapper.QuestionMapper;
import online.xuanwei.bbs.mapper.UserMapper;
import online.xuanwei.bbs.model.Question;
import online.xuanwei.bbs.model.User;
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

    List<QuestionDTO> questionDTOList;

    public List<QuestionDTO> list(Integer page, Integer size) {
        Integer offset = size*(page-1);
        List<Question> questionList = questionMapper.list(offset,size);
        questionDTOList = new ArrayList<>();
        for (Question question:questionList
             ) {
            User user = userMapper.findByCreator(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public Integer getCount(){
       return questionMapper.getCount();
    }

    public Integer getMyCount( Integer userId){return  questionMapper.getMyCount(userId);}

    public List<QuestionDTO> getMYQuestionDTOList(Integer userId,Integer page, Integer size) {
        Integer offset = size*(page-1);
        List<Question> questionList = questionMapper.listByUserId(userId,offset,size);
        questionDTOList = new ArrayList<>();
        for (Question question:questionList
        ) {
            User user = userMapper.findByCreator(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public QuestionDTO getById(Integer id){
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.getById(id);
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(userMapper.findByCreator(question.getCreator()));
        return questionDTO;
    }
}