/*
 * Controller 控制器层，Controller负责给前端返回数据的以及接收前端的数据
 * package 在文件的首行 表示这个文件位于哪个软件包中
 * */
package com.example.demo.Controllers;

import com.example.demo.Models.BuyerInfo;
import com.example.demo.Services.impl.BuyerInfoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/BuyerInfoService")
public class BuyerInfoController {
    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/
    @Autowired
    private BuyerInfoServiceImpl buyerInfoService;

    @GetMapping("/BuyerInfo")
    public Map<String, Object> GetBuyerInfo(String BuyerID) {
        Map<String, Object> UserInfo = buyerInfoService.getBuyerInfo(BuyerID);

        return UserInfo;
    }

    @PutMapping("/BuyerInfo")
    public boolean ChangeBuyerInfo(@RequestBody BuyerInfo buyerInfo){

        buyerInfoService.changeBuyerInfo(buyerInfo.BuyerID, buyerInfo.BuyerName,buyerInfo.PhoneNumber, buyerInfo.Mail,buyerInfo.Address, buyerInfo.birthday);
        return true;
    }
    @PostMapping("/BuyerAvatar")
    public boolean changeBuyerAvatar(String BuyerID,String url)
    {
        String sql = "UPDATE BUYERS SET Avatar = '"+url+"' WHERE BUYER_ID = '"+BuyerID+"'";
        jdbcTemplate.update(sql);
        return true;
    }
}
