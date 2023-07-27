package com.example.demo.Controllers;

import com.example.demo.Services.FriendListService;
import com.example.demo.Services.impl.FriendListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/FriendListService")
public class FriendListController
{
    @Autowired
    FriendListServiceImpl friendListService;

    @GetMapping("/FriendListItems")
    public List<Map<String, Object>> GetListItems(String BuyerID) {
        List<Map<String, Object>> MyList = new ArrayList<>();
        List<String> IDList = friendListService.getFriendIDForList(BuyerID);
        for (int i = 0; i < IDList.size(); i++) {
            String TempID = IDList.get(i);
            Map<String, Object> FriendInfo = friendListService.getFriendInfo(TempID);
            FriendInfo.put("FRIEND_ID", TempID);
            MyList.add(FriendInfo);
        }
        return MyList;
    }

    @GetMapping("/DeleteFriend")
    public boolean DeleteFriend(String BuyerID,String FriendID) {
        return friendListService.deleteFriend(BuyerID,FriendID);
        }
}
