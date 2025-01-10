package com.example.detailstudy;

import static androidx.activity.result.ActivityResultLauncherKt.launch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class PhotoAlbum extends AppCompatActivity {
//    private static final int SELECT_PICTURES_REQUEST_CODE = 1;
//    private ArrayList<Uri> imageUris; // 이미지 URI를 저장할 리스트
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main); // XML 레이아웃 연결
//
//        imageUris = new ArrayList<>(); // 이미지 URI 리스트 초기화
//        Button selectImagesButton = findViewById(R.id.btn_SelectImages); // 이미지 선택 버튼
//
//        // 이미지 선택 버튼 클릭 리스너
//        selectImagesButton.setOnClickListener(v -> openGallery());
//
//        // 이미지 추가 버튼 클릭 리스너
//        Button addImagesButton = findViewById(R.id.btn_AddImages); // 이미지 추가 버튼
//        addImagesButton.setOnClickListener(v -> addImagesToGallery());
//    }
//
//    // 갤러리 열기 (여러 이미지를 선택할 수 있도록)
//    private void openGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // 여러 개 이미지 선택 가능
//        startActivityForResult(intent, SELECT_PICTURES_REQUEST_CODE);
//    }
//
//    // 이미지 선택 후 결과 처리
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == SELECT_PICTURES_REQUEST_CODE && resultCode == RESULT_OK) {
//            if (data != null) {
//                if (data.getClipData() != null) {
//                    // 여러 이미지 선택한 경우
//                    int count = data.getClipData().getItemCount();
//                    for (int i = 0; i < count; i++) {
//                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
//                        imageUris.add(imageUri); // 선택한 이미지 URI를 리스트에 추가
//                    }
//                } else if (data.getData() != null) {
//                    // 하나의 이미지 선택한 경우
//                    Uri imageUri = data.getData();
//                    imageUris.add(imageUri); // 선택한 이미지 URI를 리스트에 추가
//                }
//            }
//        }
//    }
//
//    // 선택한 이미지를 TableLayout에 추가
//    private void addImagesToGallery() {
//        TableLayout tableLayout = findViewById(R.id.galleryTableLayout); // TableLayout 참조
//        tableLayout.removeAllViews(); // 이전에 추가된 이미지들을 지우기
//
//        for (int i = 0; i < imageUris.size(); i++) {
//            TableRow tableRow = new TableRow(this); // 새로운 TableRow 생성
//            ImageView imageView = new ImageView(this); // 새로운 ImageView 생성
//            imageView.setLayoutParams(new TableRow.LayoutParams(
//                    TableRow.LayoutParams.MATCH_PARENT,
//                    200 // 이미지 높이 설정
//            ));
//            imageView.setImageURI(imageUris.get(i)); // 이미지 URI를 설정
//
//            tableRow.addView(imageView); // TableRow에 ImageView 추가
//            tableLayout.addView(tableRow); // TableLayout에 TableRow 추가
//        }
//    }
}

