package com.example.demo.Controllers;

import com.example.demo.Services.impl.WishListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/WishListService")
public class WishListController {
    @Autowired
    WishListServiceImpl wishListService;

    @GetMapping("/AddWishList")
    public Map<String,Object> AddWishList(String CommodityID,String UserID)
    {
        Map<String,Object>ReturnInfo=wishListService.AddWishList(CommodityID,UserID);
        return  ReturnInfo;
    }
    @GetMapping("/WishListItems")
    public List<Map<String,Object>> GetWishListItems(String BuyerID){
        List<Map<String,Object>> MyList= new ArrayList<>();
        List<String>IDList=wishListService.getGameIDForWishList(BuyerID);
        for(int i=0;i<IDList.size();i++) {
            String TempID = IDList.get(i);
            Map<String, Object> GameInfo = wishListService.getGameInfo(TempID);
            GameInfo.put("COMMODITY_ID", TempID);
            List<String> GenreList = wishListService.getGenre(TempID);
            GameInfo.put("GENRES", GenreList);
            MyList.add(GameInfo);
        }
        return MyList;
    }
    @GetMapping("/WishListRemove")
    public boolean RemoveWishListItems(String BuyerID,String CommodityID){
        boolean status=wishListService.removeItems(BuyerID,CommodityID);
        if(!status)
            return false;
        return true;
    }
}
