package com.example.demo.Controllers;

import com.example.demo.Services.impl.GameLibServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/GameLibService")
public class GameLibController
{
    @Autowired
    GameLibServiceImpl gameLibService;

    @GetMapping("/GameLibraryItems")
    public List<Map<String, Object>> GetLibItems(String BuyerID) {
        List<Map<String, Object>> MyList = new ArrayList<>();
        List<String> IDList = gameLibService.getGameIDForLibrary(BuyerID);
        for (int i = 0; i < IDList.size(); i++) {
            String TempID = IDList.get(i);
            Map<String, Object> GameInfo = gameLibService.getGameInfo(TempID);
            GameInfo.put("COMMODITY_ID", TempID);
            List<String> GenreList = gameLibService.getGenre(TempID);
            GameInfo.put("GENRES", GenreList);
            MyList.add(GameInfo);
        }
        return MyList;
    }
    @GetMapping("/AddToGameLibrary")
    public Map<String,Object> AddToGameLibrary(String BuyerID,String CommodityID)
    {
        Map<String,Object>ReturnInfo=gameLibService.AddToLibrary(BuyerID,CommodityID);
        return ReturnInfo;
    }
}
