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
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import Adapters.CaseReportAdapter;
import Database.AppDatabase;
import Models.CaseReport;
import Models.CaseReportCareGiver;
import Models.CaseReportBeneficiaryInformation;
import Models.CaseReportDescriptionOfTheCaseProblem;
import Models.CaseReportNeedsAssesment;
import Models.CaseReportNextOfKin;
import Models.CaseReportParentsGuardiansSpousesInformation;

public class ListCasesActivity extends AppCompatActivity {

    public static RecyclerView list_case_reports;
    public static List<CaseReport> caseReports;
    ProgressDialog pd;
    public static CaseReportAdapter adapter;
    Toolbar toolbar;
    LinearLayout ll_empty;
    EditText txt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cases);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_search = (EditText) findViewById(R.id.txt_search);
        ll_empty = (LinearLayout) findViewById(R.id.ll_empty);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Case Reports");

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCaseReportsList();

    }

    private void loadCaseReportsList() {
        caseReports = new ArrayList<>();
        pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();
        AppDatabase db = AppDatabase.getAppDatabase(this);
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<CaseReport> _caseReports = db.caseReportDao().findAll();
                    for (CaseReport caseReport : _caseReports
                    ) {
                        if (!caseReport.SavedAtLeastOnce) {
                            //remove this case report, so that no blanks are showing up
                            db.caseReportClientInformationDao().delete(db.caseReportClientInformationDao().findByCaseId(caseReport.Id));
                            db.caseReportDescriptionOfTheCaseProblemDao().delete(db.caseReportDescriptionOfTheCaseProblemDao().findByCaseId(caseReport.Id));
                            db.caseReportNeedsAssesmentDao().delete(db.caseReportNeedsAssesmentDao().findByCaseId(caseReport.Id));
                            db.caseReportNextOfKinDao().delete(db.caseReportNextOfKinDao().findByCaseId(caseReport.Id));
                            db.caseReportCareGiverDao().delete(db.caseReportCareGiverDao().findByCaseId(caseReport.Id));
                            db.caseReportParentsGuardiansSpousesInformationDao().delete(db.caseReportParentsGuardiansSpousesInformationDao().findByCaseId(caseReport.Id));
                            db.caseReportDao().delete(caseReport);
                        } else {
                            caseReports.add(caseReport);
                        }
//                        caseReports.add(caseReport);
                    }
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());

        }
        pd.hide();

        if (caseReports.size() == 0) {
            ll_empty.setVisibility(View.VISIBLE);
            txt_search.setVisibility(View.GONE);
        } else {
            ll_empty.setVisibility(View.GONE);
            txt_search.setVisibility(View.VISIBLE);
        }

        adapter = new CaseReportAdapter(caseReports, this);
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
                            AppDatabase db = AppDatabase.getAppDatabase(ListCasesActivity.this);
                            long case_report_id = db.caseReportDao().insert(new CaseReport());
                            CaseReport caseReport = db.caseReportDao().findById(case_report_id);
                            String case_id = caseReport.Id;
                            db.caseReportClientInformationDao().insert(new CaseReportBeneficiaryInformation(case_id));
                            db.caseReportDescriptionOfTheCaseProblemDao().insert(new CaseReportDescriptionOfTheCaseProblem(case_id));
                            db.caseReportNeedsAssesmentDao().insert(new CaseReportNeedsAssesment(case_id));
                            db.caseReportNextOfKinDao().insert(new CaseReportNextOfKin(case_id));
                            db.caseReportCareGiverDao().insert(new CaseReportCareGiver(case_id));
                            db.caseReportParentsGuardiansSpousesInformationDao().insert(new CaseReportParentsGuardiansSpousesInformation(case_id));
                            CreateEditCaseReportActivity.case_id = case_id;
                            db = null;
                        }
                    });
                    t.start();
                    t.join();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getLocalizedMessage());
                }
                pd.hide();

                Intent intent = new Intent(ListCasesActivity.this, CreateEditCaseReportActivity.class);
                startActivity(intent);

                break;

            default:
                break;
        }
        return true;
    }
}