package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import Dao.CaseReportDao;
import Database.AppDatabase;
import Globals.globals;
import Models.CaseReport;
import Models.CaseReportClientInformation;
import Models.CaseReportDescriptionOfTheCaseProblem;
import Models.CaseReportNeedsAssesment;
import Models.CaseReportNextOfKin;
import Models.CaseReportParentsGuardiansSpousesInformation;

public class MainActivity extends AppCompatActivity {

    ImageView img_add_record;
    ImageView img_manage_records;
    long case_id;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_add_record = (ImageView) findViewById(R.id.img_add_record);
        img_manage_records = (ImageView) findViewById(R.id.img_manage_records);

        AppDatabase db = AppDatabase.getAppDatabase(MainActivity.this);


        pd=new ProgressDialog(this);

        img_add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setTitle("Loading...");
                pd.show();
                //create and save a case report
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            case_id = db.caseReportDao().insert(new CaseReport());
                            db.caseReportClientInformationDao().insert(new CaseReportClientInformation(case_id));
                            db.caseReportDescriptionOfTheCaseProblemDao().insert(new CaseReportDescriptionOfTheCaseProblem(case_id));
                            db.caseReportNeedsAssesmentDao().insert(new CaseReportNeedsAssesment(case_id));
                            db.caseReportNextOfKinDao().insert(new CaseReportNextOfKin(case_id));
                            db.caseReportParentsGuardiansSpousesInformationDao().insert(new CaseReportParentsGuardiansSpousesInformation(case_id));
                            AddRecordActivity.case_id=case_id;
                        }
                    });
                    t.start();
                    t.join();
                } catch (Exception ex) {
                    Log.e("ex", ex.getLocalizedMessage());
                }
                pd.hide();

                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                intent.putExtra("case_id", case_id);
                startActivity(intent);
            }
        });
    }
}