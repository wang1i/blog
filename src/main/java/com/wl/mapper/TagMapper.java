package com.wl.mapper;

import com.wl.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagMapper {

    int saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> getTagListByIds(List<Long> tagIds);

    List<Tag> getTagList();

    int updateTag(Tag tag);

    int deleteTag(Long id);

}
