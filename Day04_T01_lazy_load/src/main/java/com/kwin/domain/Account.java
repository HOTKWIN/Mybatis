package com.kwin.domain;

import java.io.Serializable;

/**
 * @author kwin
 * @create 2019-12-09 14:36
 */
public class Account implements Serializable {

    private Integer aid;
    private Integer uid;
    private Double money;

    private User user;

    public Integer getId() {
        return aid;
    }

    public void setId(Integer id) {
        this.aid = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", money=" + money +
                ", user=" + user +
                '}';
    }
}
