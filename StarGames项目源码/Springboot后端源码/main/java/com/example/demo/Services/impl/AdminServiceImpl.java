package com.example.demo.Services.impl;

import com.example.demo.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<String> getBuyerIDForList(){
        String sql="SELECT BUYER_ID FROM BUYERS";
        List<String> IDList = jdbcTemplate.queryForList(sql,String.class);
        return IDList;
    }
    @Override
    public boolean blockBuyer(String BuyerID){
        String sql="UPDATE BUYERS set IS_ACTIVE=0 WHERE BUYER_ID=?";
        jdbcTemplate.update(sql,BuyerID);
        return true;
    }
    @Override
    public List<String> getCommoditiesIDForList(){
        String sql="SELECT COMMODITY_ID FROM COMMODITIES";
        List<String> IDList = jdbcTemplate.queryForList(sql,String.class);
        return IDList;
    }
    @Override
    public Map<String, Object> getCommodityInfo(String CommodityID) {
        String sql = "SELECT COMMODITY_ID,COMMODITY_NAME,PRICE,COMMODITIES.DESCRIPTION,PUBLISHER_NAME,PICTURE_URL FROM COMMODITIES,PUBLISHERS WHERE PUBLISHERS.PUBLISHER_ID=COMMODITIES.PUBLISHER_ID AND COMMODITY_ID=?";
        Map<String, Object> Info = jdbcTemplate.queryForMap(sql, CommodityID);
        return Info;
    }
    @Override
    public boolean withdrawCommodity(String CommodityID){
        String sql="DELETE FROM COMMODITIES WHERE COMMODITY_ID=?";
        jdbcTemplate.update(sql,CommodityID);
        return true;
    }
}
