/*
 * Service 服务层，Controller负责给前端返回数据的以及接收前端的数据
 * 而Service就提供这些数据实现的方法，Controller中调用这些方法
 * 本项目为了简化不设Mybatis，而直接在Service层中调用SQL语句查询数据库
 * package 在文件的首行 表示这个文件位于哪个软件包中
 * */
package com.example.demo.Services.impl;

/*
 * import 添加库文件，类似于C语言的#include
 * 如果有爆红，要检查Maven配置，简单的解决方法是在爆红的地方按下Alt+Enter-添加Maven依赖项。
 * */
import com.example.demo.Services.BuyerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Map;

/*
 * Service类
 * 这个@Service一定要加上！
 * */
@Service
public class BuyerInfoServiceImpl implements BuyerInfoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/

    /**
     * 基本的查询函数，实现一个最基本的查询功能
     * 函数名称:getBuyerInfo()
     * 功能:根据参数BUYER_ID，在数据库USERS和BUYERS表中查询该用户的各项信息
     * 返回值:Map，类似于JSON文件，这种数据类型前端vue可以读取
     */
    @Override
    public Map<String, Object> getBuyerInfo(String BuyerID) {

        /*用一个字符串存储SQL语句，?代表的是参数，在查询的时候通过传参数实现*/
        String sql = "SELECT USER_ID,USER_PHONE,BUYER_NAME,BIRTHDAY,MAIL,Avatar,Address,IS_ACTIVE FROM USERS,BUYERS WHERE BUYERS.BUYER_ID = USERS.USER_ID AND BUYER_ID=?";

        /*queryForMap可以在数据库中查出Map类型的数据，第一个参数放SQL语句字符串，第二个参数放参数，即SQL语句中用?表示的部分*/
        Map<String, Object> UserInfo = jdbcTemplate.queryForMap(sql, BuyerID);
        return UserInfo;
    }

    @Override
    public boolean changeBuyerInfo(String BuyerID, String BuyerName, String PhoneNumber, String Mail, String Address, Date birthday) {

        String sql_1 = "UPDATE USERS SET USER_PHONE='"+PhoneNumber+"'WHERE USER_ID='"+BuyerID+"'";
        jdbcTemplate.update(sql_1);
        String sql_2 = "UPDATE BUYERS SET BUYER_NAME='"+BuyerName+"',MAIL='"+Mail+"',Address='"+Address+"',BIRTHDAY='"+birthday+"'WHERE BUYER_ID='"+BuyerID+"'";
        jdbcTemplate.update(sql_2);
        return true;
    }
}

