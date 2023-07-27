package com.example.demo.Services.impl;

import com.example.demo.Services.GameLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class GameLibServiceImpl implements GameLibService {
    public static final int FAILED=-1;
    public static final int SUCCESS=1;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Resource(name="CommonServices")
    CommonServiceImpl commonService;

    @Override
    public List<String> getGameIDForLibrary(String BuyerID){
        String sql="SELECT COMMODITY_ID FROM GAMELIBRARY WHERE BUYER_ID=?";
        List<String> IDList = jdbcTemplate.queryForList(sql,String.class,BuyerID);
        return IDList;
    }
    @Override
    public Map<String,Object> getGameInfo(String CommodityID){
        String sql="SELECT COMMODITY_NAME,PUBLISHERS.PUBLISHER_NAME,PICTURE_URL,COMMODITIES.DESCRIPTION FROM COMMODITIES,PUBLISHERS WHERE PUBLISHERS.PUBLISHER_ID=COMMODITIES.PUBLISHER_ID AND COMMODITY_ID=?";
        Map<String,Object> Info = jdbcTemplate.queryForMap(sql,CommodityID);
        return Info;
    }
    @Override
    public List<String> getGenre(String CommodityID){
        String sql="SELECT GENRE_NAME FROM COMMODITY_GENRE NATURAL JOIN GENRE WHERE COMMODITY_ID=?";
        List<String> m = jdbcTemplate.queryForList(sql,String.class,CommodityID);
        return m;
    }



    @Override
    public Map<String,Object>AddToLibrary(String BuyerID, String CommodityID){

        Boolean Has_own=commonService.Is_Commodity_In_GameLibrary(CommodityID,BuyerID);
        Map<String,Object>ReturnInfo= new java.util.HashMap<>(Collections.emptyMap());
        if(Has_own)//说明此时该游戏已经在购物车当中
        {
            ReturnInfo.put("STATUS",FAILED);
            ReturnInfo.put("REASON","您已经拥有该游戏");
            return  ReturnInfo;
        }
        else {
            Date times = new java.sql.Timestamp(new java.util.Date().getTime());
            String sql = "INSERT INTO GAMELIBRARY (BUYER_ID,COMMODITY_ID,JOIN_TIME) VALUES ('" + BuyerID + "','" + CommodityID + "','" + times + "')";

            try {
                jdbcTemplate.update(sql);
            } catch (Exception e) {
                /*说明此时添加游戏到游戏库*/
                ReturnInfo.put("STATUS", FAILED);
                ReturnInfo.put("REASON", "数据库更新失败，请检查数据库设置");
                return ReturnInfo;
            }

            /*此时说明添加愿望单到数据库成功*/
            ReturnInfo.put("STATUS", SUCCESS);
            return ReturnInfo;
        }
    }
}
