package com.example.detailstudy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Reservation extends AppCompatActivity {

    private CalendarView calendarView;
    private Spinner timeSpinner;
    private CheckBox checkBoxOption1, checkBoxOption2, Moning1, ToSsan1, Box1, Box2, Box3, Box4 ;
    private Button buttonReserve, btn_day, btn_Time;
    private TextView textViewResult;
    private RadioGroup Group1,Group2;
    private String selectedDate;
    private String selectedTime;
//    private View RadioButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // UI 요소 초기화
        calendarView = findViewById(R.id.calendarView);
        timeSpinner = findViewById(R.id.timeSpinner);
        checkBoxOption1 = findViewById(R.id.checkBoxOption1);
        checkBoxOption2 = findViewById(R.id.checkBoxOption2);
        buttonReserve = findViewById(R.id.buttonReserve);
        textViewResult = findViewById(R.id.textViewResult);
        Group1 = findViewById(R.id.Group1);
        Group2 = findViewById(R.id.Group2);
        Moning1 = findViewById(R.id.Moning1);
        ToSsan1 = findViewById(R.id.ToSsan1);
        Button btn_day = findViewById(R.id.btn_day);
        Button btn_Time = findViewById(R.id.btn_Time);
        Box1 = findViewById(R.id.Box1);
        Box2 = findViewById(R.id.Box2);
        Box3 = findViewById(R.id.Box3);
        Box4 = findViewById(R.id.Box4);

        // 초기값 설정
        selectedDate = null;
        selectedTime = null;

        btn_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
                timeSpinner.setVisibility(View.GONE);
                if (!btn_day.isClickable()) {
                    calendarView.setVisibility(View.GONE);
                    timeSpinner.setVisibility(View.GONE);
                }
            }
        });
        btn_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.GONE);
                timeSpinner.setVisibility(View.VISIBLE);
                if (btn_Time.isClickable()) {
                    return;
                }
                calendarView.setVisibility(View.GONE);
                timeSpinner.setVisibility(View.GONE);
            }
        });


        checkBoxOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Group1.setVisibility(View.VISIBLE);
                Group2.setVisibility(View.GONE);
                //현대 체크박스가 선택되지 않았을 시 하위 선택지는 보이지 않는다.
                if (!checkBoxOption1.isChecked()) {
                    Group1.setVisibility(View.GONE);
                    Group2.setVisibility(View.GONE);
                }
            }
        });

        checkBoxOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Group1.setVisibility(View.GONE);
                Group2.setVisibility(View.VISIBLE);
                //기아 체크박스가 선택 되지 않았을 시 하위 선택지는 보이지 않는다.
                if (!checkBoxOption2.isChecked()) {
                    Group1.setVisibility(View.GONE);
                    Group2.setVisibility(View.GONE);
                }
            }
        });




        // 시간대 설정
        ArrayList<String> timeSlots = new ArrayList<>();
        for (int i = 10; i <= 18; i++) {
            timeSlots.add(i + ":00 - " + (i + 1) + ":00");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeSlots);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter);

        // 캘린더 날짜 선택 리스너
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
        });

        // 시간 선택 리스너
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = timeSlots.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTime = null;
            }
        });


        // 예약 버튼 클릭 리스너
        buttonReserve.setOnClickListener(v -> {
            if (selectedDate == null) {
                Toast.makeText(this, "날짜를 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedTime == null) {
                Toast.makeText(this, "시간대를 선택하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isOption1Checked = checkBoxOption1.isChecked();
            boolean isOption2Checked = checkBoxOption2.isChecked();
            boolean isOption3Checked = Box1.isChecked();
            boolean isOption4Checked = Box2.isChecked();


            if (!isOption1Checked && !isOption2Checked ) {
                Toast.makeText(this, "최소 하나의 옵션을 선택하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 결과 표시
            StringBuilder result = new StringBuilder();
            result.append("예약 날짜: ").append(selectedDate).append("\n");
            result.append("예약 시간: ").append(selectedTime).append("\n");
            result.append("예약 종류: ");
            if (isOption1Checked) result.append("기아자동차").append("\n");
            if (isOption2Checked) result.append("현대자동차").append("\n");
            result.append("예약 종류: ");
            if (isOption3Checked) result.append("현대").append("\n");
            if (isOption4Checked) result.append("싼타페").append("\n");


            textViewResult.setText(result.toString());
            Toast.makeText(this, "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        });
    }
}