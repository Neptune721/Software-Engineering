package com.example.demo.Services.impl;

import com.example.demo.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/*
 * Service类
 * 这个@Service一定要加上！
 * */
@Service
public class LoginServiceImpl implements LoginService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/
   /*现在手机号UID均可登录！*/
   /*现在手机号UID均可登录！*/
   /*重要的事情说3遍，现在手机号UID均可登录！*/
   @Override
    public int checkIdentity(String UserID, String Password) {


        String sql = "SELECT USER_TYPE FROM USERS WHERE (USER_ID='" + UserID + "'OR USER_PHONE='" + UserID + "')AND PASSWORD='" + Password + "'";

        /*
         * 判断查询是否为空，即用户名密码是否正确
         * */
        boolean isEmpty = jdbcTemplate.queryForList(sql).isEmpty();

        if (isEmpty == true) return -1;

        else {

            /*
             * 若非空，则根据数据库表中的USER_TYPE确定登录的类型
             * */
            int identity = jdbcTemplate.queryForObject(sql, int.class);

            return identity;

        }
    }
    @Override
    public String generateUserID()
    {
        String sql = "SELECT MAX(USER_ID) FROM USERS";
        String MAX = jdbcTemplate.queryForObject(sql,String.class);
        int iNum = Integer.parseInt(MAX) + 1;
        String NewUserID = String.valueOf(iNum);
        return NewUserID;
    }
    @Override
    public boolean addNewUser(String PhoneNumber,String Password,int UserType) {
        String sql0="SELECT * FROM USERS WHERE USER_PHONE = '"+PhoneNumber+"'";
        boolean EmptyStatus = jdbcTemplate.queryForList(sql0).isEmpty();
        if(EmptyStatus == false)
        {
            return false;
        }
        String NewUserID=generateUserID();
        String sql1="INSERT INTO USERS VALUES ('"+NewUserID+"','"+PhoneNumber+"','"+Password+"','"+UserType+"')";
        jdbcTemplate.update(sql1);
        if(UserType == 0) {
            String sql2 = "INSERT INTO BUYERS(BUYER_ID) VALUES('"+NewUserID+"')";
            jdbcTemplate.update(sql2);
        }
        else if(UserType == 1){
            String sql2 = "INSERT INTO PUBLISHERS(PUBLISHER_ID) VALUES('"+NewUserID+"')";
            jdbcTemplate.update(sql2);
        }
        return true;
    }
}
