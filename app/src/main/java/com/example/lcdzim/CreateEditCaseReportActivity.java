package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.tabs.TabLayout;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import CaseReportFragments.BasicInformationFragment;
import CaseReportFragments.CareGiverInformationFragment;
import CaseReportFragments.ClientInformationFragment;
import CaseReportFragments.DescriptionOfCaseFragment;
import CaseReportFragments.NeedsAssesmentFragment;
import CaseReportFragments.NextOfKinFragment;
import CaseReportFragments.PGSInformationFragment;
import CaseReportFragments.ViewPagerAdapter;
import Database.AppDatabase;
import Globals.globals;
import Models.CaseReport;
import Models.CaseReportCareGiver;
import Models.CaseReportClientInformation;
import Models.CaseReportDescriptionOfTheCaseProblem;
import Models.CaseReportNeedsAssesment;
import Models.CaseReportNextOfKin;
import Models.CaseReportParentsGuardiansSpousesInformation;

public class CreateEditCaseReportActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    Toolbar toolbar;

    public static String case_id;//current case id, always set this before calling this activity
    public static Context context;
    AppDatabase db;
    ProgressDialog pd;

    public static CaseReport caseReport;
    public static CaseReportClientInformation caseReportClientInformation;
    public static CaseReportDescriptionOfTheCaseProblem caseReportDescriptionOfTheCaseProblem;
    public static CaseReportNextOfKin caseReportNextOfKin;
    public static CaseReportCareGiver caseReportCareGiver;
    public static CaseReportNeedsAssesment caseReportNeedsAssesment;
    public static CaseReportParentsGuardiansSpousesInformation caseReportParentsGuardiansSpousesInformation;

    String __caseReport;
    String __caseReportClientInformation;
    String __caseReportDescriptionOfTheCaseProblem;
    String __caseReportNeedsAssesment;
    String __caseReportNextOfKin;
    String __caseReportCareGiver;
    String __caseReportParentsGuardiansSpousesInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_add_record);
        context = this;

        pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();
        db = AppDatabase.getAppDatabase(this);
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    //pull fresh from db
                    caseReport = db.caseReportDao().findByCaseId(case_id);
                    caseReportClientInformation = db.caseReportClientInformationDao().findByCaseId(case_id);
                    caseReportDescriptionOfTheCaseProblem = db.caseReportDescriptionOfTheCaseProblemDao().findByCaseId(case_id);
                    caseReportNeedsAssesment = db.caseReportNeedsAssesmentDao().findByCaseId(case_id);
                    caseReportNextOfKin = db.caseReportNextOfKinDao().findByCaseId(case_id);
                    caseReportCareGiver = db.caseReportCareGiverDao().findByCaseId(case_id);
                    caseReportParentsGuardiansSpousesInformation = db.caseReportParentsGuardiansSpousesInformationDao().findByCaseId(case_id);
                    if (
                            caseReport == null ||
                                    caseReportClientInformation == null ||
                                    caseReportDescriptionOfTheCaseProblem == null ||
                                    caseReportNeedsAssesment == null ||
                                    caseReportNextOfKin == null ||
                                    caseReportCareGiver == null ||
                                    caseReportParentsGuardiansSpousesInformation == null
                    )
                        Log.e("critical", "there is a null value");
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", "error fetching casereport: " + ex.getMessage());
        }
        pd.hide();

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        viewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Case Report");
        db = AppDatabase.getAppDatabase(this);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.case_report_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_case_report:
                //delete case report and close activity

                new AlertDialog.Builder(this)
                        .setTitle("Confirmation")
                        .setMessage("Delete record?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                deleteRecord();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

                break;
            case R.id.upload_case_report:

                new AlertDialog.Builder(this)
                        .setTitle("Confirmation")
                        .setMessage("Upload record?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                upLoadRecord();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            case R.id.save_case_report:
                BasicInformationFragment.saveRecord();
                ClientInformationFragment.saveRecord();
                DescriptionOfCaseFragment.saveRecord();
                NeedsAssesmentFragment.saveRecord();
                NextOfKinFragment.saveRecord();
                CareGiverInformationFragment.saveRecord();
                PGSInformationFragment.saveRecord();
                Toast.makeText(this, "Record Saved", Toast.LENGTH_LONG).show();
                break;
            case R.id.open_justification_reports:
                Intent showListJustificationReports = new Intent(CreateEditCaseReportActivity.context, ListJustificationReportsActivity.class);
                startActivity(showListJustificationReports);
                break;
            case R.id.open_payments_to_beneficiaries:
                Intent showListPaymentsToBeneficiaries = new Intent(CreateEditCaseReportActivity.context, ListPaymentsToBeneficiariesActivity.class);
                startActivity(showListPaymentsToBeneficiaries);
                break;
            default:
                break;
        }
        return true;
    }

    private void upLoadRecord() {
        //ansure save first then upload
        BasicInformationFragment.saveRecord();
        ClientInformationFragment.saveRecord();
        DescriptionOfCaseFragment.saveRecord();
        NeedsAssesmentFragment.saveRecord();
        NextOfKinFragment.saveRecord();
        CareGiverInformationFragment.saveRecord();
        PGSInformationFragment.saveRecord();

        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //pull fresh from db
                        caseReport = db.caseReportDao().findByCaseId(case_id);
                        caseReportClientInformation = db.caseReportClientInformationDao().findByCaseId(case_id);
                        caseReportDescriptionOfTheCaseProblem = db.caseReportDescriptionOfTheCaseProblemDao().findByCaseId(case_id);
                        caseReportNeedsAssesment = db.caseReportNeedsAssesmentDao().findByCaseId(case_id);
                        caseReportNextOfKin = db.caseReportNextOfKinDao().findByCaseId(case_id);
                        caseReportCareGiver = db.caseReportCareGiverDao().findByCaseId(case_id);
                        caseReportParentsGuardiansSpousesInformation = db.caseReportParentsGuardiansSpousesInformationDao().findByCaseId(case_id);

                        ObjectMapper jsonMapper = new ObjectMapper();

                        __caseReport = jsonMapper.writeValueAsString(caseReport);
                        __caseReportClientInformation = jsonMapper.writeValueAsString(caseReportClientInformation);
                        __caseReportDescriptionOfTheCaseProblem = jsonMapper.writeValueAsString(caseReportDescriptionOfTheCaseProblem);
                        __caseReportNeedsAssesment = jsonMapper.writeValueAsString(caseReportNeedsAssesment);
                        __caseReportNextOfKin = jsonMapper.writeValueAsString(caseReportNextOfKin);
                        __caseReportCareGiver = jsonMapper.writeValueAsString(caseReportCareGiver);
                        __caseReportParentsGuardiansSpousesInformation = jsonMapper.writeValueAsString(caseReportParentsGuardiansSpousesInformation);

                    } catch (Exception ex) {
                        Log.e("kzzex", ex.getMessage());
                    }
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }

        pd.setTitle("Please wait...");
        pd.show();
        Ion.with(context)
                .load("POST", globals.api_end_point + "/mobile_api/v1/UpdateCaseReport")
                .setBodyParameter("__caseReport", __caseReport)
                .setBodyParameter("__caseReportClientInformation", __caseReportClientInformation)
                .setBodyParameter("__caseReportDescriptionOfTheCaseProblem", __caseReportDescriptionOfTheCaseProblem)
                .setBodyParameter("__caseReportNeedsAssesment", __caseReportNeedsAssesment)
                .setBodyParameter("__caseReportNextOfKin", __caseReportNextOfKin)
                .setBodyParameter("__caseReportCareGiver", __caseReportCareGiver)
                .setBodyParameter("__caseReportParentsGuardiansSpousesInformation", __caseReportParentsGuardiansSpousesInformation)
                .asString()
                .setCallback((e, result) -> {
                    if (e != null) {
                        Toast.makeText(CreateEditCaseReportActivity.this, "Error occurred", Toast.LENGTH_LONG).show();
                        Log.e("kzzex", e.getMessage());
                    }
                    if (result == null) {
                        Toast.makeText(CreateEditCaseReportActivity.this, "Error occurred", Toast.LENGTH_LONG).show();
                        Log.e("kzzex", "result is null");
                    } else {
                        try {
                            JSONObject jresult = new JSONObject(result);
                            String res = jresult.get("res").toString();
                            String msg = jresult.get("msg").toString();
                            if (res.equals("ok")) {
                                Thread t = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        caseReport.Uploaded = true;
                                        caseReport.SavedAtLeastOnce = true;
                                        db.caseReportDao().update(caseReport);
                                    }
                                });
                                t.start();
                                t.join();
                                Toast.makeText(CreateEditCaseReportActivity.this, "Record Uploaded", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(CreateEditCaseReportActivity.this, "Error: " + msg, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            Log.e("kzzex", ex.getMessage());
                        }
                    }
                    pd.hide();
                });
    }

    private void deleteRecord() {
        try {
            pd.setTitle("Please wait...");
            pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    CaseReportClientInformation caseReportClientInformation = db.caseReportClientInformationDao().findByCaseId(case_id);
                    if (caseReportClientInformation != null) {
                        db.caseReportClientInformationDao().delete(caseReportClientInformation);
                    }

                    CaseReportDescriptionOfTheCaseProblem caseReportDescriptionOfTheCaseProblem = db.caseReportDescriptionOfTheCaseProblemDao().findByCaseId(case_id);
                    if (caseReportDescriptionOfTheCaseProblem != null) {
                        db.caseReportDescriptionOfTheCaseProblemDao().delete(caseReportDescriptionOfTheCaseProblem);
                    }
                    CaseReportNeedsAssesment caseReportNeedsAssesment = db.caseReportNeedsAssesmentDao().findByCaseId(case_id);
                    if (caseReportNeedsAssesment != null) {
                        db.caseReportNeedsAssesmentDao().delete(caseReportNeedsAssesment);
                    }

                    CaseReportNextOfKin caseReportNextOfKin = db.caseReportNextOfKinDao().findByCaseId(case_id);
                    if (caseReportNextOfKin != null) {
                        db.caseReportNextOfKinDao().delete(caseReportNextOfKin);
                    }

                    CaseReportParentsGuardiansSpousesInformation caseReportParentsGuardiansSpousesInformation = db.caseReportParentsGuardiansSpousesInformationDao().findByCaseId(case_id);
                    if (caseReportParentsGuardiansSpousesInformation != null) {
                        db.caseReportParentsGuardiansSpousesInformationDao().delete(caseReportParentsGuardiansSpousesInformation);
                    }

                    CaseReport caseReport = db.caseReportDao().findByCaseId(case_id);
                    if (caseReport != null) {
                        db.caseReportDao().delete(db.caseReportDao().findByCaseId(case_id));
                    }
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }

        pd.hide();
        Toast.makeText(this, "Record Deleted", Toast.LENGTH_LONG).show();
        this.finish();
    }

}