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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    TextView txt_CaseNumber;
    String toolbar_title = "";
    Long created_item_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_justification_reports);
        pd =  new ProgressDialog(this);
        txt_CaseNumber  = (TextView)findViewById(R.id.txt_CaseNumber);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ll_empty = (LinearLayout) findViewById(R.id.ll_empty);
        setSupportActionBar(toolbar);
        pd.setTitle("Loading...");
        pd.show();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditRecordActivity.context);
                    CaseReport caseReport  = db.caseReportDao().findByCaseId(CreateEditRecordActivity.case_id);
                    if(!caseReport.CaseNumber.equals("")){
                        toolbar_title+= "Case Number: (" + caseReport.CaseNumber + ")";
                    }
                    else{
                        toolbar_title ="";
                    }
                }
            });
            t.start();
            t.join();
            pd.hide();
        }catch (Exception ex)
        {
            Log.e("kzzex",ex.getMessage());
        }
        txt_CaseNumber.setText(toolbar_title);
        getSupportActionBar().setTitle("Justification Reports");
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
                    List<CaseReportJustificationReportForAttendedCases> _caseReportJustificationReportForAttendedCases = db.caseReportJustificationReportForAttendedCasesDao().findAllByCaseId(CreateEditRecordActivity.case_id);
                    for (CaseReportJustificationReportForAttendedCases caseReportJustificationReportForAttendedCases :_caseReportJustificationReportForAttendedCases
                    ) {
                        if (!caseReportJustificationReportForAttendedCases.SavedAtLeastOnce) {
                            //remove this case report, so that no blanks are showing up
                            db.caseReportJustificationReportForAttendedCasesDao().delete(db.caseReportJustificationReportForAttendedCasesDao().findById(caseReportJustificationReportForAttendedCases._Id));
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
        getMenuInflater().inflate(R.menu.list_case_report_justification_records_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_case_report_justification_report:

                pd.setTitle("Loading...");
                pd.show();
                //create and save a case report
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getAppDatabase(CreateEditRecordActivity.context);
                            CaseReportJustificationReportForAttendedCases caseReportJustificationReportForAttendedCases = new CaseReportJustificationReportForAttendedCases(CreateEditRecordActivity.case_id);
                            created_item_id = db.caseReportJustificationReportForAttendedCasesDao().insert(caseReportJustificationReportForAttendedCases);
                        }
                    });
                    t.start();
                    t.join();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getLocalizedMessage());
                }
                pd.hide();

                Intent intent = new Intent(ListJustificationReportsActivity.this, CreateEditJustificationReportActivity.class);
                intent.putExtra("created_item_id",created_item_id);
                startActivity(intent);
                break;

            default:
                break;
        }
        return true;
    }

}