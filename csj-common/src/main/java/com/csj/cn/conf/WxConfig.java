package com.csj.cn.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/4/610:23
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {
    private String appId;
    private String redirectUri;
    private String responseType;
    private String scope;
    private String codeUrl;
    private String accessTokenUrl;
    private String secret;
    private String grantType;
    private String userInfoUrl;
    private String loginSuccess;
    private String loginFail;

    public String reqCodeUri(){
        StringBuffer sb = new StringBuffer(getCodeUrl());
        sb.append("?").append("appid=").append(getAppId());
        sb.append("&").append("redirect_uri=").append(getRedirectUri());
        sb.append("&").append("response_type=").append(getResponseType());
        sb.append("&").append("scope=").append(getScope());
        sb.append("&").append("state=").append("STATE").append("#wechat_redirect");
        return sb.toString();
    }

    public String reqAccessTokenUrl(String code){
        StringBuffer sb = new StringBuffer(getAccessTokenUrl());
        sb.append("?").append("appid=").append(getAppId());
        sb.append("&").append("secret=").append(getSecret());
        sb.append("&").append("code=").append(code);
        sb.append("&").append("grant_type=").append(getGrantType());
        return sb.toString();
    }

    public String reqUserInfoUrl(String accessToken,String openId){
        StringBuffer sb = new StringBuffer(getUserInfoUrl());
        sb.append("?").append("access_token=").append(accessToken);
        sb.append("&").append("openid=").append(openId).append("&lang=zh_CN");
        return sb.toString();
    }
}
