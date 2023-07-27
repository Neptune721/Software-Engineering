package com.example.demo.Controllers;

import com.example.demo.Services.impl.CommodityInfoServiceImpl;
import com.example.demo.Services.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/CommonService")
public class CommonController {
    @Autowired
    CommonServiceImpl commonService;

    @GetMapping("/CanPurchase")
    public boolean CanPurchase(String CommodityID,String UserID)
    {
        boolean result=true;
        result=commonService.Is_Commodity_In_GameLibrary(CommodityID,UserID);
        return  result;
    }
}
