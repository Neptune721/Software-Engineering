package com.example.demo.Controllers;

import com.example.demo.Services.impl.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/EvaluationService")
public class EvaluationController {
    @Autowired
    EvaluationServiceImpl evaluationService;

    @PostMapping("/AddEvaluation")
    public Map<String,Object> AddEvaluation(String CommodityID,String UserID,String Evaluation)
    {
        Map<String,Object>ReturnInfo=evaluationService.AddEvaluation(CommodityID,UserID,Evaluation);
        return  ReturnInfo;
    }
}
