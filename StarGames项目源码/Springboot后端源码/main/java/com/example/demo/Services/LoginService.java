package com.example.demo.Services;

public interface LoginService {
    /*现在手机号UID均可登录！*/
    /*现在手机号UID均可登录！*/
    /*重要的事情说3遍，现在手机号UID均可登录！*/
    int checkIdentity(String UserID, String Password);

    String generateUserID();

    boolean addNewUser(String PhoneNumber, String Password, int UserType);
}
