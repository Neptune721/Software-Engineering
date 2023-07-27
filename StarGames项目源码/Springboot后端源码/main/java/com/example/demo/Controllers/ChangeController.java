package com.example.demo.Controllers;

import com.example.demo.Services.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/ChangeService")
public class ChangeController
{
    @Autowired
    ChangeService changeService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostMapping("/changePasswd")
    public int getGameIDForLibrary(String BuyerID,String OldPasswd,String NewPasswd,String Code) {
        int status= changeService.getGameIDForLibrary(BuyerID,OldPasswd,NewPasswd,Code);
        return status;
    }
}
