package com.example.demo.Controllers;

import com.example.demo.Services.impl.HomeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/HomeService")

public class HomeController {

    @Autowired
    private HomeServiceImpl homeService;

    @GetMapping("/Home")
    public List<Map<String,Object>> ShowShopCommodity() throws Exception{
        List<Map<String, Object>> MyList = new ArrayList<>();
        MyList=homeService.GetRecommendCommodity();
        return MyList;
    }

    @GetMapping("/Home4")
    public List<String> GetGenre(){
        List<String> returnList=new ArrayList<>();
        returnList=homeService.getGenre();
        return returnList;
    }

    @GetMapping("/Home1")
    public List<Map<String,Object>> ShowHotCommodity() throws Exception{
        List<Map<String, Object>> MyList = new ArrayList<>();
        MyList=homeService.GetHotCommodity();
        return MyList;
    }

    @GetMapping("/Home2")
    public List<Map<String,Object>> ShowNewCommodity() throws Exception{
        List<Map<String, Object>> MyList = new ArrayList<>();
        MyList=homeService.GetNewCommodity();
        return MyList;
    }

    @GetMapping("/Home3")
    public List<Map<String,Object>> ShowCommodityClassification(String Genre_Name){
        List<Map<String, Object>> MyList = new ArrayList<>();
        MyList=homeService.GetCommodityClassification(Genre_Name);
        return MyList;
    }
}
