package com.example.biitdigitallibrarysystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.databinding.ActivityMainBinding;
import com.example.biitdigitallibrarysystem.models.LoginModel;
import com.example.biitdigitallibrarysystem.studentActivites.Student_dashboard;
import com.example.biitdigitallibrarysystem.teacherActivities.Teacher_dashboard;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    //    Button btnlogin;
    ActivityMainBinding binding;
    public static int userid;
    public static int tid, role;
    public static String loginRole="";
    private String userName;
    ArrayList<LoginModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(view -> {
            if (!Objects.requireNonNull(binding.etid.getText()).toString().equals("")) {
                if (!Objects.requireNonNull(binding.etpassword.getText()).toString().equals("")) {

                    String id = binding.etid.getText().toString();
                    String pass = binding.etpassword.getText().toString();
                    Retrofit retrofit = APIClient.getClient();
                    Endpoint endpoint = retrofit.create(Endpoint.class);
                    endpoint.UserLogin(id, pass).enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                            if (response.isSuccessful()) {

                                LoginModel loginModel = response.body();
                                tid = loginModel.getTid();
                                userName = loginModel.getName();
                                if (Objects.equals(loginModel.getRole(), "teacher")) {
                                    loginRole="teacher";
                                    // userid = list.get(0).getSid();
                                    Intent intent = new Intent(MainActivity.this, Teacher_dashboard.class);
                                    intent.putExtra("TName", userName);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                }
                                if (Objects.equals(loginModel.getRole(), "student")) {
                                    loginRole="student";
                                    //userid = list.get(0).getSid();
                                    Intent intent = new Intent(MainActivity.this, Student_dashboard.class);
                                    intent.putExtra("TName", userName);
                                    intent.putExtra("U_ID",loginModel.getReg_no());
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
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
                } else {
                    binding.etpassword.setError("Enter Password");
                }
            } else {
                binding.etid.setError("Enter ID");
            }
        });
    }

    public static int mGetUserID() {
        return userid;
    }
}