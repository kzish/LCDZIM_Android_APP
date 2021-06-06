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
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.lcdzim.CreateEditRecordActivity;
import com.example.lcdzim.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Database.AppDatabase;
import Models.CaseReport;
import Models.CaseReportParentsGuardiansSpousesInformation;

public class BasicInformationFragment extends Fragment {

    public static boolean fragment_can_save; //is this fragment active yet?

    public BasicInformationFragment() {
    }

    public static BasicInformationFragment newInstance(String param1, String param2) {
        BasicInformationFragment fragment = new BasicInformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    static EditText txt_ReferredByNameAndInstitution;
    static EditText txt_PoliceStation;
    static EditText txt_CrRef;
    static EditText txt_NameOfInvestigatingOfficer;
    static EditText txt_MobileNumber;
    static EditText txt_CourtHandlingTheCase;
    static EditText txt_DateCaseWasReported;
    static EditText txt_ForceNumber;
    static EditText txt_CompiledBy;
    static EditText txt_DateCompiled;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment_can_save = true;
        View view = inflater.inflate(R.layout.fragment_basic_information, container, false);
        //
        txt_ReferredByNameAndInstitution = (EditText) view.findViewById(R.id.txt_ReferredByNameAndInstitution);
        txt_PoliceStation = (EditText) view.findViewById(R.id.txt_PoliceStation);
        txt_CrRef = (EditText) view.findViewById(R.id.txt_CrRef);
        txt_NameOfInvestigatingOfficer = (EditText) view.findViewById(R.id.txt_NameOfInvestigatingOfficer);
        txt_MobileNumber = (EditText) view.findViewById(R.id.txt_MobileNumber);
        txt_CourtHandlingTheCase = (EditText) view.findViewById(R.id.txt_CourtHandlingTheCase);
        txt_DateCaseWasReported = (EditText) view.findViewById(R.id.txt_DateCaseWasReported);
        txt_ForceNumber = (EditText) view.findViewById(R.id.txt_ForceNumber);
        txt_CompiledBy = (EditText) view.findViewById(R.id.txt_CompiledBy);
        txt_DateCompiled = (EditText) view.findViewById(R.id.txt_DateCompiled);
        //
        final Calendar myCalendar = Calendar.getInstance();
        //
        txt_DateCaseWasReported.setOnClickListener(new View.OnClickListener() {
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
                        txt_DateCaseWasReported.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        txt_DateCompiled.setOnClickListener(new View.OnClickListener() {
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
                        txt_DateCompiled.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        AppDatabase db = AppDatabase.getAppDatabase(getContext());

        progressDialog = new ProgressDialog(getActivity());

        //load data into the view
        txt_ReferredByNameAndInstitution.setText(CreateEditRecordActivity.caseReport.ReferredByNameAndInstitution);
        txt_PoliceStation.setText(CreateEditRecordActivity.caseReport.PoliceStation);
        txt_CrRef.setText(CreateEditRecordActivity.caseReport.CrRef);
        txt_NameOfInvestigatingOfficer.setText(CreateEditRecordActivity.caseReport.NameOfInvestigatingOfficer);
        txt_MobileNumber.setText(CreateEditRecordActivity.caseReport.MobileNumber);
        txt_CourtHandlingTheCase.setText(CreateEditRecordActivity.caseReport.CourtHandlingTheCase);
        if (CreateEditRecordActivity.caseReport.DateCaseWasReported != null) {
            txt_DateCaseWasReported.setText(CreateEditRecordActivity.caseReport.DateCaseWasReported);
        }
        txt_ForceNumber.setText(CreateEditRecordActivity.caseReport.ForceNumber);
        txt_CompiledBy.setText(CreateEditRecordActivity.caseReport.CompiledBy);
        if (CreateEditRecordActivity.caseReport.DateCompiled != null) {
            txt_DateCompiled.setText(CreateEditRecordActivity.caseReport.DateCompiled);
        }
        //
        return view;
    }

    public static void saveRecord() {

        if(!fragment_can_save)return;
        CreateEditRecordActivity.caseReport.Uploaded=false;
        CreateEditRecordActivity.caseReport.SavedAtLeastOnce = true;
        CreateEditRecordActivity.caseReport.date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        CreateEditRecordActivity.caseReport.ReferredByNameAndInstitution = txt_ReferredByNameAndInstitution.getText().toString();
        CreateEditRecordActivity.caseReport.PoliceStation = txt_PoliceStation.getText().toString();
        CreateEditRecordActivity.caseReport.CrRef = txt_CrRef.getText().toString();
        CreateEditRecordActivity.caseReport.NameOfInvestigatingOfficer = txt_NameOfInvestigatingOfficer.getText().toString();
        CreateEditRecordActivity.caseReport.MobileNumber = txt_MobileNumber.getText().toString();
        CreateEditRecordActivity.caseReport.CourtHandlingTheCase = txt_CourtHandlingTheCase.getText().toString();
        CreateEditRecordActivity.caseReport.DateCaseWasReported = txt_DateCaseWasReported.getText().toString();
        CreateEditRecordActivity.caseReport.ForceNumber = txt_ForceNumber.getText().toString();
        CreateEditRecordActivity.caseReport.CompiledBy = txt_CompiledBy.getText().toString();
        CreateEditRecordActivity.caseReport.DateCompiled = txt_DateCompiled.getText().toString();

        ProgressDialog pd = new ProgressDialog(CreateEditRecordActivity.context);
        try {
            pd.setTitle("Saving...");
            pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditRecordActivity.context);
                    db.caseReportDao().update(CreateEditRecordActivity.caseReport);
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