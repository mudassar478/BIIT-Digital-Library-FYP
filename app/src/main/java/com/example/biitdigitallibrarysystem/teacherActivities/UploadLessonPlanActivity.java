package com.example.biitdigitallibrarysystem.teacherActivities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.GlobalData;
import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.CourseTAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UploadLessonPlanActivity extends AppCompatActivity {
    Button btnsave , btnchoose ;
    int tid  ,courseid;
    String title , week;
    boolean chossefile=false;
    private  Uri fileuri;
    final int GALLERY_REQUEST_CODE = 2;
    final int CAMERA_REQUEST_CODE = 1;
    APIClient client = APIClient.getInstance();
    Endpoint api;
    public ArrayList<MultipartBody.Part> fileList = new ArrayList<>();
    public ArrayList<MultipartBody.Part> imageList = new ArrayList<>();
    boolean chooseimage=false;
    EditText lptittle;
    Spinner spinner;
    TextView textView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api= client.getMyApi();
        setContentView(R.layout.upload_lesson_plan);
        btnsave = findViewById(R.id.save_lessonplan);
        btnchoose = findViewById(R.id.btnCHooseFile);
        textView=findViewById(R.id.lessonplannamelabel);
        lptittle=findViewById(R.id.txtViewTitle);
        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chossefile=true;
                Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent,100);
            }
        });
        Intent intent = getIntent();
        tid = intent.getIntExtra("tid",0);
        courseid = intent.getIntExtra("cid",0);

        //spinner code
        List<String> items;
        spinner = findViewById(R.id.spinnerUploadLesson);
        items = new ArrayList<>();

        items.add("General");
        items.add("Week 1");
        items.add("Week 2");
        items.add("Week 3");
        items.add("Week 4");
        items.add("Week 5");
        items.add("Week 6");
        items.add("Week 7");
        items.add("Week 8");
        items.add("Week 9");
        items.add("Week 10");
        items.add("Week 11");
        items.add("Week 12");
        items.add("Week 13");
        items.add("Week 14");
        items.add("Week 15");
        items.add("Week 16");


        spinner.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items));

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                registerUser();

            }
        });


    }

    public void registerUser() {



        saveFile();

        int tids= MainActivity.tid;
//        int cids=0;
//        if (getIntent().getIntExtra("cid",0)!=0){
//            cids=getIntent().getIntExtra("cid",0);
//        }
        String titlev=lptittle.getText().toString();
        String email=textView.getText().toString();
        String number=spinner.getSelectedItem().toString();

        courseid= CourseTAdapter.idc;


        File file= new File(fileuri.toString());
        RequestBody photoContent= RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //image  send body object create
        MultipartBody.Part image2 = null;
        try {
             image2 = api.prepareFilePart("file",fileUpload , UploadLessonPlanActivity.this);
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


        api.teacherUploadLessonPlan(image2,api.createPartFromString(tids+""),api.createPartFromString(courseid+""),api.createPartFromString(titlev+""),api.createPartFromString(number+"")).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
             //   Toast.makeText(UploadLessonPlanActivity.this, "uuu"+response.code(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    Toast.makeText(UploadLessonPlanActivity.this, "Lesson Uploaded"+response.body(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(UploadLessonPlanActivity.this, "Issue : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(UploadLessonPlanActivity.this, "Masla : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String saveFile(){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String fname = sdf.format(new Date());
        File file = new File(directory, fname + ".pdf");
        if(file.exists())
            file.delete();


        fileuri= Uri.parse(file.toString());
        Log.d("path", file.toString());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
//            photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return file.toString();
    }
Uri fileUpload=null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 ){
            Uri uri=data.getData();
            String filepath=uri.getPath();
            fileUpload= data.getData();

            Cursor cursor=getContentResolver().query(uri,null,null,null,null);
            if (cursor!=null && cursor.moveToFirst()){
                @SuppressLint("Range") String filename=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                textView.setText(filename);

            }
        }
    }
}