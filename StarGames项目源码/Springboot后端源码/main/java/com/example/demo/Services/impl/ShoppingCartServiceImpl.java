package com.example.demo.Services.impl;

import com.example.demo.Services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Date;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    public static final int FAILED=-1;
    public static final int SUCCESS=1;
    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/
    @Autowired
    @Resource(name="CommonServices")
    CommonServiceImpl commonService;
    @Override
    public Map<String,Object> AddShoppingCart(String CommodityID,String UserID)
    {
        boolean Has_Exist=commonService.Is_Commodity_In_ShoppingCart(CommodityID,UserID);
        Map<String,Object>ReturnInfo= new java.util.HashMap<>(Collections.emptyMap());
        if(Has_Exist)//说明此时该游戏已经在购物车当中
        {
            ReturnInfo.put("STATUS",FAILED);
            ReturnInfo.put("REASON","您的购物车当中已经有该游戏");
            return  ReturnInfo;
        }
        else
        {

            Date times= new java.sql.Timestamp(new java.util.Date().getTime());

            String Sql="INSERT INTO SHOPPINGCART (BUYER_ID,COMMODITY_ID,JOIN_TIME) VALUES ('"+UserID+"','"+CommodityID+"','"+times+"')";

            try
            {
                jdbcTemplate.update(Sql);
            }
            catch (Exception e)
            {
                /*说明此时添加购物车失败*/
                ReturnInfo.put("STATUS",FAILED);
                ReturnInfo.put("REASON","数据库更新失败，请检查数据库设置");
                return ReturnInfo;
            }

            /*此时说明添加购物车到数据库成功*/
            ReturnInfo.put("STATUS",SUCCESS);
            return  ReturnInfo;
        }
    }
    @Override
    public List<String> getGameIDForCart(String BuyerID){
        String sql="SELECT COMMODITY_ID FROM SHOPPINGCART WHERE BUYER_ID=?";
        List<String> IDList =jdbcTemplate.queryForList(sql, String.class,BuyerID);
        return IDList;
    }
    @Override
    public Map<String,Object> getGameInfo(String CommodityID){
        String sql="SELECT COMMODITY_NAME,PUBLISHERS.PUBLISHER_NAME,PRICE,PICTURE_URL,COMMODITIES.DESCRIPTION FROM COMMODITIES,PUBLISHERS WHERE PUBLISHERS.PUBLISHER_ID=COMMODITIES.PUBLISHER_ID AND COMMODITY_ID=?";
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
    public boolean isInCart(String BuyerID, String CommodityID){
        String sql="SELECT * FROM SHOPPINGCART WHERE BUYER_ID='"+BuyerID+"'AND COMMODITY_ID='"+CommodityID+"'";
        boolean EmptyStatus=jdbcTemplate.queryForList(sql).isEmpty();
        if(EmptyStatus){
            return false; //该商品不在购物车
        }
        return true; //该商品在购物车中
    }
    @Override
    public boolean removeItems(String BuyerID, String CommodityID){
        if(!isInCart(BuyerID,CommodityID))
            return false;
        String sql="DELETE FROM SHOPPINGCART WHERE BUYER_ID='"+ BuyerID+"'AND COMMODITY_ID='"+CommodityID+"'";
        jdbcTemplate.update(sql);
        return true;
    }
    @Override
    public boolean addItems(String BuyID,String CommodityID){
        if(isInCart(BuyID,CommodityID))
            return false;
        LocalDateTime localDateTime=LocalDateTime.now();
        String time=localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String sql="INSERT INTO SHOPPINGCART VALUES ('"+BuyID+"','"+CommodityID+"','"+time+"')";
        jdbcTemplate.update(sql);
        return true;
    }
}
