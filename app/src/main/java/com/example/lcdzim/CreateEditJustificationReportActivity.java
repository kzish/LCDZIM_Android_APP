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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Database.AppDatabase;
import Models.CaseReportJustificationReportForAttendedCases;

public class CreateEditJustificationReportActivity extends AppCompatActivity {

    long created_item_id;
    CaseReportJustificationReportForAttendedCases caseReportJustificationReportForAttendedCases;
    AppDatabase db;
    ProgressDialog pd;

    Toolbar toolbar;

    TextView txt_NameOfBeneficiary;
    TextView txt_PlaceOfOriginResidence;
    TextView txt_PlaceOfActivity;
    TextView txt_DateWhenTheBeneficiaryWasAssisted;
    CheckBox chk_TypeOfAssistanceLogisticalSupport;
    CheckBox chk_TypeOfAssistanceDisabilityExpert;
    CheckBox chk_TypeOfAssistanceHomeVisit;
    TextView txt_SummaryOfActivity;
    TextView txt_Outcome;
    TextView txt_DateOfCourtHearing;
    TextView txt_PreparedBy;
    TextView txt_PreparedByDate;
    TextView txt_ApprovedBy;
    TextView txt_ApprovedByDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_justification_report);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pd = new ProgressDialog(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Justification Report");
        created_item_id = getIntent().getLongExtra("created_item_id", 0);
        db = AppDatabase.getAppDatabase(this);
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    caseReportJustificationReportForAttendedCases = db.caseReportJustificationReportForAttendedCasesDao().findById(created_item_id);
                }
            });
            t.start();
            t.join();
            db = null;
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }


        txt_NameOfBeneficiary = (TextView) findViewById(R.id.txt_NameOfBeneficiary);
        txt_PlaceOfOriginResidence = (TextView) findViewById(R.id.txt_PlaceOfOriginResidence);
        txt_PlaceOfActivity = (TextView) findViewById(R.id.txt_PlaceOfActivity);
        txt_DateWhenTheBeneficiaryWasAssisted = (TextView) findViewById(R.id.txt_DateWhenTheBeneficiaryWasAssisted);
        chk_TypeOfAssistanceLogisticalSupport = (CheckBox) findViewById(R.id.chk_TypeOfAssistanceLogisticalSupport);
        chk_TypeOfAssistanceDisabilityExpert = (CheckBox) findViewById(R.id.chk_TypeOfAssistanceDisabilityExpert);
        chk_TypeOfAssistanceHomeVisit = (CheckBox) findViewById(R.id.chk_TypeOfAssistanceHomeVisit);
        txt_SummaryOfActivity = (TextView) findViewById(R.id.txt_SummaryOfActivity);
        txt_Outcome = (TextView) findViewById(R.id.txt_Outcome);
        txt_DateOfCourtHearing = (TextView) findViewById(R.id.txt_DateOfCourtHearing);
        txt_PreparedBy = (TextView) findViewById(R.id.txt_PreparedBy);
        txt_PreparedByDate = (TextView) findViewById(R.id.txt_PreparedByDate);
        txt_ApprovedBy = (TextView) findViewById(R.id.txt_ApprovedBy);
        txt_ApprovedByDate = (TextView) findViewById(R.id.txt_ApprovedByDate);


        final Calendar myCalendar = Calendar.getInstance();
        txt_PreparedByDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateEditJustificationReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        txt_PreparedByDate.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        txt_ApprovedByDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateEditJustificationReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        txt_ApprovedByDate.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        txt_DateWhenTheBeneficiaryWasAssisted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateEditJustificationReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        txt_DateWhenTheBeneficiaryWasAssisted.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        txt_DateOfCourtHearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateEditJustificationReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        txt_DateOfCourtHearing.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txt_NameOfBeneficiary.setText(caseReportJustificationReportForAttendedCases.NameOfBeneficiary);
        txt_PlaceOfOriginResidence.setText(caseReportJustificationReportForAttendedCases.PlaceOfOriginResidence);
        txt_PlaceOfActivity.setText(caseReportJustificationReportForAttendedCases.PlaceOfActivity);
        txt_DateWhenTheBeneficiaryWasAssisted.setText(caseReportJustificationReportForAttendedCases.DateWhenTheBeneficiaryWasAssisted);
        chk_TypeOfAssistanceLogisticalSupport.setChecked(caseReportJustificationReportForAttendedCases.TypeOfAssistanceLogisticalSupport);
        chk_TypeOfAssistanceDisabilityExpert.setChecked(caseReportJustificationReportForAttendedCases.TypeOfAssistanceDisabilityExpert);
        chk_TypeOfAssistanceHomeVisit.setChecked(caseReportJustificationReportForAttendedCases.TypeOfAssistanceHomeVisit);
        txt_SummaryOfActivity.setText(caseReportJustificationReportForAttendedCases.SummaryOfActivity);
        txt_Outcome.setText(caseReportJustificationReportForAttendedCases.Outcome);
        txt_DateOfCourtHearing.setText(caseReportJustificationReportForAttendedCases.DateOfCourtHearing);
        txt_PreparedBy.setText(caseReportJustificationReportForAttendedCases.PreparedBy);
        txt_PreparedByDate.setText(caseReportJustificationReportForAttendedCases.PreparedByDate);
        txt_ApprovedBy.setText(caseReportJustificationReportForAttendedCases.ApprovedBy);
        txt_ApprovedByDate.setText(caseReportJustificationReportForAttendedCases.ApprovedByDate);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.case_report_justification_report_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_case_report_justification_report:

                pd.setTitle("Loading...");
                pd.show();
                //create and save a case report
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getAppDatabase(CreateEditJustificationReportActivity.this);
                            caseReportJustificationReportForAttendedCases.SavedAtLeastOnce = true;
                            caseReportJustificationReportForAttendedCases.NameOfBeneficiary = txt_NameOfBeneficiary.getText().toString();
                            caseReportJustificationReportForAttendedCases.PlaceOfOriginResidence = txt_PlaceOfOriginResidence.getText().toString();
                            caseReportJustificationReportForAttendedCases.PlaceOfActivity = txt_PlaceOfActivity.getText().toString();
                            caseReportJustificationReportForAttendedCases.DateWhenTheBeneficiaryWasAssisted = txt_DateWhenTheBeneficiaryWasAssisted.getText().toString();
                            caseReportJustificationReportForAttendedCases.TypeOfAssistanceLogisticalSupport = chk_TypeOfAssistanceLogisticalSupport.isChecked();
                            caseReportJustificationReportForAttendedCases.TypeOfAssistanceDisabilityExpert = chk_TypeOfAssistanceDisabilityExpert.isChecked();
                            caseReportJustificationReportForAttendedCases.TypeOfAssistanceHomeVisit = chk_TypeOfAssistanceHomeVisit.isChecked();
                            caseReportJustificationReportForAttendedCases.SummaryOfActivity = txt_SummaryOfActivity.getText().toString();
                            caseReportJustificationReportForAttendedCases.Outcome = txt_Outcome.getText().toString();
                            caseReportJustificationReportForAttendedCases.DateOfCourtHearing = txt_DateOfCourtHearing.getText().toString();
                            caseReportJustificationReportForAttendedCases.PreparedBy = txt_PreparedBy.getText().toString();
                            caseReportJustificationReportForAttendedCases.PreparedByDate = txt_PreparedByDate.getText().toString();
                            caseReportJustificationReportForAttendedCases.ApprovedBy = txt_ApprovedBy.getText().toString();
                            caseReportJustificationReportForAttendedCases.ApprovedByDate = txt_ApprovedByDate.getText().toString();
                            db.caseReportJustificationReportForAttendedCasesDao().update(caseReportJustificationReportForAttendedCases);
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
            case R.id.delete_case_report_justification_report:

                new AlertDialog.Builder(this)
                        .setTitle("Title")
                        .setMessage("Delete this Justification Report")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DeleteReport();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

                break;
            default:
                break;
        }
        return true;
    }

    private void DeleteReport() {
        pd.setTitle("Loading...");
        pd.show();
        //create and save a case report
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditJustificationReportActivity.this);
                    db.caseReportJustificationReportForAttendedCasesDao().delete(caseReportJustificationReportForAttendedCases);
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