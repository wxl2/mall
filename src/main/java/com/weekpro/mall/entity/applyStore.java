package com.weekpro.mall.entity;

/**
 * @author wxl
 * @date 2021/6/28 下午2:35
 * @packageName com.weekpro.mall.entity
 * TODO
 */
public class applyStore {
    private String username;
    private String status;
    /*
    status:0-->申请未处理
    status:1-->同意申请
    status:2-->拒绝申请
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public applyStore(String username, String status) {
        this.username = username;
        this.status = status;
    }
    public  applyStore(){}
}
