package com.fosu.study.provider.mapper;

import com.fosu.study.provider.domain.UmsAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;


@Mapper
public interface UmsAdminMapper extends MyMapper<UmsAdmin> {
}
