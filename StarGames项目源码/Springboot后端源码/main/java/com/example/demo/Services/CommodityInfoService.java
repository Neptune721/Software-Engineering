package com.example.demo.Services;

import java.util.List;
import java.util.Map;

public interface CommodityInfoService {
    Map<String, Object> getCommodityInfo(String CommodityID);

    List getCommodityEvaluation(String CommodityID);
}
