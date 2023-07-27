package com.example.demo.Services;

import java.sql.Date;
import java.util.Map;

public interface BuyerInfoService {
    Map<String, Object> getBuyerInfo(String BuyerID);

    boolean changeBuyerInfo(String BuyerID, String BuyerName, String PhoneNumber, String Mail, String Address, Date birthday);
}
