package com.shiro.sys.dao;

import com.shiro.sys.pojo.RoleGroup;

public interface RoleGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleGroup record);

    int insertSelective(RoleGroup record);

    RoleGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleGroup record);

    int updateByPrimaryKey(RoleGroup record);
    
    // 自定义
    int deleteByRoleId(Integer roleId);
}