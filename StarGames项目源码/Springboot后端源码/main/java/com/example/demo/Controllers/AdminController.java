package com.example.demo.Controllers;
import com.example.demo.Services.impl.AdminServiceImpl;
import com.example.demo.Services.BuyerInfoService;

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
@RequestMapping("/api/v1/AdminService")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    BuyerInfoService buyerInfoService;

    @GetMapping("/BuyerListItems")
    public List<Map<String, Object>> GetBuyerListItems() {
        List<Map<String, Object>> MyList = new ArrayList<>();
        List<String> IDList = adminService.getBuyerIDForList();
        for (String TempID : IDList) {
            Map<String, Object> BuyerInfo = buyerInfoService.getBuyerInfo(TempID);
            MyList.add(BuyerInfo);
        }
        return MyList;
    }
    @GetMapping("/QueryBuyer")
    public List<Map<String, Object>> QueryBuyerItem(String BuyerID) {
        List<Map<String, Object>> MyList = new ArrayList<>();
        Map<String, Object> BuyerInfo = buyerInfoService.getBuyerInfo(BuyerID);
        MyList.add(BuyerInfo);
        return MyList;
    }

    @GetMapping("/BlockBuyer")
    public boolean BlockBuyer(String BuyerID) {
        return adminService.blockBuyer(BuyerID);
    }
    @GetMapping("/CommodityListItems")
    public List<Map<String, Object>> GetCommodityListItems() {
        List<Map<String, Object>> MyList = new ArrayList<>();
        List<String> IDList = adminService.getCommoditiesIDForList();
        for (String TempID : IDList) {
            Map<String, Object> Info = adminService.getCommodityInfo(TempID);
            MyList.add(Info);
        }
        return MyList;
    }

    @GetMapping("/QueryCommodity")
    public List<Map<String, Object>> QueryCommodityItem(String CommodityID) {
        List<Map<String, Object>> MyList = new ArrayList<>();
        Map<String, Object> Info = adminService.getCommodityInfo(CommodityID);
        MyList.add(Info);
        return MyList;
    }
    @GetMapping("/WithdrawCommodity")
    public boolean WithdrawCommodity(String CommodityID) {
        return adminService.withdrawCommodity(CommodityID);
    }
}
