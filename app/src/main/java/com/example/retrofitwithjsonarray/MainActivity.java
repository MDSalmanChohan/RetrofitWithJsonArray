package com.example.retrofitwithjsonarray;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // creating a variable for recycler view,
    // array list and adapter class.
    private RecyclerView courseRV;
    private List<Post> recyclerDataArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourse);
        progressBar = findViewById(R.id.idPBLoading);

        // creating new array list.
        recyclerDataArrayList = new ArrayList<>();

        // calling a method to
        // get all the courses.
        getAllCourses();
    }

    private void getAllCourses() {
        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                // on below line we are calling add
                // Converter factory as Gson converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // on below line we are calling a method to get all the courses from API.
        Call<RecyclerData> call = retrofitAPI.getAllCourses();

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<RecyclerData>() {
            @Override
            public void onResponse(Call<RecyclerData> call, Response<RecyclerData> response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {

                    // on successful we are hiding our progressbar.
                    progressBar.setVisibility(View.GONE);

                    Log.d("dfcvgnjjjk ", "onResponse: "+response.body().getPosts());


                    recyclerDataArrayList.addAll(response.body().getPosts());


                    recyclerViewAdapter = new RecyclerViewAdapter(recyclerDataArrayList, MainActivity.this);

                    // below line is to set layout manager for our recycler view.
                    LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);

                    // setting layout manager for our recycler view.
                    courseRV.setLayoutManager(manager);

                    // below line is to set adapter to our recycler view.
                    courseRV.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<RecyclerData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("SalmanTesting ", "onFailure: " + call.toString());
                Log.d("SalmanTesting ", "onFailure: " + t.toString());

                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(MainActivity.this, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
