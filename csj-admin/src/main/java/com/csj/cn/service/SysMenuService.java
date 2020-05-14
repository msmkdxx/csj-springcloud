package com.csj.cn.service;

import com.csj.cn.dto.SysMenu;

import java.util.List;

public interface SysMenuService {

    /**
     * 通过用户名查询menu
     *
     * @param username
     * @return
     */
    List<SysMenu> selectByUsername(String username);
}
