package com.weekpro.mall.entity;

public class typeStore {
    int typeId;
    String typename;
    public typeStore(){}

    public typeStore(int typeId, String typename) {
        this.typeId = typeId;
        this.typename = typename;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
