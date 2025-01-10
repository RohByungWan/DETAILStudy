package com.example.detailstudy;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증 처리 하는
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd, mNickName;    // 회원가입 입력 필드
    private Button mBtnRegister;        // 히원가입 버튼

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("DETAIL");

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mBtnRegister = findViewById(R.id.btn_register);
        mNickName = findViewById(R.id.et_name);
        
        Log.d(TAG, "RegisterActivity - onCreate() called");

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 회원가입 처리 시작 액션
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();

                // Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // 회원가입이 성공이 되었을때 의 처리
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); // 현재의 유저를 가지고온다 firebaseUser 를 가지고 온다
                            UserAccont account = new UserAccont();
                            account.setIdToken(firebaseUser.getUid());
                            account.getEmalilId(firebaseUser.getEmail());
                            account.getNameId(firebaseUser.getDisplayName());
                            account.getPassword(strPwd);
                            
                            Log.d(TAG, "RegisterActivity - onComplete() called");

                            // setvalue : database에 insert 삽입 행위
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "RegisterActivity - onComplete() called");
                        } else { //else 만약에 ~ 되지 않았다면
                            Toast.makeText(RegisterActivity.this, "회원가입에 실패 하셨습니다.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "RegisterActivity - onComplete() called");
                        }

                    }
                });


            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}