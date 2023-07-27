package com.example.demo.Services.impl;
import com.example.demo.Services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Date;

@Service
public class WishListServiceImpl implements WishListService {
    public static final int FAILED=-1;
    public static final int SUCCESS=1;
    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/

    @Autowired
    @Resource(name="CommonServices")
    CommonServiceImpl commonService;
    @Override
    public Map<String,Object> AddWishList(String CommodityID,String UserID)
    {
        boolean Has_Exist=commonService.Is_Commodity_In_WishList(CommodityID,UserID);
        Map<String,Object>ReturnInfo= new java.util.HashMap<>(Collections.emptyMap());
        if(Has_Exist)//说明此时该游戏已经在愿望单当中
        {
            ReturnInfo.put("STATUS",FAILED);
            ReturnInfo.put("REASON","您的愿望单当中已经有该游戏");
            return  ReturnInfo;
        }
        else
        {

            Date time = new java.sql.Date(new java.util.Date().getTime());

            String Sql="INSERT INTO WISHLIST (BUYER_ID,COMMODITY_ID,JOIN_TIME) VALUES ('"+UserID+"','"+CommodityID+"','"+time+"')";

            try
            {
                jdbcTemplate.update(Sql);
            }
            catch (Exception e)
            {
                /*说明此时添加愿望单失败*/
                ReturnInfo.put("STATUS",FAILED);
                ReturnInfo.put("REASON","数据库更新失败，请检查数据库设置");
                return ReturnInfo;
            }

            /*此时说明添加愿望单到数据库成功*/
            ReturnInfo.put("STATUS",SUCCESS);
            return  ReturnInfo;
        }
    }
    @Override
    public List<String> getGameIDForWishList(String BuyerID){
        String sql="SELECT COMMODITY_ID FROM WISHLIST WHERE BUYER_ID=?";
        List<String> IDList =jdbcTemplate.queryForList(sql, String.class,BuyerID);
        return IDList;
    }
    @Override
    public Map<String,Object> getGameInfo(String CommodityID){
        String sql="SELECT COMMODITY_NAME,PUBLISHERS.PUBLISHER_NAME,PICTURE_URL,COMMODITIES.DESCRIPTION FROM COMMODITIES,PUBLISHERS WHERE PUBLISHERS.PUBLISHER_ID=COMMODITIES.PUBLISHER_ID AND COMMODITY_ID=?";
        Map<String,Object> Info=jdbcTemplate.queryForMap(sql,CommodityID);
        return Info;
    }
    @Override
    public List<String> getGenre(String CommodityID){
        String sql="SELECT GENRE_NAME FROM COMMODITY_GENRE NATURAL JOIN GENRE WHERE COMMODITY_ID=?";
        List<String> genreList=jdbcTemplate.queryForList(sql, String.class,CommodityID);
        return genreList;
    }
    @Override
    public boolean removeItems(String BuyerID,String CommodityID){
        String sql0="SELECT * FROM WISHLIST WHERE BUYER_ID='"+BuyerID+"'AND COMMODITY_ID='"+CommodityID+"'";
        boolean EmptyStatus=jdbcTemplate.queryForList(sql0).isEmpty();
        if(EmptyStatus){
            return false;
        }
        String sql1="DELETE FROM WISHLIST WHERE BUYER_ID='"+ BuyerID+"'AND COMMODITY_ID='"+CommodityID+"'";
        jdbcTemplate.update(sql1);
        return true;
    }
}
