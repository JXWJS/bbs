package online.xuanwei.bbs.controller;

import online.xuanwei.bbs.dto.CommentCreateDTO;
import online.xuanwei.bbs.exception.CustomizeErrorCode;
import online.xuanwei.bbs.exception.CustomizeException;
import online.xuanwei.bbs.model.Comment;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.service.CommentService;
import online.xuanwei.bbs.util.CommentTypeEnum;
import online.xuanwei.bbs.util.Result;
import online.xuanwei.bbs.util.ResultGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
           Comment comment = new Comment();
            BeanUtils.copyProperties(commentCreateDTO, comment);
        return commentService.insert(comment,user.getId());
    }else{
            throw  new CustomizeException(CustomizeErrorCode.USERID_NOT_FOUND);
        }
    }



    @ResponseBody
    @RequestMapping(value = "/comments",method = RequestMethod.GET)
    public Result comments(
            @RequestParam(name = "questionId") Long questionId,
                           @RequestParam(name = "commentId") Long commentId){
        return ResultGenerator.genSuccessResult(commentService.listByTargetId(questionId.intValue(), commentId.intValue()));
    }

}
