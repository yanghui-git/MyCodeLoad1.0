package com.yanghui.mybatics.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {

    void deleteByName(@Param("name") String name);
}
