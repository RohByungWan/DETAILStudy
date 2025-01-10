package com.example.detailstudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.libraries.places.api.model.Review;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ReviewTextActivity extends AppCompatActivity {
    private EditText etReview, mEtmail;
    private Button btnSubmit;
    private RecyclerView recyclerView;

    private DatabaseReference databaseReference;
    private ArrayList<String> reviewList;
    private ReviewAdapter adapter;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_text);
        mEtmail = findViewById(R.id.et_email);
        etReview = findViewById(R.id.et_review);
        btnSubmit = findViewById(R.id.btn_submit);
        recyclerView = findViewById(R.id.recyclerView);



        // Firebase Realtime Database 초기화
        databaseReference = FirebaseDatabase.getInstance().getReference("reviews");

        // RecyclerView 설정
        reviewList = new ArrayList<>();
        adapter = new ReviewAdapter(reviewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // 리뷰 데이터 읽기
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reviewList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    String review = data.getValue(String.class);
                    reviewList.add(review);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReviewTextActivity.this, "데이터를 읽는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 리뷰 제출 버튼 클릭 이벤트
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEtmail.getText().toString().trim();
                String review = etReview.getText().toString().trim();
                if (!review.isEmpty()) {
                    databaseReference.push().setValue(email);
                    databaseReference.push().setValue(review);
                    etReview.setText("");
                    mEtmail.setText("");
                    Toast.makeText(ReviewTextActivity.this, "리뷰가 제출되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ReviewTextActivity.this, "리뷰를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}