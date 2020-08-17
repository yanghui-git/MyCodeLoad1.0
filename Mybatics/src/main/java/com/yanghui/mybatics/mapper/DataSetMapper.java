package com.yanghui.mybatics.mapper;


import com.yanghui.mybatics.dao.DataSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataSetMapper {

    int getById(@Param("tenantId") String tenantId);

    List<DataSet> listAll(@Param("tenantId") String tenantId);
}
