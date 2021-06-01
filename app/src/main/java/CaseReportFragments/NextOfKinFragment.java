package CaseReportFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lcdzim.AddRecordActivity;
import com.example.lcdzim.R;

import Database.AppDatabase;
import Models.CaseReport;
import Models.CaseReportClientInformation;
import Models.CaseReportNextOfKin;

public class NextOfKinFragment extends Fragment {

    public NextOfKinFragment() {
    }

    public static NextOfKinFragment newInstance(String param1, String param2) {
        NextOfKinFragment fragment = new NextOfKinFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    static EditText txt_Name;
    static EditText txt_Age;
    static EditText txt_Address;
    static EditText txt_PhoneNumber;
    static EditText txt_Email;
    static EditText txt_Occupation;
    static EditText txt_Employer;
    static CaseReportNextOfKin caseReportNextOfKin = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_next_of_kin, container, false);

        txt_Name = (EditText) view.findViewById(R.id.txt_Name);
        txt_Age = (EditText) view.findViewById(R.id.txt_Age);
        txt_Address = (EditText) view.findViewById(R.id.txt_Address);
        txt_PhoneNumber = (EditText) view.findViewById(R.id.txt_PhoneNumber);
        txt_Email = (EditText) view.findViewById(R.id.txt_Email);
        txt_Occupation = (EditText) view.findViewById(R.id.txt_Occupation);
        txt_Employer = (EditText) view.findViewById(R.id.txt_Employer);

        //
        Intent intent = getActivity().getIntent();
        long case_id = intent.getLongExtra("case_id", 0);
        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        //
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    caseReportNextOfKin = db.caseReportNextOfKinDao().findByCaseId(case_id);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("ex", ex.getMessage());
        }

        txt_Name.setText(caseReportNextOfKin.Name);
        txt_Age.setText(caseReportNextOfKin.Age + "");
        txt_Address.setText(caseReportNextOfKin.Address);
        txt_PhoneNumber.setText(caseReportNextOfKin.PhoneNumber);
        txt_Email.setText(caseReportNextOfKin.Email);
        txt_Occupation.setText(caseReportNextOfKin.Occupation);
        txt_Employer.setText(caseReportNextOfKin.Employer);
        //
        return view;
    }

    public static void saveRecord() {
        if (caseReportNextOfKin == null) return;
        caseReportNextOfKin.CaseId = AddRecordActivity.case_id;
        caseReportNextOfKin.Name = txt_Name.getText().toString();
        caseReportNextOfKin.Age = Integer.parseInt(txt_Age.getText().toString());
        caseReportNextOfKin.Address = txt_Address.getText().toString();
        caseReportNextOfKin.PhoneNumber = txt_PhoneNumber.getText().toString();
        caseReportNextOfKin.Email = txt_Email.getText().toString();
        caseReportNextOfKin.Occupation = txt_Occupation.getText().toString();
        caseReportNextOfKin.Employer = txt_Employer.getText().toString();

        ProgressDialog pd = new ProgressDialog(AddRecordActivity.context);
        try {
            pd.setTitle("Saving...");
            pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(AddRecordActivity.context);
                    db.caseReportNextOfKinDao().update(caseReportNextOfKin);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("ex", ex.getMessage());
        } finally {
            pd.hide();
        }
    }

}