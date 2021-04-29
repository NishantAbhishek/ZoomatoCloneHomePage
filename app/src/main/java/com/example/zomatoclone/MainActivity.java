package com.example.zomatoclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.zomatoclone.Fragment.Donate;
import com.example.zomatoclone.Fragment.Go_Out;
import com.example.zomatoclone.Fragment.Order;
import com.example.zomatoclone.Fragment.Pro;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selectFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(itemSelectectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Order()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_order:
                    selectFragment = new Order();
                    break;
                case R.id.nav_goout:
                    selectFragment = new Go_Out();
                    break;
                case R.id.nav_pro:
                    selectFragment = new Pro();
                    break;
                case R.id.nav_donate:
                    selectFragment = new Donate();
                    break;
            }
            if(selectFragment!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();
            }
            return true;
        }
    };
}