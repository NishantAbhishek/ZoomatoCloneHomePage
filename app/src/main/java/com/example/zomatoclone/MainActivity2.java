package com.example.zomatoclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.zomatoclone.Model.Task;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = MainActivity2.class.toString();
    private CompositeDisposable disposable = new CompositeDisposable();
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = findViewById(R.id.text);

        Observable<Task> taskObservable = Observable.
                fromIterable(DataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Throwable {
                        try{
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        return task.isComplete();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>(){

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG,"onsSubscribe: Called");
                disposable.add(disposable);
            }
            @Override
            public void onNext(@NonNull Task task) {
                Log.d(TAG,"onNext:"+Thread.currentThread().getName());
                Log.d(TAG,"onNext:"+task.getDescription());
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"onError "+e);
            }
            @Override
            public void onComplete() {
                Log.d(TAG,"onCompleteCalled: called.");
            }
        });

        disposable.add(taskObservable.subscribe(new Consumer<Task>() {
            @Override
            public void accept(Task task) throws Throwable {

            }
        }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}