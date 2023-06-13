package com.example.biitdigitallibrarysystem.apiServices;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.biitdigitallibrarysystem.models.FileUtil;
import com.example.biitdigitallibrarysystem.models.LessonPlanModel;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.LoginModel;
import com.example.biitdigitallibrarysystem.models.ReferencesModel;
import com.example.biitdigitallibrarysystem.models.TableOfContent;
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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Endpoint {


    @GET("Login/UserLogin")
    Call<LoginModel> UserLogin(@Query("user") String user, @Query("password") String password);

    @GET("TeacherCourse/TeacherCourse")
    Call<JsonArray> TeacherCourses(@Query("tid") int tid);

//    @GET("TeacherCourse/TeacherFetchLessonplan")
//    Call<ArrayList<WeekNumModel>>TeacherFetchLessonplan(@Query("cid")int cid);


    @GET("TeacherCourse/TeacherFetchWeekAgainstCourse")
    Call<List<String>> TeacherFetchWeekAgainstCourse(@Query("cid") int cid, @Query("tid") int tid);

    @GET("StudentCourse/StudentFetchWeekAgainstLessonplan")
    Call<List<String>> studentFetchWeekAgainstCourse(@Query("cid") int cid, @Query("sid") int sid);

    @GET("StudentLogs/FetchSection")
    Call<JsonArray> fetchSection(@Query("tid") int tid);

    @GET("StudentLogs/FetchStudentAgainstSection")
    Call<JsonArray> FetchStudent(@Query("tid") int tid, @Query("section") String section);


//    @POST("TeacherCourse/TeacherUploadLessonPlan")
//    Call<ResponseBody> teacherUpload(@Query("tid") int tid, @Query("cid") int cid, @Query("title") String title, @Query("week") String week);

    @GET("TeacherBook/TeacherFetchLibraryBook")
    Call<ArrayList<LibraryBook>> fetchLibraryBooks();

    @GET("TeacherBook/TeacherFetchOwnBooks")
    Call<ArrayList<LibraryBook>> fetchOwnBooks(@Query("tid") int tid);

    @GET("TeacherBook/SearchLibarryBook")
    Call<ArrayList<LibraryBook>> SearchLibarryBook(@Query("libraryseach") String libraryseach);

    @GET("TeacherBook/TeacherFetchTableofContent")
    Call<JsonArray> TeacherFetchTableofContent(@Query("bid") int bid);

    @GET("TeacherCourse/TeacherFetchLessonPlanAgainstWeek")
    Call<JsonArray> TeacherFetchLessonPlanAgainstWeek(@Query("cid") int cid, @Query("tid") int tid, @Query("week") String week);

    @GET("StudentCourse/StudentFetchLessonplanAgainstWeek")
    Call<JsonArray> studentFetchLessonPlanAgainstWeek(@Query("cid") int cid, @Query("sid") int sid, @Query("week") String week);


    @GET("TeacherCourse/TeacherFetchRefrences")
    Call<ArrayList<ReferencesModel>> teacherfetchrefrences(@Query("sourceid") int sourceid, @Query("lid") int lid, @Query("sourcename") String sourcename);


    @GET("TeacherCourse/TeacherDeleteLessonPlan")
    Call<ResponseBody> TeacherDeleteLessonPlan(@Query("lid") int lid);

    @GET("TeacherCourse/TeacherDeleteRefrences")
    Call<ResponseBody> TeacherDeleteRefrences(@Query("id") int id);

    @POST("TeacherCourse/TeacherAddRefrences")
    Call<ResponseBody> teacheraddrefrences(@Body ReferencesModel r);



    @GET("TeacherCourse/TeacherFetchRefrences")
    Call<ArrayList<LessonPlanModel>> TeacherFetchRefrences(@Query("sourceid") int sourceid, @Query("lid") int lid, @Query("sourcename") String sourcename);


    @GET("StudentCourse/Course")
    Call<JsonArray> Course(@Query("sid") int sid);


    @Multipart
    @POST("TeacherBook/TeacherUploadBook")
    public Call<String> TeacherUploadBook
            (
                    @Part MultipartBody.Part image,
                    @Part MultipartBody.Part book,
                    @Part("tid") RequestBody tid,
                    @Part("sourcerole") RequestBody sourcerole,
                    @Part("Title") RequestBody title,
                    @Part("Author") RequestBody author,
                    @Part("Publisher") RequestBody publisher,
                    @Part("ISBN") RequestBody isbn,
                    @Part("abstractt") RequestBody abstractt

            );

    @NonNull
    public default MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context) throws IOException {
        File file = FileUtil.from(context, fileUri);
        RequestBody requestFile = RequestBody.create(MediaType.parse(context.getContentResolver().getType(fileUri)), file
        );
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    public default RequestBody createPartFromString(String descriptionString) {
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);
        return description;

    }


    @Multipart
    @POST("TeacherCourse/TeacherUploadLessonPlan")
    public Call<String> teacherUploadLessonPlan
            (

                    @Part MultipartBody.Part file,
                    @Part("tid") RequestBody tid,
                    @Part("cid") RequestBody cid,
                    @Part("title") RequestBody title,
                    @Part("week") RequestBody week

            );

    @POST("TeacherBook/TeacherUploadTableofContent")
    Call<String> TeacherUploadTableofContent(@Query("bid") int bid, @Query("title") String title, @Query("pageno") String pageno, @Query("keywords")String keywords);


}
