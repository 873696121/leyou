package huhong.leyou.item.service;

import huhong.leyou.item.mapper.CategoryMapper;
import huhong.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        List<Category> list = this.categoryMapper.select(record);
        return list;
    }
}
