package com.example.demo.Services;

import java.util.List;
import java.util.Map;

public interface GameLibService {
    List<String> getGameIDForLibrary(String BuyerID);

    Map<String,Object> getGameInfo(String CommodityID);

    List<String> getGenre(String CommodityID);

    Map<String,Object>AddToLibrary(String BuyerID, String CommodityID);
}
