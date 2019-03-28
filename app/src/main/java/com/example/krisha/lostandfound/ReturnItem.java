package com.example.krisha.lostandfound;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ReturnItem extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private static final String TAG = "ReturnItem";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    AutoCompleteTextView st;
    String[] station = {"Agra","Bankura","Belapur","Dadar","Lokmanyatilak","Gorakhpur","Bandra","Mumbai Central","Ahmedabad","Dadar","Dehradun","Surat","Vasai Road","Madgaon","Pune","Nizamuddin","New Delhi","Panvel",
            "Vadadro","Amritsar","Chandigarh","Nashik","Howrah","Bareily","Ratnagiri","Kalyan","Gaya","Indore","Ajmer","Rajkot"};
    AutoCompleteTextView tr;
    String[] train = {"RajdhaniExpress","PuneVeravalExpress","LokshaktiExpress","GujaratMail","Avadh Express","Ahmedabad Passenger","AugustKranti","Avantika Express","Ajmer Express","Dehradhun Express","Darshan Express",
            "DeccanQueen","Duronta","Doon Express","Shatabdi Express","Swaraj Express","Sevagram Express","Saket Express","Gorakhpur Express","Gujarat Express","Goa Express","GoldenTemple","Gitanjali Express","KonkanKanya",
            "Kushinagar","Konark Express","Kolhapur Express","Mangal Express","Mahanagari","Mandovi Express","Maharashtra Express"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_item);


        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ReturnItem.this,
                        android.R.style.Theme_DeviceDefault_Dialog_Alert,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG,"onDateSet: mm/dd/yyyy: "+ month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };



        st = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.select_dialog_item, station);
        st.setThreshold(1);
        st.setAdapter(adapter1);


         tr=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2) ;
         ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.select_dialog_item,train);
         tr.setThreshold(1);
         tr.setAdapter(adapter2);

    }


    public void timebutton(View view){
        DialogFragment timePicker= new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(),"timePicker");
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView tvTime=(TextView)findViewById(R.id.tvTime);
        tvTime.setText( hourOfDay+":"+minute);

    }


    public void cbut(View view) {

        String stationName = st.getText().toString().trim();
        String trainName = tr.getText().toString().trim();
        String date = mDisplayDate.getText().toString().trim();

        if(TextUtils.isEmpty(stationName)){
            Toast.makeText(this, "Which Station did you find the object?", Toast.LENGTH_LONG).show();
        }else  if(TextUtils.isEmpty(trainName)){
            Toast.makeText(this, "Which Train did you find the object?", Toast.LENGTH_LONG).show();
        }else  if(TextUtils.isEmpty(date)){
            Toast.makeText(this, "On which date did you find the object?", Toast.LENGTH_LONG).show();
        }else{

            Intent intent = new Intent(getBaseContext(), Return1.class);
            startActivity(intent);

        }

    }


}
