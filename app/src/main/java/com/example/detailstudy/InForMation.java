package com.example.detailstudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InForMation extends AppCompatActivity {
    private Button Infor_Review;
    private EditText et_Inforemail;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDtatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_in_for_mation);

        mAuth = FirebaseAuth.getInstance();
        et_Inforemail = findViewById(R.id.et_email);

//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            String emailId = user.getEmail(); //로그인한 사용자의 이메일 가져오기
//            et_Inforemail.setText(emailId); // 내 정보에 이메일 표시
//        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user != null? user.getUid():null;

        mAuth = FirebaseAuth.getInstance();
        mDtatabase = FirebaseDatabase.getInstance().getReference().getDatabase();




        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button Infor_Review = findViewById(R.id.Infor_Review);
        Infor_Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InForMation.this,ReviewTextActivity.class);
                startActivity(intent);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.all_My), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }
}