package com.example.demo.Services;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<Map<String,Object>> Search_by_name(String name);

    List<Map<String,Object>> Search_by_friend_name(String BuyerID, String name);
}
