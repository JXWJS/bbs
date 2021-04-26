package online.xuanwei.bbs.controller;

import online.xuanwei.bbs.dto.NotificationDTO;
import online.xuanwei.bbs.dto.PageDTO;
import online.xuanwei.bbs.dto.PaginationDTO;
import online.xuanwei.bbs.dto.QuestionDTO;
import online.xuanwei.bbs.exception.CustomizeErrorCode;
import online.xuanwei.bbs.exception.CustomizeException;
import online.xuanwei.bbs.mapper.UserMapper;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.service.CategoryService;
import online.xuanwei.bbs.service.NotificationService;
import online.xuanwei.bbs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PageDTO pageDTO;

    @GetMapping({"/","index"})
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "7") Integer size,
                        @RequestParam(name = "search",required = false) String search,
                        @RequestParam(name = "category",required = false) Integer category){
        System.out.println(page+"search"+search+" category"+category);
        if(search == null && category == null){
            pageDTO.setCategoryId(null);
            pageDTO.setSearch(null);
            PaginationDTO paginationDTO = questionService.list(pageDTO,page,size);
            paginationDTO.setPage(page);
            paginationDTO.setSize(size);
            paginationDTO.setPages(size,questionService.countORSearch(pageDTO)); // 一共多少页
            paginationDTO.setCategoryList(categoryService.getAll());  //获取一共多少分类
            paginationDTO.setPages(size, questionService.countORSearch(pageDTO)); //search一共多少页
            if (page>paginationDTO.getPages()){
                throw new CustomizeException(CustomizeErrorCode.PAGE_NOT_FOUND);
            }
            model.addAttribute("questions", paginationDTO);
        } else if(search != null){
            pageDTO.setSearch(search);
            pageDTO.setCategoryId(null);
            PaginationDTO paginationDTO = questionService.list(pageDTO,page,size);
            paginationDTO.setPage(page);
            paginationDTO.setSize(size);
            paginationDTO.setPages(size,questionService.countORSearch(pageDTO)); // 一共多少页
            paginationDTO.setCategoryList(categoryService.getAll());  //获取一共多少分类
            paginationDTO.setPages(size, questionService.countORSearch(pageDTO)); //search一共多少页

            if (page>paginationDTO.getPages()){
                throw new CustomizeException(CustomizeErrorCode.PAGE_NOT_FOUND);
            }
            model.addAttribute("questions", paginationDTO);
        }else if(category != null){
            pageDTO.setCategoryId(category);
            pageDTO.setSearch(null);
            PaginationDTO paginationDTO = questionService.list(pageDTO,page,size);
            paginationDTO.setPage(page);
            paginationDTO.setSize(size);
            paginationDTO.setPages(size,questionService.countORSearch(pageDTO)); // 一共多少页
            paginationDTO.setCategoryList(categoryService.getAll());  //获取一共多少分类
            paginationDTO.setPages(size, questionService.countORSearch(pageDTO)); //search一共多少页

            if (page>paginationDTO.getPages()){
                throw new CustomizeException(CustomizeErrorCode.PAGE_NOT_FOUND);
            }
            model.addAttribute("questions", paginationDTO);
        }
        model.addAttribute("pageDTO",pageDTO);



        User user = (User) request.getSession().getAttribute("user");
        if (user !=null) {
            List<NotificationDTO> notificationDTOList = notificationService.getNotifier(user);
            model.addAttribute("notification", notificationDTOList);
        }
        return "index";
    }
}
