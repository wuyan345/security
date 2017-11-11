package com.shiro.sys.dao;

import java.util.List;

import com.shiro.sys.pojo.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    /**
     * 根据用户Id获取所有对应角色
     * @param userId
     * @return
     */
    List<Role> selectByUserId(Integer userId);
    
}