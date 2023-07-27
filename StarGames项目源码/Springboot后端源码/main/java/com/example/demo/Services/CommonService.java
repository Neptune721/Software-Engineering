package com.example.demo.Services;

import java.util.List;
import java.util.Map;

public interface CommonService {
    boolean Is_Commodity_Exists(String CommodityID);

    boolean Is_Commodity_In_WishList(String CommodityID, String UserID);

    boolean Is_Commodity_In_ShoppingCart(String CommodityID, String UserID);

    boolean Has_Evaluated(String CommodityID, String UserID);

    List<String> Get_Genre(String CommodityID);

    boolean Is_Commodity_In_GameLibrary(String CommodityID, String UserID);
}
