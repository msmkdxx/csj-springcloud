package com.csj.cn.controller;

import com.csj.cn.dto.SysUser;
import com.csj.cn.security.JwtAuthenticatioToken;
import com.csj.cn.service.SysUserService;
import com.csj.cn.utils.PasswordUtils;
import com.csj.cn.utils.ReturnResult;
import com.csj.cn.utils.ReturnResultUtils;
import com.csj.cn.utils.SecurityUtils;
import com.csj.cn.vo.SysUserVo;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/1223:23
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public ReturnResult login(@RequestBody SysUserVo sysUserVo, HttpServletRequest request) throws IOException {
//        String username = sysUserVo.getUsername();
//        String password = sysUserVo.getPassword();
//        String captcha = sysUserVo.getCaptcha();

        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
/*        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (kaptcha == null) {
            return ReturnResultUtils.returnFail("验证码已失效");
        }*/
//		if(!captcha.equals(kaptcha)){
//			return HttpResult.error("验证码不正确");
//		}
        // 用户信息
        SysUser sysUser = sysUserService.selectByUsername(sysUserVo.getUsername());

        // 账号不存在、密码错误、账号锁定
        if (ObjectUtils.isEmpty(sysUser)) {
            return ReturnResultUtils.returnFail("账号不存在");
        } else if (!PasswordUtils.matches(sysUser.getSalt(), sysUserVo.getPassword(), sysUser.getPassword())) {
            return ReturnResultUtils.returnFail("密码不正确");
        } else if (sysUser.getStatus() == 0) {
            return ReturnResultUtils.returnFail("账号已被锁定,请联系管理员");
        }
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, sysUserVo.getUsername(), sysUserVo.getPassword(), authenticationManager);

        return ReturnResultUtils.returnSucess(token);
    }
}
