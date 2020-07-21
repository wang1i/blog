package com.wl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    private Long id;
    @NotBlank
    private String name;

    private List<Blog> blogs;
}
