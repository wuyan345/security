package com.shiro.sys.pojo;

public class Menu {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer type;

    private String url;

    private String permission;

    public Menu(Integer id, Integer parentId, String name, Integer type, String url, String permission) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.type = type;
        this.url = url;
        this.permission = permission;
    }

    public Menu() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }
}