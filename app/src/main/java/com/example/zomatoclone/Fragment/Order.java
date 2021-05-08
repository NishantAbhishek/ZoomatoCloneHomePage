package com.example.zomatoclone.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zomatoclone.Adapter.HomeAdapter;
import com.example.zomatoclone.LocationBottomSheet;
import com.example.zomatoclone.Model.RestaurantItem;
import com.example.zomatoclone.R;

import java.util.ArrayList;

public class Order extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    private TextView placeSearch;
    private FragmentActivity myContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        initializeRecycler(view);

        inititlizeViews(view);
        return view;
    }

    public void inititlizeViews(View view){
        placeSearch = view.findViewById(R.id.placeSearch);
        placeSearch.setOnClickListener(this::onClick);
    }

    private void initializeRecycler(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        ArrayList<RestaurantItem> items = new ArrayList<>();
        items.add(new RestaurantItem(R.drawable.a,"4.0","Five Star Chicken","Fast Food","₹170 per person | 30 minutes","Closes in 50 Minutes"));
        items.add(new RestaurantItem(R.drawable.b,"3.0","Gold Rated Chicken","Fast Food","₹50 per person | 45 minutes","Closes in 30 Minutes"));
        items.add(new RestaurantItem(R.drawable.a,"2.0","Pizza Corner","Food","₹10 per person | 20 minutes","Closes in 30 Minutes"));
        items.add(new RestaurantItem(R.drawable.b,"5.0","Food Corner Chicken","Dinner","₹150 per person | 10 minutes","Closes in 30 Minutes"));
        items.add(new RestaurantItem(R.drawable.a,"4.0","Orange Juice","Fast Food","₹250 per person | 16 minutes","Closes in 10 Minutes"));
        items.add(new RestaurantItem(R.drawable.a,"3.0","Five Star Fish","Fast Food","₹150 per person | 50 minutes","Closes in 30 Minutes"));
        items.add(new RestaurantItem(R.drawable.b,"3.0","Star Chicken","Fast Food","₹10 per person | 43 minutes","Closes in 15 Minutes"));
        items.add(new RestaurantItem(R.drawable.a,"2.0","Birthday Cake","Fast Food","₹90 per person | 20 minutes","Closes in 10 Minutes"));
        HomeAdapter adapter = new HomeAdapter(items,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        myContext = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.placeSearch:
                LocationBottomSheet bottomSheet = new LocationBottomSheet();
                bottomSheet.show(myContext.getSupportFragmentManager(),"LocationBottomSheet");
                break;

        }
    }
}