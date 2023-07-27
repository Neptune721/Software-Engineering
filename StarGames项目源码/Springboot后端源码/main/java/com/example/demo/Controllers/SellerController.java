package com.example.demo.Controllers;

import com.example.demo.Services.LoginService;

import com.example.demo.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/SellerService")
public class SellerController
{
    @Autowired
    SellerService sellerService;
    @GetMapping("/SellerInfo")
    public Map<String, Object> GetBuyerInfo(String SellerID) {
        Map<String, Object> UserInfo = sellerService.getSellerInfo(SellerID);

        return UserInfo;
    }

    @PutMapping("/SellerInfo")
    public boolean changeSellerInfo(String SellerID,String Name,String Description,String HomePageUrl)
    {
        boolean status;
        status = sellerService.changeSellerInfo(SellerID,Name,Description,HomePageUrl);
        return true;
    }

    @GetMapping("/PublishedGames")
    public List<Map<String, Object>> GetLibItems(String PublisherID) {
        List<Map<String, Object>> MyList = new ArrayList<>();
        List<String> IDList = sellerService.getGameIDForPub(PublisherID);
        for (int i = 0; i < IDList.size(); i++) {
            String TempID = IDList.get(i);
            Map<String, Object> GameInfo = sellerService.getGameInfo(TempID);
            GameInfo.put("COMMODITY_ID", TempID);
            List<String> GenreList = sellerService.getGenre(TempID);
            GameInfo.put("GENRES", GenreList);
            MyList.add(GameInfo);
        }
        return MyList;
    }

    @GetMapping("/Genres")
    public List<Map<String,Object>> getAllGenres(){
        List<Map<String,Object>> genres=sellerService.getAllGenres();
        return genres;
    }

    @PostMapping("/NewGame")
    public String PublishAddGenre(String PublisherID,String Name,String Description,String Url,int price,int genreID){
        String ID = sellerService.PublishAddGenre(PublisherID,Name,Description,Url,price,genreID);
        return ID;
    }

    @PostMapping("/CommodityPic")
    public boolean ChangeBuyerAvatar(String CommodityID,String url) {
        sellerService.AddPic(CommodityID,url);
        return true;
    }
}
