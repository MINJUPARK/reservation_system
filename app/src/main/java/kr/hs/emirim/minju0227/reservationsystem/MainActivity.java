package kr.hs.emirim.minju0227.reservationsystem;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Chronometer chrono;
    Button butStart, butDone;
    RadioButton radioDate, radioTime;
    CalendarView calView;
    TimePicker timePick;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chrono = (Chronometer)findViewById(R.id.chronometer);
        butStart = (Button)findViewById(R.id.but_start);
        butDone = (Button)findViewById(R.id.but_done);
        radioDate = (RadioButton)findViewById(R.id.radio_date);
        radioTime = (RadioButton)findViewById(R.id.radio_time);
        calView = (CalendarView)findViewById(R.id.calendar);
        timePick = (TimePicker)findViewById(R.id.time_pick);
        timePick.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);

        // EventHandler (익명클래스 (이름 없이 클래스를 구현 + 객체 생성))
        // 이벤트 핸들러와 이벤트 소스를 연결 : EventListner
        radioDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePick.setVisibility(View.INVISIBLE); // 타임피커는 안보이게
                calView.setVisibility(View.VISIBLE); // 캘린더뷰는 보이게
            }
        });

        radioTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePick.setVisibility(View.VISIBLE); // 타임피커는 보이게
                calView.setVisibility(View.INVISIBLE); // 캘린더뷰는 안보이게
            }
        });

        butStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });

        butDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);
                Calendar cal=Calendar.getInstance();
                cal.setTimeInMillis(calView.getDate());
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH)+1;
                int date = cal.get(Calendar.DATE);
                /*int hour = timePick.getHour();
                int minute = timePick.getMinute();*/
                String dateAndTime = year+"년 "+month+"월 "+date+"일 "/*+hour+"시 "+minute+"분 "*/;
                textResult.setText(dateAndTime);
            }
        });
    }
}
