package com.example.demo.Services.impl;

import com.example.demo.Services.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChangeServiceImpl implements ChangeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int getGameIDForLibrary(String BuyerID, String OldPasswd, String NewPasswd, String Code){
        //-1 PASSWORD_FALSE
        //-2 CODE_FALSE
        //0 SUCCESS!
        String sql1 = "SELECT PASSWORD FROM USERS WHERE USER_ID='"+BuyerID+"'";
        String _OLDPASSWD = jdbcTemplate.queryForObject(sql1,String.class);
        if(!OldPasswd.equals(_OLDPASSWD)) {
            return -1;//原输入密码错误！
        }
        String sql2 = "SELECT MAIL_CODE FROM BUYERS WHERE BUYER_ID='"+BuyerID+"'";
        String _CODE = jdbcTemplate.queryForObject(sql2,String.class);
        if(!Code.equals(_CODE)){
            return -2;//邮箱验证码错误！
        }
        String sql3 = "UPDATE USERS SET PASSWORD = '"+NewPasswd+"' WHERE USER_ID = '"+BuyerID+"'";
        jdbcTemplate.update(sql3);
        return 0;
    }
}
