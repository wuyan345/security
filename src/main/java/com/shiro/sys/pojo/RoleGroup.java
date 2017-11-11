package com.shiro.sys.pojo;

public class RoleGroup {
    private Integer id;

    private Integer roleId;

    private Integer groupId;

    public RoleGroup(Integer id, Integer roleId, Integer groupId) {
        this.id = id;
        this.roleId = roleId;
        this.groupId = groupId;
    }

    public RoleGroup() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}