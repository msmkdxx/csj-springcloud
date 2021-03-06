package com.csj.cn.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.csj.cn.dto.SysUser;
import com.csj.cn.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 用户登录认证信息查询
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.selectByUsername(username);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissionSet = sysUserService.selectPermissions(sysUser.getUsername());
        List<GrantedAuthority> grantedAuthorities = permissionSet.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(sysUser.getUsername(), sysUser.getPassword(), sysUser.getSalt(), grantedAuthorities);
    }
}