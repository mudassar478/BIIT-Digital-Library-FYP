package com.example.biitdigitallibrarysystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.databinding.ActivityMainBinding;
import com.example.biitdigitallibrarysystem.models.LoginModel;
import com.example.biitdigitallibrarysystem.studentActivites.Student_dashboard;
import com.example.biitdigitallibrarysystem.teacherActivities.Teacher_dashboard;

import java.util.ArrayList;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
//    Button btnlogin;
    ActivityMainBinding binding;
    public static int userid ;
   public static int tid,role;
    ArrayList<LoginModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Objects.requireNonNull(binding.etid.getText()).toString().equals("")) {
                    if(!Objects.requireNonNull(binding.etpassword.getText()).toString().equals("")) {

                        String id = binding.etid.getText().toString();
                        String pass = binding.etpassword.getText().toString();
                        Retrofit retrofit= APIClient.getClient();
                        Endpoint endpoint= retrofit.create(Endpoint.class);
                        endpoint.UserLogin(id,pass).enqueue(new Callback<LoginModel>() {
                            @Override
                            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                                if (response.isSuccessful()) {

                                    LoginModel loginModel = response.body();
                                    tid=loginModel.getTid();
                                    if (Objects.equals(loginModel.getRole(), "teacher")) {
                                       // userid = list.get(0).getSid();
                                        Intent intent = new Intent(MainActivity.this, Teacher_dashboard.class);
                                        startActivity(intent);
                                    }
                                    if (Objects.equals(loginModel.getRole(), "student")) {
                                        //userid = list.get(0).getSid();
                                        Intent intent = new Intent(MainActivity.this, Student_dashboard.class);
                                        startActivity(intent);
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginModel> call, Throwable t) {

                            }
                        });
//                        if(id.equals("19-Arid-0110") && pass.equals("123123"))
//                        {
//                            Intent i = new Intent(MainActivity.this, Student_dashboard.class);
//                            startActivity(i);
//                        }
//                        if(id.equals("ahsan@biit.edu.pk") && pass.equals("123123"))
//                        {
//                            Intent i = new Intent(MainActivity.this, Teacher_dashboard.class);
//                            startActivity(i);
//                        }
                    }
                    else
                    {
                        binding.etpassword.setError("Enter Password");
                    }
                }
                else
                {
                    binding.etid.setError("Enter ID");
                }
            }
        });
    }
    public static int mGetUserID()
    {
       return userid;
    }
}