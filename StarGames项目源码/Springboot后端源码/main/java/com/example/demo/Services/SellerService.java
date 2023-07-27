package com.example.demo.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class SellerService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getSellerInfo(String SellerID) {

        /*用一个字符串存储SQL语句，?代表的是参数，在查询的时候通过传参数实现*/
        String sql = "SELECT PUBLISHER_ID,PUBLISHER_NAME,START_TIME,DESCRIPTION,HOMEPAGE_URL FROM PUBLISHERS WHERE PUBLISHER_ID=?";
        /*queryForMap可以在数据库中查出Map类型的数据，第一个参数放SQL语句字符串，第二个参数放参数，即SQL语句中用?表示的部分*/
        Map<String, Object> UserInfo = jdbcTemplate.queryForMap(sql, SellerID);
        return UserInfo;
    }
    public boolean changeSellerInfo(String SellerID,String Name,String Description,String HomePageUrl)
    {
        String sql="UPDATE PUBLISHERS SET PUBLISHER_NAME='"+Name+"',DESCRIPTION='"+Description+"',HOMEPAGE_URL='"+HomePageUrl+"' WHERE PUBLISHER_ID='"+SellerID+"'";
        jdbcTemplate.update(sql);
        return true;
    }

    public List<String> getGameIDForPub(String BuyerID){
        String sql="SELECT COMMODITY_ID FROM COMMODITIES WHERE PUBLISHER_ID=?";
        List<String> IDList = jdbcTemplate.queryForList(sql,String.class,BuyerID);
        return IDList;
    }

    public Map<String,Object> getGameInfo(String CommodityID){
        String sql="SELECT COMMODITY_NAME,PUBLISHERS.PUBLISHER_NAME,PICTURE_URL,COMMODITIES.DESCRIPTION FROM COMMODITIES,PUBLISHERS WHERE PUBLISHERS.PUBLISHER_ID=COMMODITIES.PUBLISHER_ID AND COMMODITY_ID=?";
        Map<String,Object> Info = jdbcTemplate.queryForMap(sql,CommodityID);
        return Info;
    }
    public List<String> getGenre(String CommodityID){
        String sql="SELECT GENRE_NAME FROM COMMODITY_GENRE NATURAL JOIN GENRE WHERE COMMODITY_ID=?";
        List<String> m = jdbcTemplate.queryForList(sql,String.class,CommodityID);
        return m;
    }

    public List<Map<String,Object>> getAllGenres(){
        String sql="SELECT * FROM GENRE";
        List<Map<String,Object>> genres=jdbcTemplate.queryForList(sql);
        return genres;
    }

    public String generateCommodityID()
    {
        String sql = "SELECT MAX(COMMODITY_ID) FROM COMMODITIES";
        String MAX = jdbcTemplate.queryForObject(sql,String.class);
        int iNum = Integer.parseInt(MAX) + 1;
        String NewID = String.valueOf(iNum);
        return NewID;
    }
    public String PublishGame(String PublisherID,String Name,String Description,String Url,int price) {
        String ID = generateCommodityID();
        String sql = "INSERT INTO COMMODITIES(COMMODITY_ID,COMMODITY_NAME,PUBLISHER_ID,PRICE,DESCRIPTION,DOWNLOAD_URL) VALUES('"+ID+"','"+Name+"','"+PublisherID+"','"+price+"','"+Description+"','"+Url+"')";
        jdbcTemplate.update(sql);
        return ID;
    }
    public String PublishAddGenre(String PublisherID,String Name,String Description,String Url,int price,int genreID){
        String ID=PublishGame(PublisherID,Name,Description,Url,price);
        String sql="INSERT INTO COMMODITY_GENRE VALUES('"+ID+"','"+genreID+"')";
        jdbcTemplate.update(sql);
        return ID;
    }
    public boolean AddPic(String CommodityID,String url)
    {
        String sql = "UPDATE COMMODITIES SET PICTURE_URL = '"+url+"' WHERE COMMODITY_ID = '"+CommodityID+"'";
        jdbcTemplate.update(sql);
        return true;
    }
}
