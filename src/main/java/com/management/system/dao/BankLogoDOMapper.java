package com.management.system.dao;

import com.management.system.model.BankLogoDO;

public interface BankLogoDOMapper {
    int deleteByPrimaryKey(Long sysno);

    int insert(BankLogoDO record);

    int insertSelective(BankLogoDO record);

    BankLogoDO selectByPrimaryKey(Long sysno);

    int updateByPrimaryKeySelective(BankLogoDO record);

    int updateByPrimaryKey(BankLogoDO record);
}