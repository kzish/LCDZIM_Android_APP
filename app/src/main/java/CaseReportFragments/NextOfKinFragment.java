package CaseReportFragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.R;

import Database.AppDatabase;

public class NextOfKinFragment extends Fragment {

    public static boolean fragment_can_save; //is this fragment active yet?

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment_can_save = true;
        View view = inflater.inflate(R.layout.fragment_next_of_kin, container, false);

        txt_Name = (EditText) view.findViewById(R.id.txt_Name);
        txt_Age = (EditText) view.findViewById(R.id.txt_Age);
        txt_Address = (EditText) view.findViewById(R.id.txt_Address);
        txt_PhoneNumber = (EditText) view.findViewById(R.id.txt_PhoneNumber);
        txt_Email = (EditText) view.findViewById(R.id.txt_Email);
        txt_Occupation = (EditText) view.findViewById(R.id.txt_Occupation);
        txt_Employer = (EditText) view.findViewById(R.id.txt_Employer);


        txt_Name.setText(CreateEditCaseReportActivity.caseReportNextOfKin.Name);
        txt_Age.setText(CreateEditCaseReportActivity.caseReportNextOfKin.Age + "");
        txt_Address.setText(CreateEditCaseReportActivity.caseReportNextOfKin.Address);
        txt_PhoneNumber.setText(CreateEditCaseReportActivity.caseReportNextOfKin.PhoneNumber);
        txt_Email.setText(CreateEditCaseReportActivity.caseReportNextOfKin.Email);
        txt_Occupation.setText(CreateEditCaseReportActivity.caseReportNextOfKin.Occupation);
        txt_Employer.setText(CreateEditCaseReportActivity.caseReportNextOfKin.Employer);
        //
        return view;
    }

    public static void saveRecord() {
        if (!fragment_can_save) return;
        CreateEditCaseReportActivity.caseReportNextOfKin.Name = txt_Name.getText().toString();
        CreateEditCaseReportActivity.caseReportNextOfKin.Age = Integer.parseInt(txt_Age.getText().toString());
        CreateEditCaseReportActivity.caseReportNextOfKin.Address = txt_Address.getText().toString();
        CreateEditCaseReportActivity.caseReportNextOfKin.PhoneNumber = txt_PhoneNumber.getText().toString();
        CreateEditCaseReportActivity.caseReportNextOfKin.Email = txt_Email.getText().toString();
        CreateEditCaseReportActivity.caseReportNextOfKin.Occupation = txt_Occupation.getText().toString();
        CreateEditCaseReportActivity.caseReportNextOfKin.Employer = txt_Employer.getText().toString();

        try {
            CreateEditCaseReportActivity.pd.setTitle("Saving...");
            CreateEditCaseReportActivity.pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseReportActivity.context);
                    db.caseReportNextOfKinDao().update(CreateEditCaseReportActivity.caseReportNextOfKin);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        } finally {
            CreateEditCaseReportActivity.pd.hide();
        }
    }

}