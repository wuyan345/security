package com.shiro.sys.dao;

import java.util.List;

import com.shiro.sys.pojo.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    // 自定义
    
    /**
     * 根据角色id获取所有对应的菜单
     * @param roleId
     * @return
     */
    List<Menu> selectByRoleId(Integer roleId);
    
    /**
     * 根据角色id获取所有对应的permission字段
     * @param roleId
     * @return
     */
    List<String> selectPermissionByRoleId(Integer roleId);
    
    /**
     * 返回所有菜单
     * @return
     */
    List<Menu> selectAll();
    
}