package com.example.demo.Services.impl;

import com.example.demo.Services.CommonService;
import com.example.demo.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    @Resource(name="CommonServices")
    CommonService commonService;

    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/

    @Override
    public List<Map<String,Object>> Search_by_name(String name)
    {


        String Sql="SELECT COMMODITY_ID,COMMODITY_NAME,PRICE,PUBLISH_TIME,DESCRIPTION,PICTURE_URL,DOWNLOAD_URL FROM COMMODITIES WHERE COMMODITY_NAME LIKE '%"+name+"%'";
        try
        {
            List<Map<String,Object>>CommodityInfo=jdbcTemplate.queryForList(Sql);
            List<Map<String,Object>>CommodityInfo_return=new ArrayList<>();
            //说明此时一定搜到了有关的游戏，则搜索游戏相关的分类
            for (int i=0;i<CommodityInfo.size();i++)
            {
                Map<String,Object>tmp_commodity=CommodityInfo.get(i);
                Object status=tmp_commodity.get("COMMODITY_ID");
                String COMMODITY_ID=(String) status;
                List<String>Genre=commonService.Get_Genre(COMMODITY_ID);
                tmp_commodity.put("GENRE",Genre);
                CommodityInfo_return.add(tmp_commodity);
            }
            return  CommodityInfo_return;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Override
    public List<Map<String,Object>> Search_by_friend_name(String BuyerID, String name)
    {
        String Sql="SELECT BUYER_NAME,BIRTHDAY,MAIL,Avatar  FROM FRIENDS LEFT OUTER JOIN BUYERS ON FRIENDS.FRIEND_ID=BUYERS.BUYER_ID WHERE FRIENDS.BUYER_ID='"+BuyerID+"' AND BUYERS.BUYER_NAME LIKE'%"+name+"%'";
        try
        {
            List<Map<String,Object>>FriendInfo=jdbcTemplate.queryForList(Sql);
            return  FriendInfo;
        }
        catch (Exception e)
        {
            /*List<Map<String,Object>>FriendInfo_return=new ArrayList<>();
            Map<String,Object>FriendInfo= new java.util.HashMap<>(Collections.emptyMap());
            FriendInfo.put("STATUS",-1);
            FriendInfo_return.add(FriendInfo);
            return  FriendInfo_return;*/
            return null;
        }
    }
}
