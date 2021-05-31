package CaseReportFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lcdzim.R;

public class PGSInformationFragment extends Fragment {

    public PGSInformationFragment() {
    }

    public static PGSInformationFragment newInstance(String param1, String param2) {
        PGSInformationFragment fragment = new PGSInformationFragment();
        return fragment;
    }

    EditText txt_Name;
    EditText txt_Age;
    EditText txt_Address;
    EditText txt_PhoneNumber;
    EditText txt_Email;
    EditText txt_Occupation;
    EditText txt_Employer;
    EditText txt_MaritalStatusOtherSpecify;
    Spinner txt_MaritalStatus;

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
        return view;
    }
}