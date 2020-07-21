package com.wl.mapper;

import com.wl.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeMapper {

    int saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> getTypeList();

    int updateType(@Param("newType") Type type);

    int deleteType(Long id);


}
