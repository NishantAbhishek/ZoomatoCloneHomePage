package com.example.zomatoclone.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zomatoclone.Controller.ILoginController;
import com.example.zomatoclone.Controller.LoginController;
import com.example.zomatoclone.R;

public class LoginMVC extends AppCompatActivity implements ILoginView{
    EditText email,password;
    Button loginButton;

    ILoginController loginController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_m_v_c);

        loginController = new LoginController(this);
        //get text from email and password and sens them to loginCONTEOLER

    }

    @Override
    public void OnLoginSuccess(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
    
    @Override
    public void OnLoginError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}