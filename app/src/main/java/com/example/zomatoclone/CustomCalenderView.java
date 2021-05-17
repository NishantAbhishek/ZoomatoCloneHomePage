package com.example.zomatoclone;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

public class CustomCalenderView extends LinearLayout {
    GridAdapter gridAdapter;
    Button nextButton,prvButton;
    TextView currentDate;
    GridView gridView;
    private static final int MAX_CALENDER_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy",Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM",Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY",Locale.ENGLISH);

    private DBOpenHelper dbOpenHelper;

    AlertDialog alertDialog;

    List<Date> dates = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();


    public CustomCalenderView(Context context) {
        super(context);
        this.context = context;
    }

    public CustomCalenderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        InitializeLayout();
        prvButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,-1);
                SetUpCalender();
            }
        });


        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                SetUpCalender();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);
                View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_new_event_layout,null);
                EditText eventName = addView.findViewById(R.id.event_name);
                EditText eventTime = addView.findViewById(R.id.eventTime);
                ImageButton setTime = addView.findViewById(R.id.setEventButton);
                Button addEvent = addView.findViewById(R.id.addevent);

                setTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        Calendar c = Calendar.getInstance();
                                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                        c.set(Calendar.MINUTE,minute);
                                        c.setTimeZone(TimeZone.getDefault());
                                        SimpleDateFormat hformate = new SimpleDateFormat("K:mm a",Locale.ENGLISH);
                                        String time = hformate.format(c.getTime());
                                        eventTime.setText(time);

                                    }
                                },hours,minutes,false);

                        timePickerDialog.show();
                    }
                });

                final String date = dateFormat.format(dates.get(position));
                final String month = monthFormat.format(dates.get(position));
                final String year = yearFormat.format(dates.get(position));


                addEvent.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveEvent(eventName.getText().toString(),eventTime.getText().toString(),date,month,year);
                        SetUpCalender();
                        alertDialog.dismiss();

                    }
                });


            }
        });
    }


    private void saveEvent(String event,String time,String date,String month,String year){
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(event,time,date,month,year,database);
        Log.e(TAG,"Event Saved");
    }

    public CustomCalenderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }

    private void InitializeLayout(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.calender_layout,this);
        View view = inflate(context,R.layout.calender_layout,this);

        nextButton = view.findViewById(R.id.nextBtn);

        prvButton = view.findViewById(R.id.previousBtn);

        currentDate = view.findViewById(R.id.currentDate);
        gridView = view.findViewById(R.id.gridView);
        SetUpCalender();

    }


    private static final String TAG = "CustomCalenderView";


    //remeber additon and subtraction fllows normal calender rule
    private void SetUpCalender(){
        String currentDate_ = dateFormat.format(calendar.getTime());
        currentDate.setText(currentDate_);
        dates.clear();//clearing the list so that there will be new list added
        Calendar monthCalender = (Calendar) calendar.clone();//creating a new Object of Calender which is same as the instance calender object
        monthCalender.set(Calendar.DAY_OF_MONTH,1);//setting the date as 1
        int FirstDayOfMonth = monthCalender.get(Calendar.DAY_OF_WEEK)-1;//returns the day of weak of current month
        Log.e(TAG, "SetUpCalender: "+monthCalender.get(Calendar.DAY_OF_WEEK));
        Log.e(TAG, "SetUpCalender: "+monthCalender.get(Calendar.DAY_OF_MONTH));
        Log.e(TAG, "SetUpCalender: "+monthCalender.get(Calendar.MONTH));
        monthCalender.add(Calendar.DAY_OF_MONTH,-FirstDayOfMonth);

        //filling the 7 columns and 6 rows and 7 columns of calender view and storing them inside the dates list
        while (dates.size()<MAX_CALENDER_DAYS){
            Log.e("date",monthCalender.get(Calendar.DAY_OF_MONTH)+"");
            dates.add(monthCalender.getTime());
            monthCalender.add(Calendar.DAY_OF_MONTH,1);//incrementing day by 1 after storing it
        }
        gridAdapter = new GridAdapter(context,dates,eventsList,calendar);
        gridView.setAdapter(gridAdapter);


    }

}
