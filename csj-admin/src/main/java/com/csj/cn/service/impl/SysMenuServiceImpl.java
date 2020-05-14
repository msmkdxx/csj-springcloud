package com.csj.cn.service.impl;

import com.csj.cn.constants.SysConstants;
import com.csj.cn.dto.SysMenu;
import com.csj.cn.mapper.SysMenuMapper;
import com.csj.cn.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/130:30
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> selectByUsername(String username) {
        if (StringUtils.isEmpty(username) || SysConstants.ADMIN.equalsIgnoreCase(username)) {
            return sysMenuMapper.selectByExample(null);
        }
        return sysMenuMapper.selectByUsername(username);
    }
}
