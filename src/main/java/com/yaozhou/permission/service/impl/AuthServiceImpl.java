package com.yaozhou.permission.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yaozhou.permission.cache.CacheService;
import com.yaozhou.permission.cache.KeyPrefix;
import com.yaozhou.permission.cache.keyprefix.impl.UserKeyPrefix;
import com.yaozhou.permission.common.TimeUnit;
import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.common.security.DESMessageEncrypt;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.LoginUserParam;
import com.yaozhou.permission.service.AuthService;
import com.yaozhou.permission.service.SysUserService;
import com.yaozhou.permission.util.CookieUtil;
import com.yaozhou.permission.util.EncryptUtil;
import com.yaozhou.permission.util.PassWordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yaozhou.permission.common.CookieNames.User.*;

/**
 * @author Yao.Zhou
 * @since 2018/7/28 21:01
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final int MD5_TIMES = 10;

    private static final DESMessageEncrypt DES_COOKIE_USER_INFO = new DESMessageEncrypt("IS!)$Jf&*%");

    @Autowired
    SysUserService sysUserService;
    @Autowired
    private CacheService cacheService;

    @Override
    public SysUser keepAlive(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (checkVisa(request) == false) {

            return null;
        }

        JSONObject cookieInfo = decodeLoginCookie(request);
        if (null == cookieInfo) {

            return null;
        }

        String userId = cookieInfo.getString("userId");
        if (null == userId) {

            return null;
        }

        SysUser sysUser = (SysUser) cacheService.get(UserKeyPrefix.CACHE_KEY_USERNAME_TO_USER, userId);
        if (null == sysUser) {
            clearLoginCookie(response);

            return null;
        }
        if (sysUser.getStatus() != 1) {
            cacheService.remove(UserKeyPrefix.CACHE_KEY_USERNAME_TO_USER, userId);
            clearLoginCookie(response);

            return null;
        }

        //重置缓存
        cacheService.set(UserKeyPrefix.CACHE_KEY_USERNAME_TO_USER, userId, sysUser);

        return sysUser;
    }

    @Override
    public void login(LoginUserParam loginUserParam, HttpServletResponse response) throws Exception {
        SysUser sysUser = sysUserService.selectByUserName(loginUserParam.getUsername());
        if (null == sysUser) {
            throw new PermException(CODE_USER_NOT_EXIST, "用户不存在");
        }

        if (sysUser.getStatus() != 1) {
            throw new PermException(CODE_USER_FROZEN, "用户已被冻结");
        }

        String inputEncodedPassword = PassWordUtil.encodePassword(loginUserParam.getPassword(), sysUser.getSalt());
        if (!StringUtils.equals(sysUser.getPassword(), inputEncodedPassword)) {
            throw new PermException(CODE_USER_PASSWORD_ERROR, "密码错误");
        }

        //设置Cookie
        setLoginCookie(sysUser, response);
        //设置缓存
        cacheService.set(UserKeyPrefix.CACHE_KEY_USERNAME_TO_USER, sysUser.getUserId().toString(), sysUser);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (checkVisa(request) == true) {
            JSONObject cookieInfo = decodeLoginCookie(request);
            if (null != cookieInfo) {
                String userId = cookieInfo.getString("userId");
                if (null != userId) {
                    cacheService.remove(UserKeyPrefix.CACHE_KEY_USERNAME_TO_USER, userId);
                }
            }
        }

        clearLoginCookie(response);
    }

    //===========================================================================

    /**
     * 校验visa
     * @param request
     * @return
     */
    private boolean checkVisa(HttpServletRequest request) {
        String userInfoFromCookie = CookieUtil.getCookieValue(COOKIE_NAME_USER_INFO, request);
        String visaFromCookie = CookieUtil.getCookieValue(COOKIE_NAME_USER_INFO_VISA, request);
        if (userInfoFromCookie == null || visaFromCookie == null) {

            return false;
        }

        String visa = EncryptUtil.md5(MD5_TIMES, userInfoFromCookie).toLowerCase();
        if (!visa.equals(visaFromCookie)) {

            return false;
        }

        return true;
    }

    /**
     * 解码用户cookie
     * @param request
     * @return
     */
    private JSONObject decodeLoginCookie(HttpServletRequest request) {
        String userInfoFromCookie = CookieUtil.getCookieValue(COOKIE_NAME_USER_INFO, request);

        try {
            return JSONObject.parseObject(
                        DES_COOKIE_USER_INFO.decode(userInfoFromCookie)
                    );
        } catch (Exception e) { }

        return null;
    }

    /**
     * 清除用户登录cookie
     * @param response
     */
    private void clearLoginCookie(HttpServletResponse response)  {
        CookieUtil.deleteCookie(response, COOKIE_NAME_USER_INFO, "/");
        CookieUtil.deleteCookie(response, COOKIE_NAME_USER_INFO_VISA, "/");
    }

    /**
     * 设置登录cookie
     *
     * cookie时长为30天
     *
     * @param sysUser
     * @param response
     * @throws Exception
     */
    private void setLoginCookie(SysUser sysUser, HttpServletResponse response) throws Exception {
        KeyPrefix uck = UserKeyPrefix.CACHE_KEY_USERNAME_TO_USER;

        String infoKey = COOKIE_NAME_USER_INFO;
        String visaKey = COOKIE_NAME_USER_INFO_VISA;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", sysUser.getUserId());

        String infoValue = DES_COOKIE_USER_INFO.encode(jsonObject.toJSONString());
        String visaValue = EncryptUtil.md5(MD5_TIMES, infoValue, sysUser.getSalt()).toLowerCase();

        CookieUtil.setCookie(response, infoKey, infoValue, "/", TimeUnit.DAY * 30);
        CookieUtil.setCookie(response, visaKey, visaValue, "/", TimeUnit.DAY * 30);
    }

}
