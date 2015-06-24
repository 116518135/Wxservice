package com.wxservice.framework.report.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Band implements Serializable {
    private List children=new ArrayList();
    public Band() {
    }
    public void addChild(Object child){
        if(child!=null){
            children.add(child);
        }
    }
    public List getChildren() {
        return children;
    }


}
