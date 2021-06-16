package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import CaseReportFragments.CaseLogListFragment;
import CaseReportFragments.CaseWorkPlanListFragment;
import Database.AppDatabase;
import Models.CasePlanCaseLog;

public class CreateEditCaseLogActivity extends AppCompatActivity {

    public static long created_item_id;
    public static EditText txt_Date;
    public static EditText txt_ActionTakenActivity;
    public static EditText txt_Outcome;
    public static EditText txt_AttendingPerson;
    public static Button btn_save;
    public static Button btn_close;
    public static Button btn_delete;

    CasePlanCaseLog casePlanCaseLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_case_log);

        txt_Date = (EditText)findViewById(R.id.txt_Date);
        txt_ActionTakenActivity = (EditText)findViewById(R.id.txt_ActionTakenActivity);
        txt_Outcome = (EditText)findViewById(R.id.txt_Outcome);
        txt_AttendingPerson = (EditText)findViewById(R.id.txt_AttendingPerson);

        final Calendar myCalendar = Calendar.getInstance();
        //
        txt_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateEditCaseLogActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        txt_Date.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseLogActivity.this);
                    casePlanCaseLog = db.casePlanCaseLogDao().findById(created_item_id);

                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }

        txt_Date.setText(casePlanCaseLog.Date);
        txt_ActionTakenActivity.setText(casePlanCaseLog.ActionTakenActivity);
        txt_Outcome.setText(casePlanCaseLog.Outcome);
        txt_AttendingPerson.setText(casePlanCaseLog.AttendingPerson);
    }

    public void saveCaseLog(View v)
    {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseLogActivity.this);
                    casePlanCaseLog.Date = txt_Date.getText().toString();
                    casePlanCaseLog.ActionTakenActivity = txt_ActionTakenActivity.getText().toString();
                    casePlanCaseLog.Outcome = txt_Outcome.getText().toString();
                    casePlanCaseLog.AttendingPerson = txt_AttendingPerson.getText().toString();
                    casePlanCaseLog.SavedAtLeastOnce = true;
                    db.casePlanCaseLogDao().update(casePlanCaseLog);
                    db = null;
                }
            });
            t.start();
            t.join();
            Toast.makeText(CreateEditCaseLogActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }
        pd.hide();
        CaseLogListFragment.loadCaseLogs();
    }

    public void closeActivity(View v)
    {
        finish();
        CaseLogListFragment.loadCaseLogs();
    }

    public void deleteCaseLog(View v)
    {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseLogActivity.this);
                    db.casePlanCaseLogDao().delete(casePlanCaseLog);
                    db = null;
                }
            });
            t.start();
            t.join();
            Toast.makeText(CreateEditCaseLogActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }
        pd.hide();
        CaseLogListFragment.loadCaseLogs();
    }


}