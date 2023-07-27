package com.example.demo.Services.impl;

import com.example.demo.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;
import java.util.Date;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    public static final int FAILED=-1;
    public static final int SUCCESS=1;
    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/

    @Autowired
    @Resource(name="CommonServices")
    CommonServiceImpl commonService;
    @Override
    public Map<String,Object> AddEvaluation(String CommodityID,String UserID,String Evaluation)
    {
        boolean Has_Evaluated= commonService.Has_Evaluated(CommodityID,UserID);
        Map<String,Object>ReturnInfo= new java.util.HashMap<>(Collections.emptyMap());

        if(Has_Evaluated)
        {
            /*说明该用户已经评测过该游戏*/
            ReturnInfo.put("STATUS",FAILED);
            ReturnInfo.put("REASON","您已经评测过该游戏");
            return  ReturnInfo;
        }
        else
        {
            Date times= new java.sql.Timestamp(new java.util.Date().getTime());
            String Sql ="INSERT INTO EVALUATION (BUYER_ID,COMMODITY_ID,EVALUATION,EVA_TIME) VALUES ('"+UserID+"','"+CommodityID+"','"+Evaluation+"','"+times+"')";

            try
            {
                jdbcTemplate.update(Sql);
            }
            catch (Exception e)
            {
                /*说明此时添加评论失败*/
                ReturnInfo.put("STATUS",FAILED);
                ReturnInfo.put("REASON","数据库更新失败，请检查数据库设置");
                return ReturnInfo;
            }

            /*此时说明添加愿望单到数据库成功*/
            ReturnInfo.put("STATUS",SUCCESS);
            return  ReturnInfo;
        }
    }
}
