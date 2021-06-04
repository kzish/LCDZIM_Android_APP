package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapters.CaseReportAdapter;
import Database.AppDatabase;
import Models.CaseReport;

public class ListCasesActivity extends AppCompatActivity {

    public static RecyclerView list_case_reports;
    public static List<CaseReport> caseReports;
    ProgressDialog pd;
    public static CaseReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cases);


    }

    @Override
    protected void onResume() {
        super.onResume();

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
                            //remove this case report
                            db.caseReportClientInformationDao().delete(db.caseReportClientInformationDao().findByCaseId(caseReport.IdAtClient));
                            db.caseReportDescriptionOfTheCaseProblemDao().delete(db.caseReportDescriptionOfTheCaseProblemDao().findByCaseId(caseReport.IdAtClient));
                            db.caseReportNeedsAssesmentDao().delete(db.caseReportNeedsAssesmentDao().findByCaseId(caseReport.IdAtClient));
                            db.caseReportNextOfKinDao().delete(db.caseReportNextOfKinDao().findByCaseId(caseReport.IdAtClient));
                            db.caseReportParentsGuardiansSpousesInformationDao().delete(db.caseReportParentsGuardiansSpousesInformationDao().findByCaseId(caseReport.IdAtClient));
                            db.caseReportDao().delete(caseReport);
                        } else {
                            caseReports.add(caseReport);
                        }

                    }
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("ex", ex.getMessage());

        }
        pd.hide();


        adapter = new CaseReportAdapter(caseReports, this);
        list_case_reports = (RecyclerView) findViewById(R.id.list_case_reports);
        list_case_reports.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        list_case_reports.setHasFixedSize(true);
        list_case_reports.setLayoutManager(new LinearLayoutManager(this));
        list_case_reports.setAdapter(adapter);
    }
}