package com.example.demo.Controllers;

import com.example.demo.Services.impl.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/ShoppingCartService")
public class ShoppingCartController {
    @Autowired
    ShoppingCartServiceImpl shoppingCartService;

    @GetMapping("/AddShoppingCart")
    public Map<String,Object> AddShoppingCart(String CommodityID,String UserID)
    {
        Map<String,Object>ReturnInfo=shoppingCartService.AddShoppingCart(CommodityID,UserID);
        return  ReturnInfo;
    }
    @GetMapping("/ShoppingCartItems")
    public List<Map<String,Object>> GetWishListItems(String BuyerID){
        List<Map<String,Object>> MyList= new ArrayList<>();
        List<String>IDList=shoppingCartService.getGameIDForCart(BuyerID);
        for(int i=0;i<IDList.size();i++) {
            String TempID = IDList.get(i);
            Map<String, Object> GameInfo = shoppingCartService.getGameInfo(TempID);
            GameInfo.put("COMMODITY_ID", TempID);
            List<String> GenreList = shoppingCartService.getGenre(TempID);
            GameInfo.put("GENRES", GenreList);
            MyList.add(GameInfo);
        }
        return MyList;
    }

    @GetMapping("/ShoppingCartRemove")
    public boolean RemoveShoppingCartItems(String BuyerID,String CommodityID){
        boolean status=shoppingCartService.removeItems(BuyerID,CommodityID);
        if(!status)
            return false;
        return true;
    }
    @PostMapping("/CheckCart")
    public boolean CheckShoppingCart(String BuyerID,String CommodityID){
        boolean status = shoppingCartService.isInCart(BuyerID,CommodityID);
        if(!status)
            return false; //该商品不在购物车中
        return true; //该商品在购物车中
    }
    @PostMapping("/Wish2Cart")
    public boolean WishList2ShoppingCart(String BuyerID,String CommodityID){
        boolean status= shoppingCartService.addItems(BuyerID,CommodityID);
        if(!status)
            return false;
        return true;
    }
}
