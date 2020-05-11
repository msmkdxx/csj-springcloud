package com.csj.cn.conf;

import com.csj.cn.vo.UserVo;
import org.springframework.core.MethodParameter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentUserComplete implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class)&&parameter.getParameterType().isAssignableFrom(UserVo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        UserVo userVo = (UserVo) nativeWebRequest.getAttribute("userVo", RequestAttributes.SCOPE_REQUEST);
        if(!ObjectUtils.isEmpty(userVo)){
            return userVo;
        }
        return null;
    }
}
