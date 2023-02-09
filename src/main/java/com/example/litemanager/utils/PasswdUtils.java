package com.example.litemanager.utils;

import com.example.litemanager.constant.ConmonConstant;
import org.springframework.util.DigestUtils;

/**
 * @author: hj
 * @date: 2023/2/9
 * @time: 8:53 AM
 */
public class PasswdUtils {

    /**
     * 生成密码
     *
     * @param passwd
     * @return
     */
    public static String generatePasswd(String passwd) {
        return DigestUtils.md5DigestAsHex((ConmonConstant.passwdSalt + passwd).getBytes());
    }
}
