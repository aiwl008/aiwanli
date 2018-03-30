package com.aiwl.pms.entity;

import com.aiwl.common.entity.Query;

public class GoodsClass extends Query{
    private Integer classid;

    private String classname;

    private Integer parentid;

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
    
    @Override
    public String toString(){
		return "GoodsClass = className:"+this.classname+", parentId:"+this.parentid;
    	
    }
}