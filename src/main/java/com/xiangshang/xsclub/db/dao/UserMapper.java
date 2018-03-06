package com.xiangshang.xsclub.db.dao;


import com.xiangshang.xsclub.db.model.User;

public interface UserMapper {
    User selectByPrimaryKey(Long id);

    User selectByMobile(String mobile);
}
