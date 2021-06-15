package CaseReportFragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lcdzim.CreateEditCasePlanActivity;
import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Database.AppDatabase;
import Models.CaseReportCasePlanAndFollowUp;

public class CasePlanAndFollowUpFragment extends Fragment {

    static Spinner txt_StageCaseRefered;
    static TextView txt_ActionTakenByAnyoneDateThisFormWasCompleted;
    static TextView txt_AnyOtherImportantNotes;
    static TextView txt_StateOtherNeedsOfTheBeneficiaryForReferalToOtherServiceProviders;
    static TextView txt_CompiledBy;
    static TextView txt_ApprovedBy;
    static TextView txt_ApprovedBySignature;
    static TextView txt_Date;

    ProgressDialog pd;

    public CasePlanAndFollowUpFragment() {
    }

    public static CasePlanAndFollowUpFragment newInstance(String param1, String param2) {
        CasePlanAndFollowUpFragment fragment = new CasePlanAndFollowUpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_case_plan_and_follow_up, container, false);
        txt_StageCaseRefered = (Spinner) view.findViewById(R.id.txt_StageCaseRefered);
        txt_ActionTakenByAnyoneDateThisFormWasCompleted = (TextView) view.findViewById(R.id.txt_ActionTakenByAnyoneDateThisFormWasCompleted);
        txt_AnyOtherImportantNotes = (TextView) view.findViewById(R.id.txt_AnyOtherImportantNotes);
        txt_StateOtherNeedsOfTheBeneficiaryForReferalToOtherServiceProviders = (TextView) view.findViewById(R.id.txt_StateOtherNeedsOfTheBeneficiaryForReferalToOtherServiceProviders);
        txt_CompiledBy = (TextView) view.findViewById(R.id.txt_CompiledBy);
        txt_ApprovedBy = (TextView) view.findViewById(R.id.txt_ApprovedBy);
        txt_Date = (TextView) view.findViewById(R.id.txt_Date);

        pd = new ProgressDialog(getActivity());
        final Calendar myCalendar = Calendar.getInstance();
        txt_Date.setOnClickListener(new View.OnClickListener() {
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
                        txt_Date.setText(sdf.format(myCalendar.getTime()));
                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        for (int i = 0; i < txt_StageCaseRefered.getAdapter().getCount(); i++) {
            String val = txt_StageCaseRefered.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.StageCaseRefered)) {
                txt_StageCaseRefered.setSelection(i);
            }
        }
        txt_ActionTakenByAnyoneDateThisFormWasCompleted.setText(CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.ActionTakenByAnyoneDateThisFormWasCompleted);
        txt_AnyOtherImportantNotes.setText(CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.AnyOtherImportantNotes);
        txt_StateOtherNeedsOfTheBeneficiaryForReferalToOtherServiceProviders.setText(CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.StateOtherNeedsOfTheBeneficiaryForReferalToOtherServiceProviders);
        txt_CompiledBy.setText(CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.CompiledBy);
        txt_ApprovedBy.setText(CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.ApprovedBy);
        txt_Date.setText(CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.Date);

        return view;
    }

    public static void saveCasePlan() {
        CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.StageCaseRefered = txt_StageCaseRefered.getSelectedItem().toString();
        CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.ActionTakenByAnyoneDateThisFormWasCompleted = txt_ActionTakenByAnyoneDateThisFormWasCompleted.getText().toString();
        CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.AnyOtherImportantNotes = txt_AnyOtherImportantNotes.getText().toString();
        CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.StateOtherNeedsOfTheBeneficiaryForReferalToOtherServiceProviders = txt_StateOtherNeedsOfTheBeneficiaryForReferalToOtherServiceProviders.getText().toString();
        CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.CompiledBy = txt_CompiledBy.getText().toString();
        CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.ApprovedBy = txt_ApprovedBy.getText().toString();
        CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp.Date = txt_Date.getText().toString();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseReportActivity.context);
                    db.caseReportCasePlanAndFollowUpDao().update(CreateEditCasePlanActivity.caseReportCasePlanAndFollowUp);
                    db = null;
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }

    }

}