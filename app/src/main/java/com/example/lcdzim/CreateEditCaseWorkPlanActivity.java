package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import CaseReportFragments.CaseWorkPlanListFragment;
import Database.AppDatabase;
import Models.CasePlanCaseWorkplan;

public class CreateEditCaseWorkPlanActivity extends AppCompatActivity {

    public static long created_item_id;

    public static EditText txt_Date;
    public static EditText txt_ActionToBeTaken;
    public static EditText txt_Responsibility;
    public static CheckBox chk_Done;
    public static Button btn_save;
    public static Button btn_close;
    public static Button btn_delete;

    CasePlanCaseWorkplan casePlanCaseWorkplan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_edit_case_work_plan);

        txt_Date = (EditText) findViewById(R.id.txt_Date);
        txt_ActionToBeTaken = (EditText) findViewById(R.id.txt_ActionToBeTaken);
        txt_Responsibility = (EditText) findViewById(R.id.txt_Responsibility);
        chk_Done = (CheckBox) findViewById(R.id.chk_Done);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_close = (Button) findViewById(R.id.btn_close);
        btn_delete = (Button) findViewById(R.id.btn_delete);

        final Calendar myCalendar = Calendar.getInstance();
        //
        txt_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateEditCaseWorkPlanActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseWorkPlanActivity.this);
                    casePlanCaseWorkplan = db.casePlanCaseWorkPlanDao().findById(created_item_id);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }

        txt_Date.setText(casePlanCaseWorkplan.Date);
        txt_ActionToBeTaken.setText(casePlanCaseWorkplan.ActionToBeTaken);
        txt_Responsibility.setText(casePlanCaseWorkplan.Responsibility);
        chk_Done.setChecked(casePlanCaseWorkplan.Done);

    }

    public void saveWorkPlan(View v) {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseWorkPlanActivity.this);
                    casePlanCaseWorkplan.Responsibility = txt_Responsibility.getText().toString();
                    casePlanCaseWorkplan.ActionToBeTaken = txt_Responsibility.getText().toString();
                    casePlanCaseWorkplan.Done = chk_Done.isChecked();
                    casePlanCaseWorkplan.Date = txt_Date.getText().toString();
                    casePlanCaseWorkplan.SavedAtLeastOnce = true;
                    db.casePlanCaseWorkPlanDao().update(casePlanCaseWorkplan);
                    db = null;
                }
            });
            t.start();
            t.join();
            Toast.makeText(CreateEditCaseWorkPlanActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }
        pd.hide();
        CaseWorkPlanListFragment.loadWorkPlans();
    }

    public void closeActivity(View v) {
        finish();
        CaseWorkPlanListFragment.loadWorkPlans();
    }

    public void deleteWorkPlan(View v) {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseWorkPlanActivity.this);
                    db.casePlanCaseWorkPlanDao().delete(casePlanCaseWorkplan);
                    db = null;
                }
            });
            t.start();
            t.join();
            Toast.makeText(CreateEditCaseWorkPlanActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }
        pd.hide();
        CaseWorkPlanListFragment.loadWorkPlans();
    }

}