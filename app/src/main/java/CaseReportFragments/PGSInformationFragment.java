package CaseReportFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lcdzim.AddRecordActivity;
import com.example.lcdzim.R;

import Database.AppDatabase;
import Models.CaseReport;
import Models.CaseReportClientInformation;
import Models.CaseReportParentsGuardiansSpousesInformation;

public class PGSInformationFragment extends Fragment {

    public PGSInformationFragment() {
    }

    public static PGSInformationFragment newInstance(String param1, String param2) {
        PGSInformationFragment fragment = new PGSInformationFragment();
        return fragment;
    }

    static EditText txt_Name;
    static EditText txt_Age;
    static EditText txt_Address;
    static EditText txt_PhoneNumber;
    static EditText txt_Email;
    static EditText txt_Occupation;
    static EditText txt_Employer;
    static EditText txt_MaritalStatusOtherSpecify;
    static Spinner txt_MaritalStatus;
    static CaseReportParentsGuardiansSpousesInformation caseReportParentsGuardiansSpousesInformation = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pgs_information, container, false);
        txt_Name = (EditText) view.findViewById(R.id.txt_Name);
        txt_MaritalStatus = (Spinner) view.findViewById(R.id.txt_MaritalStatus);
        txt_Age = (EditText) view.findViewById(R.id.txt_Age);
        txt_Address = (EditText) view.findViewById(R.id.txt_Address);
        txt_PhoneNumber = (EditText) view.findViewById(R.id.txt_PhoneNumber);
        txt_Email = (EditText) view.findViewById(R.id.txt_Email);
        txt_Occupation = (EditText) view.findViewById(R.id.txt_Occupation);
        txt_Employer = (EditText) view.findViewById(R.id.txt_Employer);
        txt_MaritalStatusOtherSpecify = (EditText) view.findViewById(R.id.txt_MaritalStatusOtherSpecify);

        txt_MaritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = txt_MaritalStatus.getSelectedItem().toString();
                if (selected.equals("Other")) {
                    txt_MaritalStatusOtherSpecify.setVisibility(View.VISIBLE);
                } else {
                    txt_MaritalStatusOtherSpecify.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        //
        Intent intent = getActivity().getIntent();
        long case_id = intent.getLongExtra("case_id", 0);
        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        //
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    caseReportParentsGuardiansSpousesInformation = db.caseReportParentsGuardiansSpousesInformationDao().findByCaseId(case_id);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("ex", ex.getMessage());
        }

        txt_Name.setText(caseReportParentsGuardiansSpousesInformation.Name);
        txt_Age.setText(caseReportParentsGuardiansSpousesInformation.Age + "");
        txt_Address.setText(caseReportParentsGuardiansSpousesInformation.Address);
        txt_PhoneNumber.setText(caseReportParentsGuardiansSpousesInformation.PhoneNumber);
        txt_Email.setText(caseReportParentsGuardiansSpousesInformation.Email);
        txt_Occupation.setText(caseReportParentsGuardiansSpousesInformation.Occupation);
        txt_Employer.setText(caseReportParentsGuardiansSpousesInformation.Employer);
        txt_MaritalStatusOtherSpecify.setText(caseReportParentsGuardiansSpousesInformation.MaritalStatusOtherSpecify);

        for (int i = 0; i < txt_MaritalStatus.getAdapter().getCount(); i++) {
            String val = txt_MaritalStatus.getAdapter().getItem(i).toString();
            if (val.equals(caseReportParentsGuardiansSpousesInformation.MaritalStatus)) {
                txt_MaritalStatus.setSelection(i);
            }
        }

        //
        return view;
    }

    public static void saveRecord() {
        if (caseReportParentsGuardiansSpousesInformation == null) return;
        caseReportParentsGuardiansSpousesInformation.CaseId = AddRecordActivity.case_id;
        caseReportParentsGuardiansSpousesInformation.Name = txt_Name.getText().toString();
        caseReportParentsGuardiansSpousesInformation.Age = Integer.parseInt(txt_Age.getText().toString());
        caseReportParentsGuardiansSpousesInformation.Address = txt_Address.getText().toString();
        caseReportParentsGuardiansSpousesInformation.PhoneNumber = txt_PhoneNumber.getText().toString();
        caseReportParentsGuardiansSpousesInformation.Email = txt_Email.getText().toString();
        caseReportParentsGuardiansSpousesInformation.Occupation = txt_Occupation.getText().toString();
        caseReportParentsGuardiansSpousesInformation.Employer = txt_Employer.getText().toString();
        caseReportParentsGuardiansSpousesInformation.MaritalStatus = txt_MaritalStatus.getSelectedItem().toString();
        caseReportParentsGuardiansSpousesInformation.MaritalStatusOtherSpecify = txt_MaritalStatusOtherSpecify.getText().toString();

        ProgressDialog pd = new ProgressDialog(AddRecordActivity.context);
        try {
            pd.setTitle("Saving...");
            pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(AddRecordActivity.context);
                    db.caseReportParentsGuardiansSpousesInformationDao().update(caseReportParentsGuardiansSpousesInformation);
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