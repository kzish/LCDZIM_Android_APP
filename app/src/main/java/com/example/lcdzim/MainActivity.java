package com.example.lcdzim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import Database.AppDatabase;
import Models.CaseReport;
import Models.CaseReportCareGiver;
import Models.CaseReportClientInformation;
import Models.CaseReportDescriptionOfTheCaseProblem;
import Models.CaseReportNeedsAssesment;
import Models.CaseReportNextOfKin;
import Models.CaseReportParentsGuardiansSpousesInformation;

import static com.example.lcdzim.CreateEditRecordActivity.case_id;

public class MainActivity extends AppCompatActivity {

    ImageView img_add_record;
    ImageView img_manage_records;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_add_record = (ImageView) findViewById(R.id.img_add_record);
        img_manage_records = (ImageView) findViewById(R.id.img_manage_records);

        img_manage_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewCasesIntent = new Intent(MainActivity.this, ListCasesActivity.class);
                startActivity(viewCasesIntent);
            }
        });

        AppDatabase db = AppDatabase.getAppDatabase(MainActivity.this);


        pd = new ProgressDialog(this);

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
                        }
                    });
                    t.start();
                    t.join();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getLocalizedMessage());
                }
                pd.hide();

                Intent intent = new Intent(MainActivity.this, CreateEditRecordActivity.class);
                startActivity(intent);
            }
        });
    }
}