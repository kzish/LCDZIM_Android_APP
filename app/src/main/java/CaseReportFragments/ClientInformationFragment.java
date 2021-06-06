package CaseReportFragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.anurag.multiselectionspinner.MultiSelectionSpinnerDialog;
import com.anurag.multiselectionspinner.MultiSpinner;
import com.example.lcdzim.AddRecordActivity;
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

    static EditText txt_NameOfClient;
    static EditText txt_Dob;
    static EditText txt_Age;
    static Spinner txt_Sex;
    static Spinner txt_LevelOfEducation;
    static EditText txt_ClientsAddress;
    static EditText txt_PhoneNumberHome;
    static EditText txt_Mobile;
    static List<String> chosen_disability_items = new ArrayList<>();
    static CheckBox chk_disability_hi;
    static CheckBox chk_disability_mr;
    static CheckBox chk_disability_vi;
    static CheckBox chk_disability_physical;
    static CheckBox chk_disability_cosmetic;
    static EditText txt_GiveDetailsOfTheDisability;

    static CaseReportClientInformation caseReportClientInformation = null;

    private void setChosen_disability_items() {
        if (chk_disability_cosmetic.isChecked()) {
            if (!chosen_disability_items.contains("cosmetic")) {
                chosen_disability_items.add("cosmetic");
            }
        } else {
            chosen_disability_items.remove("cosmetic");
        }

        if (chk_disability_physical.isChecked()) {
            if (!chosen_disability_items.contains("physical")) {
                chosen_disability_items.add("physical");
            }
        } else {
            chosen_disability_items.remove("physical");
        }

        if (chk_disability_vi.isChecked()) {
            if (!chosen_disability_items.contains("vi")) {
                chosen_disability_items.add("vi");
            }
        } else {
            chosen_disability_items.remove("vi");
        }

        if (chk_disability_hi.isChecked()) {
            if (!chosen_disability_items.contains("hi")) {
                chosen_disability_items.add("hi");
            }
        } else {
            chosen_disability_items.remove("hi");
        }

        if (chk_disability_mr.isChecked()) {
            if (!chosen_disability_items.contains("mr")) {
                chosen_disability_items.add("mr");
            }
        } else {
            chosen_disability_items.remove("mr");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_information, container, false);

        final Calendar myCalendar = Calendar.getInstance();
        //
        txt_NameOfClient = (EditText) view.findViewById(R.id.txt_NameOfClient);
        txt_Dob = (EditText) view.findViewById(R.id.txt_Dob);
        txt_Age = (EditText) view.findViewById(R.id.txt_Age);
        txt_Sex = (Spinner) view.findViewById(R.id.txt_Sex);
        txt_LevelOfEducation = (Spinner) view.findViewById(R.id.txt_LevelOfEducation);
        txt_ClientsAddress = (EditText) view.findViewById(R.id.txt_ClientsAddress);
        txt_PhoneNumberHome = (EditText) view.findViewById(R.id.txt_PhoneNumberHome);
        txt_Mobile = (EditText) view.findViewById(R.id.txt_Mobile);
        chk_disability_hi = (CheckBox) view.findViewById(R.id.chk_disability_hi);
        chk_disability_mr = (CheckBox) view.findViewById(R.id.chk_disability_mr);
        chk_disability_vi = (CheckBox) view.findViewById(R.id.chk_disability_vi);
        chk_disability_physical = (CheckBox) view.findViewById(R.id.chk_disability_physical);
        chk_disability_cosmetic = (CheckBox) view.findViewById(R.id.chk_disability_cosmetic);
        txt_GiveDetailsOfTheDisability = (EditText) view.findViewById(R.id.txt_GiveDetailsOfTheDisability);

        chk_disability_hi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setChosen_disability_items();
            }
        });
        chk_disability_mr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setChosen_disability_items();
            }
        });
        chk_disability_vi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setChosen_disability_items();
            }
        });
        chk_disability_physical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setChosen_disability_items();
            }
        });
        chk_disability_cosmetic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setChosen_disability_items();
            }
        });

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
        String case_id = intent.getStringExtra("case_id");
        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        //
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    caseReportClientInformation = db.caseReportClientInformationDao().findByCaseId(case_id);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }
        txt_NameOfClient.setText(caseReportClientInformation.NameOfClient);
        txt_Dob.setText(caseReportClientInformation.Dob);
        txt_Age.setText(caseReportClientInformation.Age + "");

        txt_ClientsAddress.setText(caseReportClientInformation.NameOfClient);
        txt_PhoneNumberHome.setText(caseReportClientInformation.NameOfClient);
        txt_Mobile.setText(caseReportClientInformation.NameOfClient);
        try {
            chk_disability_hi.setChecked(caseReportClientInformation.DescriptionOfDisability.contains("hi"));
            chk_disability_mr.setChecked(caseReportClientInformation.DescriptionOfDisability.contains("mr"));
            chk_disability_vi.setChecked(caseReportClientInformation.DescriptionOfDisability.contains("vi"));
            chk_disability_physical.setChecked(caseReportClientInformation.DescriptionOfDisability.contains("physical"));
            chk_disability_cosmetic.setChecked(caseReportClientInformation.DescriptionOfDisability.contains("cosmetic"));

            if (chk_disability_hi.isChecked() && !chosen_disability_items.contains("hi"))
                chosen_disability_items.add("hi");
            if (chk_disability_vi.isChecked() && !chosen_disability_items.contains("vi"))
                chosen_disability_items.add("vi");
            if (chk_disability_mr.isChecked() && !chosen_disability_items.contains("mr"))
                chosen_disability_items.add("mr");
            if (chk_disability_physical.isChecked() && !chosen_disability_items.contains("physical"))
                chosen_disability_items.add("physical");
            if (chk_disability_cosmetic.isChecked() && !chosen_disability_items.contains("cosmetic"))
                chosen_disability_items.add("cosmetic");

        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }

        for (int i = 0; i < txt_Sex.getAdapter().getCount(); i++) {
            String val = txt_Sex.getAdapter().getItem(i).toString();
            if (val.equals(caseReportClientInformation.Sex)) {
                txt_Sex.setSelection(i);
            }
        }
        for (int i = 0; i < txt_LevelOfEducation.getAdapter().getCount(); i++) {
            String val = txt_LevelOfEducation.getAdapter().getItem(i).toString();
            if (val.equals(caseReportClientInformation.LevelOfEducation)) {
                txt_LevelOfEducation.setSelection(i);
            }
        }

        return view;
    }

    public static void saveRecord() {
        if (caseReportClientInformation == null) return;
        caseReportClientInformation.CaseId = AddRecordActivity.case_id;
        caseReportClientInformation.NameOfClient = txt_NameOfClient.getText().toString();
        try {
            caseReportClientInformation.Dob = txt_Dob.getText().toString();
        } catch (Exception ex) {

        }
        caseReportClientInformation.Age = Integer.parseInt(txt_Age.getText().toString());
        caseReportClientInformation.Sex = txt_Sex.getSelectedItem().toString();
        caseReportClientInformation.LevelOfEducation = txt_LevelOfEducation.getSelectedItem().toString();
        caseReportClientInformation.ClientsAddress = txt_ClientsAddress.getText().toString();
        caseReportClientInformation.PhoneNumberHome = txt_PhoneNumberHome.getText().toString();
        caseReportClientInformation.Mobile = txt_Mobile.getText().toString();
        caseReportClientInformation.DescriptionOfDisability = "";//clear old and save
        for (String item : chosen_disability_items
        ) {
            caseReportClientInformation.DescriptionOfDisability += (item + ",");

        }
        caseReportClientInformation.GiveDetailsOfTheDisability = txt_GiveDetailsOfTheDisability.getText().toString();

        ProgressDialog pd = new ProgressDialog(AddRecordActivity.context);
        try {
            pd.setTitle("Saving...");
            pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(AddRecordActivity.context);
                    db.caseReportClientInformationDao().update(caseReportClientInformation);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        } finally {
            pd.hide();
        }

    }
}