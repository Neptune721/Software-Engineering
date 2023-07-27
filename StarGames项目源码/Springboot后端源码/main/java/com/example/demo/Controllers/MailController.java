package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("/api/v1/test")
public class MailController {
    @Autowired
    private JdbcTemplate jdbcTemplate;/*初始化JDBC，以进行数据库的查询*/
    //引用邮箱发送
@Autowired
    JavaMailSenderImpl mailSender;


    @RequestMapping("/getCheckCode")
    @ResponseBody
    public String getCheckCode(@RequestParam String email) {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成六位随机数
        System.out.println(checkCode);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");
        message.setText("验证码是：" + checkCode+" ，请在一分钟内使用");
        message.setTo(email);
        message.setFrom("1061633882@qq.com");
        mailSender.send(message);
        String sql_1 = "UPDATE BUYERS SET MAIL_CODE='"+checkCode+"'WHERE MAIL='"+email+"'";
        jdbcTemplate.update(sql_1);
        return checkCode;

    }
}

