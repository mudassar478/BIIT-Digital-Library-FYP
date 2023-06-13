package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.databinding.UploadBookBinding;
import com.example.biitdigitallibrarysystem.models.TableOfContent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadBook extends AppCompatActivity {
   UploadBookBinding binding;
    public ArrayList<MultipartBody.Part> imageList = new ArrayList<>();
    public ArrayList<MultipartBody.Part> fileList = new ArrayList<>();
    List<TableOfContent> list = new ArrayList<>();
    final int CAMERA_REQUEST_CODE = 1;
    final int GALLERY_REQUEST_CODE = 2;
    APIClient client = APIClient.getInstance();
    Endpoint api;
    MultipartBody.Part ImageUri,FileUri;
    boolean chossefile=false;
    boolean chooseimage=false;
    TextView text_view_fileName,text_view_imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = UploadBookBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        text_view_fileName=findViewById(R.id.text_view_fileName);
        text_view_imageName= findViewById(R.id.text_view_imageName);
        setContentView(R.layout.upload_book);
        setContentView(binding.getRoot());
        api= client.getMyApi();
        text_view_fileName=findViewById(R.id.text_view_fileName);
        text_view_imageName=findViewById(R.id.text_view_imageName);

        binding.btnAddTableOfContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TableOfContent t = new TableOfContent();
                t.setKeywords(binding.edtKeyWordsUploadBook.getText().toString());
                ////////////
                t.setPageno(Integer.parseInt(binding.edtPageNoUploadBook.getText().toString()));
                t.setTitle(binding.edtPageTitleUploadBook.getText().toString());
                list.add(t);
                Toast.makeText(UploadBook.this, "TOC Added", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chossefile=true;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);;
                startActivityForResult(intent,GALLERY_REQUEST_CODE);


            }
        });
        binding.btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseimage=true;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                startActivityForResult(intent,GALLERY_REQUEST_CODE);
            }
        });
        binding.btnUploadBookFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIClient client = APIClient.getInstance();
                Endpoint api = client.getMyApi();
                RequestBody tid = RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(MainActivity.tid));
                RequestBody role = RequestBody.create(
                        MediaType.parse("multipart/form-data"), MainActivity.loginRole);
                RequestBody edtTitleUploadBook = RequestBody.create(
                        MediaType.parse("multipart/form-data"), binding.edtTitleUploadBook.getText().toString());
                RequestBody edtAuthorUploadBook = RequestBody.create(
                        MediaType.parse("multipart/form-data"), binding.edtAuthorUploadBook.getText().toString());
                RequestBody edtPublisherUploadBook = RequestBody.create(
                        MediaType.parse("multipart/form-data"), binding.edtPublisherUploadBook.getText().toString());
                RequestBody edtISBNUploadBook = RequestBody.create(
                        MediaType.parse("multipart/form-data"), binding.edtISBNUploadBook.getText().toString());
                RequestBody edtAbstractUploadBook = RequestBody.create(
                        MediaType.parse("multipart/form-data"), binding.edtAbstractUploadBook.getText().toString());
                RequestBody edtPageTitleUploadBook = RequestBody.create(
                        MediaType.parse("multipart/form-data"), binding.edtPageTitleUploadBook.getText().toString());
                RequestBody edtPageNoUploadBook = RequestBody.create(
                        MediaType.parse("multipart/form-data"), (binding.edtPageNoUploadBook.getText().toString()));
                RequestBody edtKeyWordsUploadBook = RequestBody.create(
                        MediaType.parse("multipart/form-data"), binding.edtKeyWordsUploadBook.getText().toString());
                //  MultipartBody.Part photo=MultipartBody.Part.createFormData("imagefile",imageList);

//                Toast.makeText(UploadBook.this, "Getting data"+ImageUri.body(), Toast.LENGTH_SHORT).show();


                RequestBody tid1= api.createPartFromString(""+MainActivity.tid);
                RequestBody loginRole1= api.createPartFromString(""+MainActivity.loginRole);
                RequestBody title1 = api.createPartFromString(""+binding.edtTitleUploadBook.getText().toString());
                RequestBody author1 = api.createPartFromString(""+binding.edtAuthorUploadBook.getText());
                RequestBody publisher1 = api.createPartFromString(""+binding.edtPublisherUploadBook.getText());
                RequestBody Isbn1 = api.createPartFromString(""+binding.edtISBNUploadBook.getText());
                RequestBody Abstract1 = api.createPartFromString(""+binding.edtAbstractUploadBook.getText());

//                RequestBody Pagetitle1 = api.createPartFromString(""+binding.edtPageTitleUploadBook.getText());
//                RequestBody Pagenumber1= api.createPartFromString(""+binding.edtPageNoUploadBook.getText());
//                RequestBody keyword1 = api.createPartFromString(""+binding.edtKeyWordsUploadBook.getText());


                api.TeacherUploadBook(ImageUri,FileUri, tid1,loginRole1,title1,author1,publisher1,Isbn1,Abstract1).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(UploadBook.this, "Book Upload Successfully" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(UploadBook.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 @Nullable Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Uri uri=data.getData();
                String filepath=uri.getPath();
                String[]projection={MediaStore.Files.FileColumns.DATA};
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                if (cursor!=null && cursor.moveToFirst()){
                    @SuppressLint("Range") String filename=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//                    text_view_imageName.setText(filename);
                }
                Bundle bundleObj = data.getExtras();
                Bitmap bmpImage = (Bitmap) bundleObj.get("data");
//                binding.imageBook.setImageBitmap(bmpImage);

            }
        }else if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == RESULT_OK){

                Uri uri=data.getData();
                String filepath=uri.getPath();
                String[]projection={MediaStore.Files.FileColumns.DATA};
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                if (cursor!=null && cursor.moveToFirst()){
                    @SuppressLint("Range") String filename=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    if (filename.endsWith("jpg")|| filename.endsWith("png")){
                        text_view_imageName.setText(filename);
                    }else {
                        text_view_fileName.setText(filename);
                    }
                }

                if (data.getClipData() != null) {
                    // Multiple images selected
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        // Handle each image URI as needed
                        try {
                            if(chooseimage) {
                                ImageUri =  api.prepareFilePart("image", imageUri, UploadBook.this);
                                MultipartBody.Part image =
                                        api.prepareFilePart("image", imageUri, UploadBook.this);
                                imageList.add(image);
                                chooseimage=false;
                            }
                            else if(chossefile){
                                FileUri=  api.prepareFilePart("book", imageUri, UploadBook.this);
                                MultipartBody.Part image =
                                        api.prepareFilePart("book", imageUri, UploadBook.this);
                                fileList.add(image);
                                chossefile=false;

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (data.getData() != null) {
                    // Single image selected
                    Uri imageUri = data.getData();


//                    binding.imageBook.setImageURI(imageUri);
                    try {
                        if(chooseimage) {
                            ImageUri =  api.prepareFilePart("image", imageUri, UploadBook.this);
                            MultipartBody.Part image =
                                    api.prepareFilePart("image", imageUri, getApplicationContext());
                            imageList.add(image);
                            chooseimage=false;
                        }
                        else if(chossefile){
                            //////////////////////////////////////////
//                            Uri fileUri = data.getData();
//                            String filePath = fileUri.getPath(); // Get the file path
//
//                            editTextFilePath.setText(filePath);

                            //////////////////////////////////////////
                            FileUri=  api.prepareFilePart("book", imageUri, UploadBook.this);
                            MultipartBody.Part image =
                                    api.prepareFilePart("book", imageUri, getApplicationContext());
                            imageList.add(image);
                            chossefile=false;

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Handle the image URI as needed
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}