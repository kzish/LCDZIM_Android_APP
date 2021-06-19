package CaseReportFragments;

import android.app.DatePickerDialog;
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

import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.R;

import java.io.CharArrayReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Database.AppDatabase;

public class BeneficiaryInformationFragment extends Fragment {

    public static boolean fragment_can_save; //is this fragment active yet?

    public BeneficiaryInformationFragment() {
    }

    public static BeneficiaryInformationFragment newInstance(String param1, String param2) {
        BeneficiaryInformationFragment fragment = new BeneficiaryInformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    static EditText txt_NameOfClient;
    static EditText txt_Dob;
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
        fragment_can_save = true;
        View view = inflater.inflate(R.layout.fragment_beneficiary_information, container, false);

        final Calendar myCalendar = Calendar.getInstance();
        //
        txt_NameOfClient = (EditText) view.findViewById(R.id.txt_NameOfClient);
        txt_Dob = (EditText) view.findViewById(R.id.txt_Dob);
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
        AppDatabase db = AppDatabase.getAppDatabase(getContext());

        txt_NameOfClient.setText(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.NameOfClient);
        txt_Dob.setText(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.Dob);

        txt_ClientsAddress.setText(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.NameOfClient);
        txt_PhoneNumberHome.setText(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.NameOfClient);
        txt_Mobile.setText(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.NameOfClient);
        try {
            chk_disability_hi.setChecked(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.DescriptionOfDisability.contains("hi"));
            chk_disability_mr.setChecked(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.DescriptionOfDisability.contains("mr"));
            chk_disability_vi.setChecked(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.DescriptionOfDisability.contains("vi"));
            chk_disability_physical.setChecked(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.DescriptionOfDisability.contains("physical"));
            chk_disability_cosmetic.setChecked(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.DescriptionOfDisability.contains("cosmetic"));

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
            if (val.equals(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.Sex)) {
                txt_Sex.setSelection(i);
            }
        }
        for (int i = 0; i < txt_LevelOfEducation.getAdapter().getCount(); i++) {
            String val = txt_LevelOfEducation.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditCaseReportActivity.caseReportBeneficiaryInformation.LevelOfEducation)) {
                txt_LevelOfEducation.setSelection(i);
            }
        }

        return view;
    }

    public static void saveRecord() {
        if(!fragment_can_save)return;
        CreateEditCaseReportActivity.caseReportBeneficiaryInformation.NameOfClient = txt_NameOfClient.getText().toString();
        CreateEditCaseReportActivity.caseReportBeneficiaryInformation.Dob = txt_Dob.getText().toString();
        CreateEditCaseReportActivity.caseReportBeneficiaryInformation.Sex = txt_Sex.getSelectedItem().toString();
        CreateEditCaseReportActivity.caseReportBeneficiaryInformation.LevelOfEducation = txt_LevelOfEducation.getSelectedItem().toString();
        CreateEditCaseReportActivity.caseReportBeneficiaryInformation.ClientsAddress = txt_ClientsAddress.getText().toString();
        CreateEditCaseReportActivity.caseReportBeneficiaryInformation.PhoneNumberHome = txt_PhoneNumberHome.getText().toString();
        CreateEditCaseReportActivity.caseReportBeneficiaryInformation.Mobile = txt_Mobile.getText().toString();
        CreateEditCaseReportActivity.caseReportBeneficiaryInformation.DescriptionOfDisability = "";//clear old and save

        for (String item : chosen_disability_items
        ) {
            CreateEditCaseReportActivity.caseReportBeneficiaryInformation.DescriptionOfDisability += (item + ",");

        }
        CreateEditCaseReportActivity.caseReportBeneficiaryInformation.GiveDetailsOfTheDisability = txt_GiveDetailsOfTheDisability.getText().toString();

        try {
            CreateEditCaseReportActivity.pd.setTitle("Saving...");
            CreateEditCaseReportActivity.pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseReportActivity.context);
                    db.caseReportClientInformationDao().update(CreateEditCaseReportActivity.caseReportBeneficiaryInformation);

                    //update search fields in caseReport
                    CreateEditCaseReportActivity.caseReport._beneficiary_name = txt_NameOfClient.getText().toString();
                    db.caseReportDao().update(CreateEditCaseReportActivity.caseReport);
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