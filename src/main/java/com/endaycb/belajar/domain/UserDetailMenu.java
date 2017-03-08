/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.endaycb.belajar.domain;

/**
 *
 * @author mashiro
 */
public class UserDetailMenu {
    private Integer id, id_user, menu, isAllowed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public Integer getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(Integer isAllowed) {
        this.isAllowed = isAllowed;
    }
    
}
