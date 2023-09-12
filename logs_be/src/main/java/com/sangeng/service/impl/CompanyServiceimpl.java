package com.sangeng.service.impl;

import com.sangeng.mapper.CompanyMapper;
import com.sangeng.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class CompanyServiceimpl implements CompanyService{
 
    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public int updateImgById(String businessLicenseUrl, Integer id) {
        return companyMapper.updateImgById(businessLicenseUrl, id);
    }
}