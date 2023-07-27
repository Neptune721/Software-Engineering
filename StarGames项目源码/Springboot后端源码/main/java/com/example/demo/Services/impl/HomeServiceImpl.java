package com.example.demo.Services.impl;

import com.example.demo.Services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.*;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
@Override
    public List<Map<String, Object>> GetRecommendCommodity() throws Exception {
        String sql = "SELECT COMMODITY_NAME,PRICE,PICTURE_URL,DESCRIPTION FROM COMMODITIES Order By SALES_VOLUME DESC";
        List<Map<String, Object>> TempList = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> returnList = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        //一天的毫秒数
        long nd = 1000 * 60 * 60 * 24;
        String endTime = ft.format(date);
        Long nowTime = date.getTime();
        int count = 0;
        for (int i = 0; i < TempList.size(); i++) {
            Map<String, Object> map = TempList.get(i);
            String startTime = map.get("PUBLISH_TIME").toString();
            Date publish_time = ft.parse(startTime);
            Long pubilisTime = publish_time.getTime();
            int days = (int) ((nowTime - pubilisTime) / nd);
            if (days <= 30) {
                returnList.add(map);
                count++;
                if (count >= 5)
                    break;
            }
        }
        return returnList;
    }
    @Override
    public List<String> getGenre() {
        String sql = "SELECT DISTINCT GENRE_NAME FROM COMMODITY_GENRE NATURAL JOIN GENRE";
        List<String> m = jdbcTemplate.queryForList(sql, String.class);
        return m;
    }
    @Override
    public List<Map<String, Object>> GetHotCommodity() throws Exception {
        String sql = "SELECT COMMODITY_NAME,PRICE,PICTURE_URL,DESCRIPTION FROM COMMODITIES Order By SALES_VOLUME DESC";
        List<Map<String, Object>> TempList = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> returnList = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        //一天的毫秒数
        long nd = 1000 * 60 * 60 * 24;
        String endTime = ft.format(date);
        Long nowTime = date.getTime();
        int count = 0;
        for (int i = 0; i < TempList.size(); i++) {
            Map<String, Object> map = TempList.get(i);
            if (i <= 8) {
                returnList.add(map);
                continue;
            }
            String startTime = map.get("PUBLISH_TIME").toString();
            Date publish_time = ft.parse(startTime);
            Long pubilisTime = publish_time.getTime();
            int days = (int) ((nowTime - pubilisTime) / nd);
            if (days <= 365) {
                returnList.add(map);
                count++;
                if (count >= 8)
                    break;
            }
        }
        return returnList;
    }
    @Override
    public List<Map<String, Object>> GetNewCommodity() throws Exception{
        String sql = "SELECT COMMODITY_NAME,PRICE,PICTURE_URL,DESCRIPTION FROM COMMODITIES Order By SALES_VOLUME DESC";
        List<Map<String, Object>> TempList = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> returnList = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        //一天的毫秒数
        long nd = 1000 * 60 * 60 * 24;
        String endTime = ft.format(date);
        Long nowTime = date.getTime();
        int count = 0;
        for (int i = 0; i < TempList.size(); i++) {
            Map<String, Object> map = TempList.get(i);
            String startTime = map.get("PUBLISH_TIME").toString();
            Date publish_time = ft.parse(startTime);
            Long pubilisTime = publish_time.getTime();
            int days = (int) ((nowTime - pubilisTime) / nd);
            if (days <= 14) {
                returnList.add(map);
                count++;
                if (count >= 8)
                    break;
            }
        }
        return returnList;
    }
    @Override
    public List<Map<String,Object>> GetCommodityClassification(String Genre){
        List<Map<String, Object>> returnList = new ArrayList<>();
        String sql = "SELECT GENRE_ID FROM GENRE WHERE GENRE_NAME=?";
        Map<String,Object> genre=jdbcTemplate.queryForMap(sql,Genre);
        String genre_id=genre.get("GENRE_ID").toString();
        String sql1="SELECT COMMODITY_NAME,PRICE,PICTURE_URL,DESCRIPTION FROM COMMODITIES NATURAL JOIN COMMODITY_GENRE WHERE GENRE_ID=? Order By SALES_VOLUME DESC";
        returnList=jdbcTemplate.queryForList(sql1,genre_id);
        return returnList;
    }
}
