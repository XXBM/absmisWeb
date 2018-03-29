package com.absmis.domain.message;

import java.io.Serializable;


public class UserInfo implements Serializable {
    //用户名
    private String userName;
    //用户类型
    private String userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
