package com.xiangshang.xsclub.service;

import com.xiangshang.xsclub.db.dao.UserMapper;
import com.xiangshang.xsclub.db.model.User;
import com.xiangshang.xsclub.web.result.ResultCode;
import com.xiangshang.xsclub.web.result.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(long UserId) {
        return userMapper.selectByPrimaryKey(UserId);
    }

    public User getUserByMobile(String mobile) {
        return userMapper.selectByMobile(mobile);
    }

    public void passwordValid(User user, String password) {
        if (user == null) {
            throw new ResultException(ResultCode.NULL_USER.getCode(), "用户不存在");
        }

        if (!user.getPasswd().equals(password)) {
            throw new ResultException(ResultCode.PARAM_ERROR.getCode(), "用户密码错误");
        }
    }
}
