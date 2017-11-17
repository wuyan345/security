package com.shiro.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shiro.sys.pojo.RoleMenu;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);
    
    // 自定义
    
    /**
     * 批量插入角色菜单关系
     * @param roleId
     * @param menuIdList
     * @return
     */
    int batchInsert(@Param("roleId")Integer roleId, @Param("menuIdList")List<Integer> menuIdList);
    
    /**
     * 批量删除指定的角色id的指定菜单关系
     * @param roleId
     * @param menuIdList
     * @return
     */
    int batchDelete(@Param("roleId")Integer roleId, @Param("menuIdList")List<Integer> menuIdList);
    
    /**
     * 批量删除指定的角色id的所有菜单关系
     * @param roleId
     * @return
     */
    int batchDeleteAllByRoleId(Integer roleId);
    
}