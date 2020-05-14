package com.csj.cn.service.impl;

import com.csj.cn.dto.SysMenu;
import com.csj.cn.dto.SysUser;
import com.csj.cn.dto.SysUserExample;
import com.csj.cn.mapper.SysUserMapper;
import com.csj.cn.service.SysMenuService;
import com.csj.cn.service.SysUserService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/1223:42
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public SysUser selectByUsername(String username) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUsernameEqualTo(username);
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
        if (!CollectionUtils.isEmpty(sysUserList)) return sysUserList.get(0);

        return null;
    }

    @Override
    public Set<String> selectPermissions(String username) {
        Set<String> permissionSet = Sets.newHashSet();
        List<SysMenu> sysMenuList = sysMenuService.selectByUsername(username);
        for (SysMenu sysMenu : sysMenuList) {
            if (!StringUtils.isEmpty(sysMenu.getPermission())) {
                permissionSet.add(sysMenu.getPermission());
            }
        }
        return permissionSet;
    }
}
