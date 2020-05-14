package com.csj.cn.service;

import com.csj.cn.dto.SysUser;

import java.util.List;
import java.util.Set;

public interface SysUserService {

    /**
     * 通过名字查询
     *
     * @param username
     * @return
     */
    SysUser selectByUsername(String username);

    /**
     * 通过用户名查询菜单权限
     *
     * @param username
     * @return
     */
    Set<String> selectPermissions(String username);
}
