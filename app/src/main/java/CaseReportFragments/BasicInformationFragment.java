package CaseReportFragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lcdzim.AddRecordActivity;
import com.example.lcdzim.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Database.AppDatabase;
import Models.CaseReport;

public class BasicInformationFragment extends Fragment {

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
    static CaseReport caseReport = null;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        Intent intent = getActivity().getIntent();
        long case_id = intent.getLongExtra("case_id", 0);
        AppDatabase db = AppDatabase.getAppDatabase(getContext());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading...");
        progressDialog.show();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    caseReport = db.caseReportDao().find(case_id);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("ex", ex.getMessage());
        }
        progressDialog.hide();

        txt_ReferredByNameAndInstitution.setText(caseReport.ReferredByNameAndInstitution);
        txt_PoliceStation.setText(caseReport.PoliceStation);
        txt_CrRef.setText(caseReport.CrRef);
        txt_NameOfInvestigatingOfficer.setText(caseReport.NameOfInvestigatingOfficer);
        txt_MobileNumber.setText(caseReport.MobileNumber);
        txt_CourtHandlingTheCase.setText(caseReport.CourtHandlingTheCase);
        if(caseReport.DateCaseWasReported!=null) {
            txt_DateCaseWasReported.setText(new SimpleDateFormat("yyyy-MM-dd").format(caseReport.DateCaseWasReported));
        }
        txt_ForceNumber.setText(caseReport.ForceNumber);
        txt_CompiledBy.setText(caseReport.CompiledBy);
        if(caseReport.DateCompiled!=null) {
            txt_DateCompiled.setText(new SimpleDateFormat("yyyy-MM-dd").format(caseReport.DateCompiled));
        }
        //
        return view;
    }

    public static void saveRecord() {
        if (caseReport == null) {
            return;
        }
        caseReport.date = Calendar.getInstance().getTime();
        caseReport.ReferredByNameAndInstitution = txt_ReferredByNameAndInstitution.getText().toString();
        caseReport.PoliceStation = txt_PoliceStation.getText().toString();
        caseReport.CrRef = txt_CrRef.getText().toString();
        caseReport.NameOfInvestigatingOfficer = txt_NameOfInvestigatingOfficer.getText().toString();
        caseReport.MobileNumber = txt_MobileNumber.getText().toString();
        caseReport.CourtHandlingTheCase = txt_CourtHandlingTheCase.getText().toString();
        try {
            caseReport.DateCaseWasReported = new SimpleDateFormat("yyyy-MM-dd").parse(txt_DateCaseWasReported.getText().toString());
        } catch (Exception ex) {
        }
        caseReport.ForceNumber = txt_ForceNumber.getText().toString();
        caseReport.CompiledBy = txt_CompiledBy.getText().toString();
        try {
            caseReport.DateCompiled = new SimpleDateFormat("yyyy-MM-dd").parse(txt_DateCompiled.getText().toString());
        } catch (Exception ex) {
        }
        caseReport.SavedAtLeastOnce = true;

        ProgressDialog pd = new ProgressDialog(AddRecordActivity.context);
        try {
            pd.setTitle("Saving...");
            pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(AddRecordActivity.context);
                    db.caseReportDao().update(caseReport);
                }
            });
            t.start();
            t.join();
        }catch (Exception ex){
            Log.e("ex",ex.getMessage());
        }finally {
            pd.hide();
        }

    }
}