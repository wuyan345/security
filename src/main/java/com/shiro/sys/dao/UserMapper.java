package com.shiro.sys.dao;

import java.util.List;

import com.shiro.sys.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    // 自定义
    
    /**
     * 根据用户名返回用户信息
     * @param username
     * @return
     */
    User selectByUsername(String username);
    
    /**
     * 返回所有用户信息
     * @return
     */
    List<User> selectAllUser();
    
    /**
     * 批量删除用户
     * @param userId
     * @return
     */
    int batchDeleteByUserId(Integer[] userId);
}