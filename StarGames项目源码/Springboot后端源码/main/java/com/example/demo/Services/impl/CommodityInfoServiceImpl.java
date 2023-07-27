package com.example.demo.Services.impl;

import com.example.demo.Services.CommodityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;
import java.util.List;
@Service
public class CommodityInfoServiceImpl implements CommodityInfoService {
    public static final int FAILED=-1;
    public static final int SUCCESS=1;
    @Autowired
    @Resource(name="CommonServices")
    CommonServiceImpl commonService;
    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/


    /**
     * 基本的查询函数，实现一个最基本的查询功能
     * 函数名称:getBuyerInfo()
     * 功能:根据参数BUYER_ID，在数据库USERS和BUYERS表中查询该用户的各项信息
     * 返回值:Map，类似于JSON文件，这种数据类型前端vue可以读取
     */
    @Override
    public Map<String, Object> getCommodityInfo(String CommodityID) {

        /*首先判断CommodityID是否正确*/
        if(!commonService.Is_Commodity_Exists(CommodityID)){
            Map<String,Object>CommodityInfo= new java.util.HashMap<>(Collections.emptyMap());
            CommodityInfo.put("STATUS",FAILED);
            return  CommodityInfo;
        }

        /*用一个字符串存储SQL语句，?代表的是参数，在查询的时候通过传参数实现*/
        String sql = "SELECT COMMODITY_ID,COMMODITY_NAME,PRICE,PUBLISH_TIME,DESCRIPTION,PICTURE_URL,DOWNLOAD_URL FROM COMMODITIES WHERE COMMODITY_ID=?";

        /*queryForMap可以在数据库中查出Map类型的数据，第一个参数放SQL语句字符串，第二个参数放参数，即SQL语句中用?表示的部分*/

        Map<String, Object> CommodityInfo = jdbcTemplate.queryForMap(sql,CommodityID);
        CommodityInfo.put("STATUS",SUCCESS);

        return CommodityInfo;
    }
    @Override
    public List getCommodityEvaluation(String CommodityID)
    {

        String sql= "SELECT  EVALUATION.BUYER_ID,EVALUATION,EVA_TIME,BUYER_NAME FROM EVALUATION LEFT OUTER JOIN BUYERS ON EVALUATION.BUYER_ID=BUYERS.BUYER_ID WHERE EVALUATION.COMMODITY_ID=?";

        List<Map<String, Object>> CommodityEvaluation=jdbcTemplate.queryForList(sql,CommodityID);

        return  CommodityEvaluation;
    }
}
