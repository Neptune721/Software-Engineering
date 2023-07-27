package com.example.demo.Controllers;

import com.example.demo.Services.impl.LoginServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/v1/LoginService")
public class LoginController
{
    @Autowired
    LoginServiceImpl loginService;
    @PostMapping("/Login")
    public int checkIdentity(String UserID, String Password) {

        /*现在手机号UID均可登录！*/
        /*现在手机号UID均可登录！*/
        /*重要的事情说3遍，现在手机号UID均可登录！*/

        /*调用Service中的函数完成数据查询*/
        int status = loginService.checkIdentity(UserID, Password);

        /*
         * 返回值 status 的含义：
         * 若返回值为-1，则账号密码错误
         * 若返回值为0，以学生身份登录
         * 若返回值为1，以教师身份登录
         * 若返回值为2，以管理员身份登录
         * */
        return status;
    }

    @PostMapping("/Register")
    public boolean register(String PhoneNumber,String Password,int UserType) {

        /*现在手机号UID均可登录！*/
        /*现在手机号UID均可登录！*/
        /*重要的事情说3遍，现在手机号UID均可登录！*/

        /*调用Service中的函数完成数据查询*/
        boolean status = loginService.addNewUser(PhoneNumber, Password,UserType);

        /*
         * 返回值 status 的含义：
         * 若返回值为-1，则账号密码错误
         * 若返回值为0，以学生身份登录
         * 若返回值为1，以教师身份登录
         * 若返回值为2，以管理员身份登录
         * */
        return status;
    }
}
