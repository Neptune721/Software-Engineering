package com.example.demo.Services.impl;

import com.example.demo.Services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
@Service("CommonServices")
public class CommonServiceImpl implements CommonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/
@Override
    public boolean Is_Commodity_Exists(String CommodityID)
    {
        String sql = "SELECT COMMODITY_ID,COMMODITY_NAME,PRICE,PUBLISH_TIME,DESCRIPTION,PICTURE_URL,DOWNLOAD_URL FROM COMMODITIES WHERE COMMODITY_ID=?";
        try
        {
             jdbcTemplate.queryForMap(sql,CommodityID).isEmpty();
        }
        catch (Exception e)
        {
            return  false;
        }

        return true;
    }
    @Override
    public  boolean Is_Commodity_In_WishList(String CommodityID, String UserID)
    {
        String sql="SELECT BUYER_ID,COMMODITY_ID FROM WISHLIST WHERE BUYER_ID='"+UserID+"' AND COMMODITY_ID='"+CommodityID+"'";

        try
        {
            jdbcTemplate.queryForMap(sql).isEmpty();
        }
        catch (Exception e)
        {
            return  false;
        }

        return true;
    }
    @Override
    public  boolean Is_Commodity_In_ShoppingCart(String CommodityID, String UserID)
    {
        String sql="SELECT BUYER_ID,COMMODITY_ID FROM SHOPPINGCART WHERE BUYER_ID='"+UserID+"' AND COMMODITY_ID='"+CommodityID+"'";

        try
        {
            jdbcTemplate.queryForMap(sql).isEmpty();
        }
        catch (Exception e)
        {
            return  false;
        }

        return true;
    }
    @Override
    public  boolean Has_Evaluated(String CommodityID, String UserID)
    {
        String sql="SELECT BUYER_ID,EVALUATION FROM EVALUATION WHERE BUYER_ID='"+UserID+"' AND COMMODITY_ID='"+CommodityID+"'";

        try
        {
            jdbcTemplate.queryForMap(sql).isEmpty();
        }
        catch (Exception e)
        {
            return  false;
        }

        return true;
    }
    @Override
    public List<String>Get_Genre(String CommodityID)
    {
        String Sql="SELECT GENRE_NAME FROM COMMODITY_GENRE LEFT OUTER JOIN GENRE ON COMMODITY_GENRE.GENRE_ID=GENRE.GENRE_ID WHERE COMMODITY_ID=?";
        List<String> m = jdbcTemplate.queryForList(Sql,String.class,CommodityID);
        return m;
    }
    @Override
    public  boolean Is_Commodity_In_GameLibrary(String CommodityID, String UserID)
    {
        String sql="SELECT BUYER_ID,COMMODITY_ID FROM GAMELIBRARY WHERE BUYER_ID='"+UserID+"' AND COMMODITY_ID='"+CommodityID+"'";

        try
        {
           jdbcTemplate.queryForMap(sql).isEmpty();
        }
        catch (Exception e)
        {
            return  false;
        }

        return true;

    }
}
