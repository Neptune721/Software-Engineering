package com.example.demo.Services;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<String> getBuyerIDForList();

    boolean blockBuyer(String BuyerID);

    List<String> getCommoditiesIDForList();

    Map<String, Object> getCommodityInfo(String CommodityID);

    boolean withdrawCommodity(String CommodityID);
}
