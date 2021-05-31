package CaseReportFragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.anurag.multiselectionspinner.MultiSelectionSpinnerDialog;
import com.anurag.multiselectionspinner.MultiSpinner;
import com.example.lcdzim.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Database.AppDatabase;
import Models.CaseReport;
import Models.CaseReportClientInformation;

public class ClientInformationFragment extends Fragment {

    public ClientInformationFragment() {
    }

    public static ClientInformationFragment newInstance(String param1, String param2) {
        ClientInformationFragment fragment = new ClientInformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    EditText txt_NameOfClient;
    EditText txt_Dob;
    EditText txt_Age;
    Spinner txt_Sex;
    Spinner txt_LevelOfEducation;
    EditText txt_ClientsAddress;
    EditText txt_PhoneNumberHome;
    EditText txt_Mobile;
    List<String> chosen_disability_items;
    CheckBox chk_disability_hi;
    CheckBox chk_disability_mr;
    CheckBox chk_disability_vi;
    CheckBox chk_disability_physical;
    CheckBox chk_disability_cosmetic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_information, container, false);

        final Calendar myCalendar = Calendar.getInstance();

        //
        chosen_disability_items = new ArrayList<>();
        txt_NameOfClient = (EditText)view.findViewById(R.id.txt_NameOfClient);
        txt_Dob = (EditText)view.findViewById(R.id.txt_Dob);
        txt_Age = (EditText)view.findViewById(R.id.txt_Age);
        txt_Sex = (Spinner) view.findViewById(R.id.txt_Sex);
        txt_LevelOfEducation = (Spinner)view.findViewById(R.id.txt_LevelOfEducation);
        txt_ClientsAddress = (EditText)view.findViewById(R.id.txt_ClientsAddress);
        txt_PhoneNumberHome = (EditText)view.findViewById(R.id.txt_PhoneNumberHome);
        txt_Mobile = (EditText)view.findViewById(R.id.txt_Mobile);
        chk_disability_hi = (CheckBox)view .findViewById(R.id.chk_disability_hi);
        chk_disability_mr = (CheckBox)view .findViewById(R.id.chk_disability_mr);
        chk_disability_vi = (CheckBox)view .findViewById(R.id.chk_disability_vi);
        chk_disability_physical = (CheckBox)view .findViewById(R.id.chk_disability_physical);
        chk_disability_cosmetic = (CheckBox)view .findViewById(R.id.chk_disability_cosmetic);

        txt_Dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        txt_Dob.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //
        Intent intent = getActivity().getIntent();
        long case_id = intent.getLongExtra("case_id",0);
        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        //
        CaseReportClientInformation caseReportClientInformation = db.caseReportClientInformationDao().findByCaseId(case_id);

        return view;
    }
}