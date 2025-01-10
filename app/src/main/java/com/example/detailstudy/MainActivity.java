package com.example.detailstudy;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int SELECT_PICTURES_REQUEST_CODE = 1;
    private ArrayList<Uri> imageUris; // 이미지 URI를 저장할 리스트
    private RecyclerView recyclerView;
    private ImageAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageUris = new ArrayList<>(); // 이미지 URI 리스트 초기화
        recyclerView = findViewById(R.id.recyclerView);

        // RecyclerView 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ImageAdapter(this, imageUris);
        recyclerView.setAdapter(adapter);

        recyclerView = findViewById(R.id.recyclerView); // RecyclerView의 ID 설정
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2열 그리드 설정


        // 이미지 선택 버튼 설정
        Button selectImagesButton = findViewById(R.id.btn_SelectImages);
        selectImagesButton.setOnClickListener(v -> openGallery());

//        // 이미지 추가 버튼 클릭 리스너
//        Button addImagesButton = findViewById(R.id.btn_AddImages); // 이미지 추가 버튼
//        addImagesButton.setOnClickListener(v -> addImagesToGallery());

        Button Submain_Infor = findViewById(R.id.Submain_Infor);
        Submain_Infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InForMation.class);
                startActivity(intent);
            }
        });

        Button Submain_Cal = findViewById(R.id.Submain_Cal);
        Submain_Cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Reservation.class);
                startActivity(intent);
            }
        });


        ImageButton Map_Fragment = findViewById(R.id.Map_Fragment);
        Map_Fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, googleMapActivity.class);
                startActivity(intent);
            }
        });
        Button Submain_Maps = findViewById(R.id.Submain_Maps);
        Submain_Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, googleMapActivity.class);
                startActivity(intent);
            }
        });

        Button reviewText = findViewById(R.id.reviewText);
        reviewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReviewTextActivity.class);
                startActivity(intent);

                Log.d(TAG, "MainActivity - onClick() called");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }


    // 갤러리 열기 (여러 이미지를 선택할 수 있도록)
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // 여러 개 이미지 선택 가능
        startActivityForResult(intent, SELECT_PICTURES_REQUEST_CODE);
    } 

    // 갤러리에서 이미지를 선택한 후 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURES_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        imageUris.add(imageUri); // 선택한 이미지 URI 추가
                    }
                } else if (data.getData() != null) {
                    Uri imageUri = data.getData();
                    imageUris.add(imageUri); // 하나의 이미지 URI 추가
                }
            }

            // 선택한 이미지들을 RecyclerView에 업데이트
            adapter.notifyDataSetChanged();
        }
    }
}