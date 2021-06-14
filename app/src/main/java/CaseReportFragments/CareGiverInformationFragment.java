package CaseReportFragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Database.AppDatabase;

public class CareGiverInformationFragment extends Fragment {

    public static boolean fragment_can_save; //is this fragment active yet?

    public CareGiverInformationFragment() {
    }

    public static CareGiverInformationFragment newInstance(String param1, String param2) {
        CareGiverInformationFragment fragment = new CareGiverInformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    static EditText txt_CareGiverName;
    static EditText txt_CareGiverDob;
    static Spinner txt_CareGiverSex;
    static EditText txt_CareGiverPhoneNumber;
    static EditText txt_CareGiverAddress;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment_can_save = true;
        View view = inflater.inflate(R.layout.fragment_care_giver_information, container, false);
        //
        txt_CareGiverName = (EditText) view.findViewById(R.id.txt_CareGiverName);
        txt_CareGiverDob = (EditText) view.findViewById(R.id.txt_CareGiverDob);
        txt_CareGiverSex = (Spinner) view.findViewById(R.id.txt_CareGiverSex);
        txt_CareGiverPhoneNumber = (EditText) view.findViewById(R.id.txt_CareGiverPhoneNumber);
        txt_CareGiverAddress = (EditText) view.findViewById(R.id.txt_CareGiverAddress);
        //
        final Calendar myCalendar = Calendar.getInstance();
        //
        txt_CareGiverDob.setOnClickListener(new View.OnClickListener() {
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
                        txt_CareGiverDob.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        AppDatabase db = AppDatabase.getAppDatabase(getContext());

        progressDialog = new ProgressDialog(getActivity());

        //load data into the view
        txt_CareGiverName.setText(CreateEditCaseReportActivity.caseReportCareGiver.CareGiverName);
        txt_CareGiverDob.setText(CreateEditCaseReportActivity.caseReportCareGiver.CareGiverDob);
        txt_CareGiverAddress.setText(CreateEditCaseReportActivity.caseReportCareGiver.CareGiverAddress);
        txt_CareGiverPhoneNumber.setText(CreateEditCaseReportActivity.caseReportCareGiver.CareGiverPhoneNumber);

        for (int i = 0; i < txt_CareGiverSex.getAdapter().getCount(); i++) {
            String val = txt_CareGiverSex.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditCaseReportActivity.caseReportCareGiver.CareGiverSex)) {
                txt_CareGiverSex.setSelection(i);
            }
        }

        //
        return view;
    }

    public static void saveRecord() {

        if (!fragment_can_save) return;
        CreateEditCaseReportActivity.caseReportCareGiver.CareGiverName = txt_CareGiverName.getText().toString();
        CreateEditCaseReportActivity.caseReportCareGiver.CareGiverDob = txt_CareGiverDob.getText().toString();
        CreateEditCaseReportActivity.caseReportCareGiver.CareGiverSex = txt_CareGiverSex.getSelectedItem().toString();
        CreateEditCaseReportActivity.caseReportCareGiver.CareGiverAddress = txt_CareGiverAddress.getText().toString();
        CreateEditCaseReportActivity.caseReportCareGiver.CareGiverPhoneNumber = txt_CareGiverPhoneNumber.getText().toString();

        ProgressDialog pd = new ProgressDialog(CreateEditCaseReportActivity.context);
        try {
            pd.setTitle("Saving...");
            pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseReportActivity.context);
                    db.caseReportCareGiverDao().update(CreateEditCaseReportActivity.caseReportCareGiver);
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