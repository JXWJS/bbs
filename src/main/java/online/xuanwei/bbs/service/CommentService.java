package online.xuanwei.bbs.service;

import online.xuanwei.bbs.dto.CommentDTO;
import online.xuanwei.bbs.exception.CustomizeErrorCode;
import online.xuanwei.bbs.exception.CustomizeException;
import online.xuanwei.bbs.mapper.CommentMapper;
import online.xuanwei.bbs.mapper.UserMapper;
import online.xuanwei.bbs.model.*;
import online.xuanwei.bbs.util.CommentTypeEnum;
import online.xuanwei.bbs.util.ResultGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public Object insert(Comment comment,Integer userId){
        comment.setCommentator(userId);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        Question question = new Question();
        question.setId(comment.getParentId().intValue());
        int temp = commentMapper.insert(comment);
        questionService.incCommentCount(question);
        if (temp != 1){
            throw  new CustomizeException(CustomizeErrorCode.COMMENT_FAIL);
        }else{
            return ResultGenerator.genSuccessResult(comment);
        }
    }

    public List<CommentDTO> listByTargetId(Integer id, Integer type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id.longValue()).andTypeEqualTo(type);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size() == 0){
        return new ArrayList<>();
}
        Set<Integer> commentators  = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> userList = userMapper.selectByExample(userExample);
        System.out.println(userList.size());
        Map<Integer,User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getId(),user ->user));
        System.out.println(userMap.get(2).getAccountId());
        List<CommentDTO> commentDTOS = comments.stream().map(comment ->
                {
                    CommentDTO commentDTO = new CommentDTO();
                    BeanUtils.copyProperties(comment,commentDTO);
                    commentDTO.setUser(userMap.get(comment.getCommentator()));
                    return commentDTO;
                }).collect(Collectors.toList());
        return  commentDTOS;
    }


}
