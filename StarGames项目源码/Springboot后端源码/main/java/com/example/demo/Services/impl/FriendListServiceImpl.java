package com.example.demo.Services.impl;

import com.example.demo.Services.FriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FriendListServiceImpl implements FriendListService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<String> getFriendIDForList(String BuyerID){
        String sql="SELECT FRIEND_ID FROM FRIENDS WHERE BUYER_ID=?";
        List<String> IDList = jdbcTemplate.queryForList(sql,String.class,BuyerID);
        return IDList;
    }
    @Override
    public Map<String,Object> getFriendInfo(String FRIEND_ID){
        String sql="SELECT Avatar,BUYER_NAME,BIRTHDAY,MAIL FROM BUYERS WHERE BUYER_ID=?";
        Map<String,Object> Info = jdbcTemplate.queryForMap(sql,FRIEND_ID);
        return Info;
    }
    @Override
    public boolean deleteFriend(String BuyerID,String FriendID){
        String sql="DELETE FROM FRIENDS WHERE BUYER_ID=? and FRIEND_ID=?";
        jdbcTemplate.update(sql,BuyerID,FriendID);
        return true;
    }
}
