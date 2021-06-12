package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import Adapters.CaseReportAdapter;
import Adapters.CaseReportJustificationReportAdapter;
import Database.AppDatabase;
import Models.CaseReport;
import Models.CaseReportCareGiver;
import Models.CaseReportClientInformation;
import Models.CaseReportDescriptionOfTheCaseProblem;
import Models.CaseReportJustificationReportForAttendedCases;
import Models.CaseReportNeedsAssesment;
import Models.CaseReportNextOfKin;
import Models.CaseReportParentsGuardiansSpousesInformation;

public class ListJustificationReportsActivity extends AppCompatActivity {

    public static RecyclerView list_case_reports;
    public static List<CaseReportJustificationReportForAttendedCases> caseReportJustificationReportForAttendedCasess;
    ProgressDialog pd;
    public static CaseReportJustificationReportAdapter adapter;
    Toolbar toolbar;
    LinearLayout ll_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_justification_reports);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ll_empty = (LinearLayout) findViewById(R.id.ll_empty);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        caseReportJustificationReportForAttendedCasess = new ArrayList<>();
        pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();
        AppDatabase db = AppDatabase.getAppDatabase(this);
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<CaseReportJustificationReportForAttendedCases> _caseReportJustificationReportForAttendedCases = db.caseReportJustificationReportForAttendedCasesDao().findAll();
                    for (CaseReportJustificationReportForAttendedCases caseReportJustificationReportForAttendedCases :_caseReportJustificationReportForAttendedCases
                    ) {
                        if (!caseReportJustificationReportForAttendedCases.SavedAtLeastOnce) {
                            //remove this case report, so that no blanks are showing up
                            db.caseReportJustificationReportForAttendedCasesDao().delete(db.caseReportJustificationReportForAttendedCasesDao().findByCaseId(CreateEditRecordActivity.case_id));
                        } else {
                            caseReportJustificationReportForAttendedCasess.add(caseReportJustificationReportForAttendedCases);
                        }
                    }
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());

        }
        pd.hide();

        if(caseReportJustificationReportForAttendedCasess.size()==0){
            ll_empty.setVisibility(View.VISIBLE);
        }else {
            ll_empty.setVisibility(View.GONE);
        }

        adapter = new CaseReportJustificationReportAdapter(caseReportJustificationReportForAttendedCasess, this);
        list_case_reports = (RecyclerView) findViewById(R.id.list_case_reports);
        list_case_reports.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        list_case_reports.setHasFixedSize(true);
        list_case_reports.setLayoutManager(new LinearLayoutManager(this));
        list_case_reports.setAdapter(adapter);
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_case_report_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_case_report:

                pd.setTitle("Loading...");
                pd.show();
                //create and save a case report
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getAppDatabase(CreateEditRecordActivity.context);
                            long case_report_id = db.caseReportDao().insert(new CaseReport());
                            CaseReport caseReport = db.caseReportDao().findById(case_report_id);
                            String case_id = caseReport.Id;
                            db.caseReportClientInformationDao().insert(new CaseReportClientInformation(case_id));
                            db.caseReportDescriptionOfTheCaseProblemDao().insert(new CaseReportDescriptionOfTheCaseProblem(case_id));
                            db.caseReportNeedsAssesmentDao().insert(new CaseReportNeedsAssesment(case_id));
                            db.caseReportNextOfKinDao().insert(new CaseReportNextOfKin(case_id));
                            db.caseReportCareGiverDao().insert(new CaseReportCareGiver(case_id));
                            db.caseReportParentsGuardiansSpousesInformationDao().insert(new CaseReportParentsGuardiansSpousesInformation(case_id));
                            CreateEditRecordActivity.case_id = case_id;
                            db = null;
                        }
                    });
                    t.start();
                    t.join();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getLocalizedMessage());
                }
                pd.hide();

                Intent intent = new Intent(CreateEditRecordActivity.context, ListCasesActivity.class);
                startActivity(intent);

                break;

            default:
                break;
        }
        return true;
    }
}