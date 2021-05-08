package com.example.zomatoclone;
import com.example.zomatoclone.Model.Task;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static List<Task> createTasksList(){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Take out the trash",true,3));
        tasks.add(new Task("Walk the dog",false,2));
        tasks.add(new Task("Make my bed",true,0));
        tasks.add(new Task("Upload the dishwasher",true,3));
        tasks.add(new Task("Make dinner",true,5));
        return tasks;
    }
}
