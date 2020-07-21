package com.wl.service;

import com.github.pagehelper.PageInfo;
import com.wl.pojo.Type;

import java.util.List;


public interface TypeService {

    int saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> getTypeList();

    PageInfo<Type> listType(Integer pageNum);

    int updateType(Type type);

    int deleteType(Long id);

}
