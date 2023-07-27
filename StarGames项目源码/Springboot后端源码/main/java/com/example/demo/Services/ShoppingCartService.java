package com.example.demo.Services;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {
    Map<String,Object> AddShoppingCart(String CommodityID, String UserID);

    List<String> getGameIDForCart(String BuyerID);

    Map<String,Object> getGameInfo(String CommodityID);

    List<String> getGenre(String CommodityID);

    boolean isInCart(String BuyerID, String CommodityID);

    boolean removeItems(String BuyerID, String CommodityID);

    boolean addItems(String BuyID, String CommodityID);
}
