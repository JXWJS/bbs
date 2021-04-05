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

    public List<QuestionDTO> list() {
        List<Question> questionList = questionMapper.list();
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
}
