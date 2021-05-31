package CaseReportFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

    EditText txt_Name;
    EditText txt_Age;
    EditText txt_Address;
    EditText txt_PhoneNumber;
    EditText txt_Email;
    EditText txt_Occupation;
    EditText txt_Employer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_next_of_kin, container, false);


        //
        Intent intent = getActivity().getIntent();
        long case_id = intent.getLongExtra("case_id", 0);
        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        //
        CaseReportNextOfKin caseReportNextOfKin = db.caseReportNextOfKinDao().findByCaseId(case_id);


        //
        return view;
    }
}