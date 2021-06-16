package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import CaseReportFragments.CasePlanAndFollowUpFragment;
import CaseReportFragments.ViewPagerAdapterCasePlan;
import CaseReportFragments.ViewPagerAdapterCaseReport;
import Database.AppDatabase;
import Models.CaseReport;
import Models.CaseReportCasePlanAndFollowUp;

public class CreateEditCasePlanActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapterCasePlan viewPagerAdapterCasePlan;
    Toolbar toolbar;

    public static String case_id;//current case id, always set this before calling this activity
    public static Context context;

    ProgressDialog pd;
    public static CaseReportCasePlanAndFollowUp caseReportCasePlanAndFollowUp;
    String __caseReportCasePlanAndFollowUp;


    TextView txt_CaseNumber;
    String toolbar_title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_case_plan);

        pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();


        txt_CaseNumber = (TextView) findViewById(R.id.txt_CaseNumber);

        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCasePlanActivity.this);
                    CaseReport caseReport = db.caseReportDao().findByCaseId(CreateEditCaseReportActivity.case_id);
                    if (!caseReport.CaseNumber.equals("")) {
                        toolbar_title += "Case Number: (" + caseReport.CaseNumber + ")";
                    } else {
                        toolbar_title = "";
                    }
                    caseReportCasePlanAndFollowUp = db.caseReportCasePlanAndFollowUpDao().findByCaseId(CreateEditCaseReportActivity.case_id);
                    if (caseReportCasePlanAndFollowUp == null) {
                        caseReportCasePlanAndFollowUp = new CaseReportCasePlanAndFollowUp(CreateEditCaseReportActivity.case_id);
                        db.caseReportCasePlanAndFollowUpDao().insert(caseReportCasePlanAndFollowUp);
                    }
                    db = null;
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", "error fetching caseReportCasePlanAndFollowUp: " + ex.getMessage());
        }
        txt_CaseNumber.setText(toolbar_title);
        pd.hide();
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        viewPagerAdapterCasePlan = new ViewPagerAdapterCasePlan(
                getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterCasePlan);

        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Case Plan");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.case_plan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_case_plan:
                //delete case workplan and sub-tables and close activity
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getAppDatabase(CreateEditCasePlanActivity.this);
                            db.casePlanCaseLogDao().deleteAllByCaseId(caseReportCasePlanAndFollowUp.CaseId);
                            db.casePlanCaseWorkPlanDao().deleteAllByCaseId(caseReportCasePlanAndFollowUp.CaseId);
                            db.caseReportCasePlanAndFollowUpDao().delete(caseReportCasePlanAndFollowUp);
                            db = null;
                        }
                    });
                    t.start();
                    t.join();
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getMessage());
                }
                finish();
                break;
            case R.id.save_case_plan:
                //save case plan
                try {
                    CasePlanAndFollowUpFragment.saveCasePlan();
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getMessage());
                }
                break;

            default:
                break;
        }
        return true;
    }

}