package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Database.AppDatabase;
import Models.CaseReportPaymentsToBeneficiaries;

public class CreateEditPaymentsToBeneficiaryActivity extends AppCompatActivity {

    long created_item_id;
    CaseReportPaymentsToBeneficiaries caseReportPaymentsToBeneficiaries;
    AppDatabase db;
    ProgressDialog pd;

    Toolbar toolbar;

    TextView txt_Date;
    TextView txt_Name;
    TextView txt_IdNumber;
    TextView txt_BusFare;
    TextView txt_BreakFast;
    TextView txt_Lunch;
    TextView txt_Dinner;
    TextView txt_Accomodation;
    TextView txt_PerDiem;
    TextView txt_Other;
    TextView txt_ExpenseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_payments_to_beneficiary);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pd = new ProgressDialog(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Payment To Beneficiary Form");
        created_item_id = getIntent().getLongExtra("created_item_id", 0);
        db = AppDatabase.getAppDatabase(this);
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    caseReportPaymentsToBeneficiaries = db.caseReportPaymentsToBeneficiariesDao().findById(created_item_id);
                }
            });
            t.start();
            t.join();
            db = null;
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }


        txt_Date = (TextView) findViewById(R.id.txt_Date);
        txt_Name = (TextView) findViewById(R.id.txt_Name);
        txt_IdNumber = (TextView) findViewById(R.id.txt_IdNumber);
        txt_BusFare = (TextView) findViewById(R.id.txt_BusFare);
        txt_BreakFast = (TextView) findViewById(R.id.txt_BreakFast);
        txt_Lunch = (TextView) findViewById(R.id.txt_Lunch);
        txt_Dinner = (TextView) findViewById(R.id.txt_Dinner);
        txt_Accomodation = (TextView) findViewById(R.id.txt_Accomodation);
        txt_PerDiem = (TextView) findViewById(R.id.txt_PerDiem);
        txt_Other = (TextView) findViewById(R.id.txt_Other);
        txt_ExpenseCode = (TextView) findViewById(R.id.txt_ExpenseCode);

        final Calendar myCalendar = Calendar.getInstance();
        txt_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateEditPaymentsToBeneficiaryActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        txt_Date.setText(caseReportPaymentsToBeneficiaries.Date);
        txt_Name.setText(caseReportPaymentsToBeneficiaries.Name);
        txt_IdNumber.setText(caseReportPaymentsToBeneficiaries.IdNumber);
        if(caseReportPaymentsToBeneficiaries.BusFare!=null)txt_BusFare.setText(caseReportPaymentsToBeneficiaries.BusFare+"");
        if(caseReportPaymentsToBeneficiaries.Breakfast!=null)txt_BreakFast.setText(caseReportPaymentsToBeneficiaries.Breakfast+"");
        if(caseReportPaymentsToBeneficiaries.Lunch!=null)txt_Lunch .setText(caseReportPaymentsToBeneficiaries.Lunch+"");
        if(caseReportPaymentsToBeneficiaries.Dinner!=null)txt_Dinner.setText(caseReportPaymentsToBeneficiaries.Dinner+"");
        if(caseReportPaymentsToBeneficiaries.Accomodation!=null)txt_Accomodation.setText(caseReportPaymentsToBeneficiaries.Accomodation+"");
        if(caseReportPaymentsToBeneficiaries.PerDiem!=null)txt_PerDiem.setText(caseReportPaymentsToBeneficiaries.PerDiem+"");
        if(caseReportPaymentsToBeneficiaries.Other!=null)txt_Other.setText(caseReportPaymentsToBeneficiaries.Other +"");
        txt_ExpenseCode.setText(caseReportPaymentsToBeneficiaries.ExpenseCode);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.case_report_payments_to_beneficiary_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_case_report_payment_to_beneficiary_form:

                pd.setTitle("Loading...");
                pd.show();
                //create and save a case report
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getAppDatabase(CreateEditPaymentsToBeneficiaryActivity.this);
                            caseReportPaymentsToBeneficiaries.SavedAtLeastOnce = true;
                            caseReportPaymentsToBeneficiaries.Date = txt_Date.getText().toString();
                            caseReportPaymentsToBeneficiaries.Name = txt_Name.getText().toString();
                            caseReportPaymentsToBeneficiaries.IdNumber = txt_IdNumber.getText().toString();
                            caseReportPaymentsToBeneficiaries.BusFare = Double.parseDouble(txt_BusFare.getText().toString());
                            caseReportPaymentsToBeneficiaries.Breakfast = Double.parseDouble(txt_BreakFast.getText().toString());
                            caseReportPaymentsToBeneficiaries.Lunch = Double.parseDouble(txt_Lunch.getText().toString());
                            caseReportPaymentsToBeneficiaries.Dinner = Double.parseDouble(txt_Dinner.getText().toString());
                            caseReportPaymentsToBeneficiaries.Accomodation = Double.parseDouble(txt_Accomodation.getText().toString());
                            caseReportPaymentsToBeneficiaries.PerDiem = Double.parseDouble(txt_PerDiem.getText().toString());
                            caseReportPaymentsToBeneficiaries.Other = Double.parseDouble(txt_Other.getText().toString());
                            caseReportPaymentsToBeneficiaries.ExpenseCode = txt_ExpenseCode.getText().toString();
                            db.caseReportPaymentsToBeneficiariesDao().update(caseReportPaymentsToBeneficiaries);
                            db = null;
                        }
                    });
                    t.start();
                    t.join();
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getLocalizedMessage());
                }
                pd.hide();
                break;
            case R.id.delete_case_report_payent_to_beneficiary_form:

                new AlertDialog.Builder(this)
                        .setTitle("Title")
                        .setMessage("Delete this Form")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DeleteForm();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

                break;
            default:
                break;
        }
        return true;
    }

    private void DeleteForm() {
        pd.setTitle("Loading...");
        pd.show();
        //create and save a case report
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditPaymentsToBeneficiaryActivity.this);
                    db.caseReportPaymentsToBeneficiariesDao().delete(caseReportPaymentsToBeneficiaries);
                    db = null;
                }
            });
            t.start();
            t.join();
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getLocalizedMessage());
        }
        pd.hide();
        finish();
    }
}