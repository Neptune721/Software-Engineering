package com.example.demo.Controllers;

import com.example.demo.Services.impl.CommodityInfoServiceImpl;
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
@RequestMapping("/api/v1/CommodityInfoService")
public class CommodityInfoController {
    public static final int FAILED=-1;
    public static final int SUCCESS=1;
    @Autowired
    CommodityInfoServiceImpl commodityInfoService;

    /*得到CommodityID所对应的商品详情信息*/
    @GetMapping("/CommodityInfo")
    public Map<String, Object> GetCommodityInfo(String CommodityID) {
        return commodityInfoService.getCommodityInfo(CommodityID);
    }

    /*暂未完善，在CommodityID所对应商品不存在时可能报错(maybe)*/
    @GetMapping("/CommodityEvaluation")
    public List<Map<String, Object>> GetCommodityEvaluation(String CommodityID) {
        return commodityInfoService.getCommodityEvaluation(CommodityID);
    }

    /*得到CommodityID所对应的商品详情和用户评价信息*/
    @GetMapping("/CommodityFullInfo")
    public Map<String, Object> GetCommodityFullInfo(String CommodityID) {

        Map<String, Object> CommodityInfo=commodityInfoService.getCommodityInfo(CommodityID);

        /*判断该CommodityID所对应的商品是否存在，若不存在则直接返回，若存在则继续查询该游戏对应的评价*/
        Object status=CommodityInfo.get("STATUS");
        if(status instanceof Integer)
        {
            Integer Status=(Integer)status;

            if(Status==FAILED)
                return CommodityInfo;
        }
        List<Map<String, Object>> CommodityEvaluation = commodityInfoService.getCommodityEvaluation(CommodityID);
        CommodityInfo.put("EVALUATION",CommodityEvaluation);

        return CommodityInfo;
    }
}
