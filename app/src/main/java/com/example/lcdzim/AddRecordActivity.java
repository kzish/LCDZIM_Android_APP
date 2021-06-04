package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import CaseReportFragments.BasicInformationFragment;
import CaseReportFragments.ClientInformationFragment;
import CaseReportFragments.DescriptionOfCaseFragment;
import CaseReportFragments.NeedsAssesmentFragment;
import CaseReportFragments.NextOfKinFragment;
import CaseReportFragments.PGSInformationFragment;
import CaseReportFragments.ViewPagerAdapter;
import Database.AppDatabase;
import Globals.globals;
import Models.CaseReport;
import Models.CaseReportClientInformation;
import Models.CaseReportDescriptionOfTheCaseProblem;
import Models.CaseReportNeedsAssesment;
import Models.CaseReportNextOfKin;
import Models.CaseReportParentsGuardiansSpousesInformation;

public class AddRecordActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    Toolbar toolbar;

    public static long case_id;//current case id, always set this
    public static Context context;
    AppDatabase db;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_add_record);
        context = this;

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        viewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);

        db = AppDatabase.getAppDatabase(this);
        pd = new ProgressDialog(this);

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

                try {
                    pd.setTitle("Please wait...");
                    pd.show();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            db.caseReportClientInformationDao().delete(db.caseReportClientInformationDao().findByCaseId(case_id));
                            db.caseReportDescriptionOfTheCaseProblemDao().delete(db.caseReportDescriptionOfTheCaseProblemDao().findByCaseId(case_id));
                            db.caseReportNeedsAssesmentDao().delete(db.caseReportNeedsAssesmentDao().findByCaseId(case_id));
                            db.caseReportNextOfKinDao().delete(db.caseReportNextOfKinDao().findByCaseId(case_id));
                            db.caseReportParentsGuardiansSpousesInformationDao().delete(db.caseReportParentsGuardiansSpousesInformationDao().findByCaseId(case_id));
                            db.caseReportDao().delete(db.caseReportDao().find(case_id));
                        }
                    });
                    t.start();
                    t.join();
                } catch (Exception ex) {
                    Log.e("ex", ex.getMessage());
                }

                pd.hide();
                Toast.makeText(this, "Record Deleted", Toast.LENGTH_LONG).show();
                this.finish();
                break;
            case R.id.upload_case_report:
                CaseReport caseReport = db.caseReportDao().find(case_id);
                CaseReportClientInformation caseReportClientInformation = db.caseReportClientInformationDao().findByCaseId(case_id);
                CaseReportDescriptionOfTheCaseProblem caseReportDescriptionOfTheCaseProblem = db.caseReportDescriptionOfTheCaseProblemDao().findByCaseId(case_id);
                CaseReportNeedsAssesment caseReportNeedsAssesment = db.caseReportNeedsAssesmentDao().findByCaseId(case_id);
                CaseReportNextOfKin caseReportNextOfKin = db.caseReportNextOfKinDao().findByCaseId(case_id);
                CaseReportParentsGuardiansSpousesInformation caseReportParentsGuardiansSpousesInformation = db.caseReportParentsGuardiansSpousesInformationDao().findByCaseId(case_id);

                ObjectMapper jsonMapper = new ObjectMapper();

                JsonObject json = new JsonObject();
                try {
                    json.addProperty("caseReport", jsonMapper.writeValueAsString(caseReport));
                    json.addProperty("caseReportClientInformation", jsonMapper.writeValueAsString(caseReportClientInformation));
                    json.addProperty("caseReportDescriptionOfTheCaseProblem", jsonMapper.writeValueAsString(caseReportDescriptionOfTheCaseProblem));
                    json.addProperty("caseReportNeedsAssesment", jsonMapper.writeValueAsString(caseReportNeedsAssesment));
                    json.addProperty("caseReportNextOfKin", jsonMapper.writeValueAsString(caseReportNextOfKin));
                    json.addProperty("caseReportParentsGuardiansSpousesInformation", jsonMapper.writeValueAsString(caseReportParentsGuardiansSpousesInformation));
                }catch (Exception ex){
                   Log.e("ex",ex.getMessage());
                }

                pd.setTitle("Please wait...");
                pd.show();
                Log.e("json",json.toString());
                Ion.with(context)
                        .load(globals.api_end_point+"/mobile_api/v1/UpdateCaseReport")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                Log.e("res",result.getAsString());

                                Toast.makeText(AddRecordActivity.this, "upload", Toast.LENGTH_LONG).show();
                                pd.hide();
                            }
                        });
                break;
            case R.id.save_case_report:
                BasicInformationFragment.saveRecord();
                ClientInformationFragment.saveRecord();
                DescriptionOfCaseFragment.saveRecord();
                NeedsAssesmentFragment.saveRecord();
                NextOfKinFragment.saveRecord();
                PGSInformationFragment.saveRecord();
                Toast.makeText(this, "Record Saved", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }

}