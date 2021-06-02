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

import java.util.List;

import Adapters.CaseReportAdapter;
import Database.AppDatabase;
import Models.CaseReport;

public class ListCasesActivity extends AppCompatActivity {

    RecyclerView list_case_reports;
    List<CaseReport> caseReports;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cases);

        pd= new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();
        AppDatabase db = AppDatabase.getAppDatabase(this);
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    caseReports = db.caseReportDao().findAll();

                }
            });
            t.start();
            t.join();
        }catch (Exception ex){
            Log.e("ex",ex.getMessage());

        }
        pd.hide();

        CaseReportAdapter adapter = new CaseReportAdapter(caseReports,this);
        list_case_reports = (RecyclerView) findViewById(R.id.list_case_reports);
        list_case_reports.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        list_case_reports.setHasFixedSize(true);
        list_case_reports.setLayoutManager(new LinearLayoutManager(this));
        list_case_reports.setAdapter(adapter);


    }
}