package com.sangeng.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper     //扫描时有用
@Repository
public interface CompanyMapper {
    int updateImgById(String businessLicenseUrl, Integer id);
}
