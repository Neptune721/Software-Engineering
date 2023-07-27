package com.example.demo.Services;

import java.util.List;
import java.util.Map;

public interface HomeService {
    List<Map<String, Object>> GetRecommendCommodity() throws Exception;

    List<String> getGenre();

    List<Map<String, Object>> GetHotCommodity() throws Exception;

    List<Map<String, Object>> GetNewCommodity() throws Exception;

    List<Map<String,Object>> GetCommodityClassification(String Genre);
}
