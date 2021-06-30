package com.weekpro.mall.entity;

public class typeStore {
    int id;
    int typeId;
    String typename;
    public typeStore(){}
    public typeStore(int id, int typeId, String typename) {
        this.id = id;
        this.typeId = typeId;
        this.typename = typename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
