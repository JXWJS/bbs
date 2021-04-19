package online.xuanwei.bbs.controller;

import online.xuanwei.bbs.dto.CommentDTO;
import online.xuanwei.bbs.dto.QuestionDTO;
import online.xuanwei.bbs.service.CommentService;
import online.xuanwei.bbs.service.QuestionService;
import online.xuanwei.bbs.util.CommentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private CommentService commentService;

    @Autowired
    QuestionService questionService;

    @GetMapping("/question")
    public String questions(@RequestParam(name = "id") Integer id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> commentDTOList = commentService.listByTargetId(id, CommentTypeEnum.QUESTION.getValue());
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentDTOList);
        return "question";

    }
}
