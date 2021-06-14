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

import java.util.ArrayList;
import java.util.List;

import Adapters.CaseReportJustificationReportAdapter;
import Adapters.CaseReportPaymentsToBeneficiariesAdapter;
import Database.AppDatabase;
import Models.CaseReport;
import Models.CaseReportJustificationReportForAttendedCases;
import Models.CaseReportPaymentsToBeneficiaries;

public class ListPaymentsToBeneficiariesActivity extends AppCompatActivity {

    public static RecyclerView list_case_reports;
    public static List<CaseReportPaymentsToBeneficiaries> caseReportPaymentsToBeneficiariess;
    ProgressDialog pd;
    public static CaseReportPaymentsToBeneficiariesAdapter adapter;
    Toolbar toolbar;
    LinearLayout ll_empty;
    TextView txt_CaseNumber;
    String toolbar_title = "";
    Long created_item_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_payments_to_beneficiaries);
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
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseReportActivity.context);
                    CaseReport caseReport  = db.caseReportDao().findByCaseId(CreateEditCaseReportActivity.case_id);
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
        getSupportActionBar().setTitle("Payments to Beneficiaries");
    }

    @Override
    protected void onResume() {
        super.onResume();

        caseReportPaymentsToBeneficiariess = new ArrayList<>();
        pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();
        AppDatabase db = AppDatabase.getAppDatabase(this);
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<CaseReportPaymentsToBeneficiaries> _caseReportPaymentsToBeneficiaries = db.caseReportPaymentsToBeneficiariesDao().findAllByCaseId(CreateEditCaseReportActivity.case_id);
                    for (CaseReportPaymentsToBeneficiaries caseReportPaymentsToBeneficiaries :_caseReportPaymentsToBeneficiaries
                    ) {
                        if (!caseReportPaymentsToBeneficiaries.SavedAtLeastOnce) {
                            //remove this case report, so that no blanks are showing up
                            db.caseReportPaymentsToBeneficiariesDao().delete(db.caseReportPaymentsToBeneficiariesDao().findById(caseReportPaymentsToBeneficiaries._Id));
                        } else {
                            caseReportPaymentsToBeneficiariess.add(caseReportPaymentsToBeneficiaries);
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

        if(caseReportPaymentsToBeneficiariess.size()==0){
            ll_empty.setVisibility(View.VISIBLE);
        }else {
            ll_empty.setVisibility(View.GONE);
        }

        adapter = new CaseReportPaymentsToBeneficiariesAdapter(caseReportPaymentsToBeneficiariess, this);
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
        getMenuInflater().inflate(R.menu.list_case_report_payments_to_beneficiaries_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_case_report_payment_to_beneficiary:

                pd.setTitle("Loading...");
                pd.show();
                //create and save a case report
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseReportActivity.context);
                            CaseReportPaymentsToBeneficiaries caseReportPaymentsToBeneficiaries = new CaseReportPaymentsToBeneficiaries(CreateEditCaseReportActivity.case_id);
                            created_item_id = db.caseReportPaymentsToBeneficiariesDao().insert(caseReportPaymentsToBeneficiaries);
                            db=null;
                        }
                    });
                    t.start();
                    t.join();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getLocalizedMessage());
                }
                pd.hide();

                Intent intent = new Intent(ListPaymentsToBeneficiariesActivity.this, CreateEditPaymentsToBeneficiaryActivity.class);
                intent.putExtra("created_item_id",created_item_id);
                startActivity(intent);
                break;

            default:
                break;
        }
        return true;
    }
}