package com.example.demo.Services;

import java.util.Map;

public interface EvaluationService {
    Map<String,Object> AddEvaluation(String CommodityID, String UserID, String Evaluation);
}
