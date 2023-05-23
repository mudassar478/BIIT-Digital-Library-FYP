package com.example.biitdigitallibrarysystem.apiServices;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.FileUtil;
import com.example.biitdigitallibrarysystem.models.Item;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.LoginModel;
import com.example.biitdigitallibrarysystem.models.TableOfContent;
import com.example.biitdigitallibrarysystem.models.WeekNumModel;
import com.example.biitdigitallibrarysystem.models.WeeksModel;
import com.google.gson.JsonArray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Endpoint {
    @GET("Login/UserLogin")
   Call<LoginModel> UserLogin(@Query("user")String user, @Query("password")String password);

    @GET("TeacherCourse/TeacherCourse")
    Call<JsonArray> TeacherCourses(@Query("tid") int tid);

//    @GET("TeacherCourse/TeacherFetchLessonplan")
//    Call<ArrayList<WeekNumModel>>TeacherFetchLessonplan(@Query("cid")int cid);


    @GET("TeacherCourse/TeacherFetchWeekAgainstCourse")
    Call<List<String>>TeacherFetchWeekAgainstCourse(@Query("cid")int cid, @Query("tid")int tid);


    @GET("StudentLogs/FetchSection")
    Call<JsonArray> fetchSection(@Query("tid")int tid);

    @POST("TeacherCourse/TeacherUploadLessonPlan")
    Call<ResponseBody> teacherUpload(@Query("tid") int tid,@Query("cid") int cid,@Query("title") String title,@Query("week") String week);

    @GET("TeacherBook/TeacherFetchLibraryBook")
    Call<ArrayList<LibraryBook>> fetchLibraryBooks();

    @GET("TeacherBook/SearchLibarryBook")
    Call<ArrayList<LibraryBook>> SearchLibarryBook(@Query("libraryseach") String libraryseach);

 @GET("TeacherBook/TeacherFetchTableofContent")
 Call<JsonArray> TeacherFetchTableofContent(@Query("bid") int bid);

 @GET("TeacherBook/TeacherFetchLessonPlanAgainstWeek")
 Call<JsonArray> TeacherFetchLessonPlanAgainstWeek(@Query("cid") int cid, @Query("tid") int tid,@Query("week") String week );


 @Multipart
 @POST("TeacherBook/TeacherUploadBook")
 public Call<String>TeacherUploadBook
         (
                 @Part ArrayList<MultipartBody.Part> imagefile,
                 @Part ArrayList<MultipartBody.Part> bookfile,
                 @Part("tid") RequestBody tid,
                 @Part("sourcerole") RequestBody sourcerole,
                 @Part("title") RequestBody title,
                 @Part("author") RequestBody author,
                 @Part("publisher") RequestBody publisher,
                 @Part("isbn") RequestBody isbn,
                 @Part("abstractt") RequestBody abstractt,
                 @Part("table_of_content") List<TableOfContent> list
         );




 @NonNull
 public default MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context) throws IOException {
  File file = FileUtil.from(context, fileUri);
  RequestBody requestFile = RequestBody.create(MediaType.parse(context.getContentResolver().getType(fileUri)), file
  );
  return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
 }
 public default RequestBody createPartFromString(String descriptionString){
  RequestBody description =
          RequestBody.create(
                  okhttp3.MultipartBody.FORM, descriptionString);
  return  description;

 }
}
