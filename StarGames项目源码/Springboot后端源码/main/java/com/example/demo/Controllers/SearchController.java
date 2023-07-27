package com.example.demo.Controllers;

import com.example.demo.Services.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/SearchService")
public class SearchController {

    @Autowired
    SearchServiceImpl searchService;

    @GetMapping("/SearchByName")
    public List<Map<String,Object>>Search_By_name(String name)
    {
        List<Map<String,Object>>CommodityInfo=searchService.Search_by_name(name);
        if(CommodityInfo!=null)
            return  CommodityInfo;
        else
        {
            List<Map<String, Object>> commodityInfo = new ArrayList<>();
            Map<String,Object>Info= new java.util.HashMap<>(Collections.emptyMap());
            Info.put("STAUTS",-1);
            commodityInfo.add(Info);
            return commodityInfo;
        }
    }
    @GetMapping("/SearchByFriendName")
    public List<Map<String,Object>>Search_By_Friend_name(String BuyerID,String name)
    {
        List<Map<String,Object>>FriendInfo=searchService.Search_by_friend_name(BuyerID,name);
        return  FriendInfo;
    }
}
