package com.wl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.mapper.BlogMapper;
import com.wl.mapper.TypeMapper;
import com.wl.pojo.Blog;
import com.wl.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public List<Type> getTypeList() {
        List<Type> types = typeMapper.getTypeList();
        for (Type type : types) {
            List<Blog> blogs = blogMapper.getBlogListByTypeId(type.getId());
            type.setBlogs(blogs);
        }
        return types;
    }

    @Transactional
    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Transactional
    @Override
    public PageInfo<Type> listType(Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Type> typeList = typeMapper.getTypeList();
        return new PageInfo<>(typeList, 5);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteType(id);
    }
}
