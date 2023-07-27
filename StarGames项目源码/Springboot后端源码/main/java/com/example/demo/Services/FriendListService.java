package com.example.demo.Services;

import java.util.List;
import java.util.Map;

public interface FriendListService {
    List<String> getFriendIDForList(String BuyerID);

    Map<String,Object> getFriendInfo(String FRIEND_ID);

    boolean deleteFriend(String BuyerID, String FriendID);
}
