package com.example.zomatoclone.Controller;

import com.example.zomatoclone.Model.User;
import com.example.zomatoclone.View.ILoginView;

public class LoginController implements ILoginController{
    ILoginView loginView;

    public LoginController(ILoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String email, String password) {
        User user = new User(email,password);
        int loginCode = user.isValid();

        if(loginCode==0){
            loginView.OnLoginError("Please Enter Email");
        }else if(loginCode == 1){
            loginView.OnLoginError("Please enter a valid email");
        }else if(loginCode==2){
            loginView.OnLoginError("Please enter a password");
        }else if(loginCode==3){
            loginView.OnLoginError("Password should be more than 6 character");
        }else{
            loginView.OnLoginSuccess("Login Success");
        }

    }
}
