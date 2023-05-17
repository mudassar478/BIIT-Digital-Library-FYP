package com.example.biitdigitallibrarysystem.apiServices;

import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.LoginModel;
import com.example.biitdigitallibrarysystem.models.WeekNumModel;
import com.example.biitdigitallibrarysystem.models.WeeksModel;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Endpoint {
    @GET("Login/UserLogin")
   Call<LoginModel> UserLogin(@Query("user")String user, @Query("password")String password);

    @GET("TeacherCourse/TeacherCourse")
    Call<JsonArray> TeacherCourses(@Query("tid") int tid);

//    @GET("TeacherCourse/TeacherFetchLessonplan")
//    Call<ArrayList<WeekNumModel>>TeacherFetchLessonplan(@Query("cid")int cid);


    @GET("TeacherCourse/TeacherFetchLessonplan")
    Call<ArrayList<WeekNumModel>>TeacherFetchLessonplan(@Query("cid")int cid,@Query("tid")int tid);




    @GET("StudentLogs/FetchSection")
    Call<JsonArray> fetchSection(@Query("tid")int tid);

    @POST("TeacherCourse/TeacherUploadLessonPlan")
    Call<ResponseBody> teacherUpload(@Query("tid") int tid,@Query("cid") int cid,@Query("title") String title,@Query("week") String week);

    @GET("TeacherBook/TeacherFetchLibraryBook")
    Call<ArrayList<LibraryBook>> fetchLibraryBooks();
}
