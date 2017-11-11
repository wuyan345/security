package com.shiro.sys.pojo;

public class Group {
    private Integer id;

    private Integer parentId;

    private String name;

    private String desc;

    public Group(Integer id, Integer parentId, String name, String desc) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.desc = desc;
    }

    public Group() {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}