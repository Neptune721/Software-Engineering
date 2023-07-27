package com.example.demo.Services;

import java.util.List;
import java.util.Map;

public interface WishListService {
    Map<String,Object> AddWishList(String CommodityID, String UserID);

    List<String> getGameIDForWishList(String BuyerID);

    Map<String,Object> getGameInfo(String CommodityID);

    List<String> getGenre(String CommodityID);

    boolean removeItems(String BuyerID, String CommodityID);
}
