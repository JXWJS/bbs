package online.xuanwei.bbs.service;

import online.xuanwei.bbs.mapper.CategoryMapper;
import online.xuanwei.bbs.model.Category;
import online.xuanwei.bbs.model.CategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> getAll(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIsDeletedEqualTo(0);
        return categoryMapper.selectByExample(categoryExample);
    }

    public  List<Category> getById(Category category){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdEqualTo(category.getId());
        return null;
    }

}
