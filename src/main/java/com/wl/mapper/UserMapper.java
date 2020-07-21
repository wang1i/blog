package com.wl.mapper;

import com.wl.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User selectUserByNameAndPassword(String username, String password);

}
