package CaseReportFragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.R;

import Database.AppDatabase;

public class NeedsAssesmentFragment extends Fragment {

    public static boolean fragment_can_save; //is this fragment active yet?

    public NeedsAssesmentFragment() {
    }

    public static NeedsAssesmentFragment newInstance(String param1, String param2) {
        NeedsAssesmentFragment fragment = new NeedsAssesmentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    static Spinner txt_WhereDoesTheBeneficiaryLive;
    static Spinner txt_WhoIsTakingCareOfTheBeneficiary;
    static Spinner IsTheBeneficiaryAbleToVerballyCommunicate;
    static Spinner txt_IsTheBeneficiaryAbleToMoveIndependently;
    static Spinner txt_BeneficiaryWellBeingAtThePointOfIntake;
    static EditText txt_IfNotWhatAssistanceIsNeededMovement;
    static EditText txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify;
    static EditText txt_BeneficiaryImmediateConcerns;
    static EditText txt_OtherRelevantIssues;
    static EditText txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify;
    static EditText txt_HowManyPeopleLiveWithTheBeneficiary;
    static EditText txt_HowManyPeopleLiveWithTheBeneficiaryRelation;
    static EditText txt_IfNoHowDoesHeSheCommunicate;
    static LinearLayout layout_cannot_communicate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment_can_save = true;
        View view = inflater.inflate(R.layout.fragment_needs_assesment, container, false);
        txt_WhereDoesTheBeneficiaryLive = (Spinner) view.findViewById(R.id.txt_WhereDoesTheBeneficiaryLive);
        txt_WhoIsTakingCareOfTheBeneficiary = (Spinner) view.findViewById(R.id.txt_WhoIsTakingCareOfTheBeneficiary);
        IsTheBeneficiaryAbleToVerballyCommunicate = (Spinner) view.findViewById(R.id.IsTheBeneficiaryAbleToVerballyCommunicate);
        txt_IsTheBeneficiaryAbleToMoveIndependently = (Spinner) view.findViewById(R.id.txt_IsTheBeneficiaryAbleToMoveIndependently);
        txt_BeneficiaryWellBeingAtThePointOfIntake = (Spinner) view.findViewById(R.id.txt_BeneficiaryWellBeingAtThePointOfIntake);
        txt_IfNotWhatAssistanceIsNeededMovement = (EditText) view.findViewById(R.id.txt_IfNotWhatAssistanceIsNeededMovement);
        txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify = (EditText) view.findViewById(R.id.txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify);
        txt_BeneficiaryImmediateConcerns = (EditText) view.findViewById(R.id.txt_BeneficiaryImmediateConcerns);
        txt_OtherRelevantIssues = (EditText) view.findViewById(R.id.txt_OtherRelevantIssues);
        txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify = (EditText) view.findViewById(R.id.txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify);
        txt_HowManyPeopleLiveWithTheBeneficiary = (EditText) view.findViewById(R.id.txt_HowManyPeopleLiveWithTheBeneficiary);
        txt_HowManyPeopleLiveWithTheBeneficiaryRelation = (EditText) view.findViewById(R.id.txt_HowManyPeopleLiveWithTheBeneficiaryRelation);
        txt_IfNoHowDoesHeSheCommunicate = (EditText) view.findViewById(R.id.txt_IfNoHowDoesHeSheCommunicate);
        layout_cannot_communicate = (LinearLayout) view.findViewById(R.id.layout_cannot_communicate);
        //

        txt_WhoIsTakingCareOfTheBeneficiary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = txt_WhoIsTakingCareOfTheBeneficiary.getSelectedItem().toString();
                if (selected.equals("Other")) {
                    txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify.setVisibility(View.VISIBLE);
                } else {
                    txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        txt_BeneficiaryWellBeingAtThePointOfIntake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = txt_BeneficiaryWellBeingAtThePointOfIntake.getSelectedItem().toString();
                if (selected.equals("Other")) {
                    txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify.setVisibility(View.VISIBLE);
                } else {
                    txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        IsTheBeneficiaryAbleToVerballyCommunicate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = IsTheBeneficiaryAbleToVerballyCommunicate.getSelectedItem().toString();
                if (selected.equals("No")) {
                    layout_cannot_communicate.setVisibility(View.VISIBLE);
                } else {
                    layout_cannot_communicate.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        txt_IsTheBeneficiaryAbleToMoveIndependently.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = txt_IsTheBeneficiaryAbleToMoveIndependently.getSelectedItem().toString();
                if (selected.equals("No")) {
                    txt_IfNotWhatAssistanceIsNeededMovement.setVisibility(View.VISIBLE);
                } else {
                    txt_IfNotWhatAssistanceIsNeededMovement.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        //
        txt_IsTheBeneficiaryAbleToMoveIndependently = (Spinner) view.findViewById(R.id.txt_IsTheBeneficiaryAbleToMoveIndependently);
        txt_BeneficiaryWellBeingAtThePointOfIntake = (Spinner) view.findViewById(R.id.txt_BeneficiaryWellBeingAtThePointOfIntake);

        txt_IfNotWhatAssistanceIsNeededMovement.setText(CreateEditCaseReportActivity.caseReportNeedsAssesment.IfNotWhatAssistanceIsNeededMovement);
        txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify.setText(CreateEditCaseReportActivity.caseReportNeedsAssesment.BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify);
        txt_BeneficiaryImmediateConcerns.setText(CreateEditCaseReportActivity.caseReportNeedsAssesment.BeneficiaryImmediateConcerns);
        txt_OtherRelevantIssues.setText(CreateEditCaseReportActivity.caseReportNeedsAssesment.OtherRelevantIssues);
        txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify.setText(CreateEditCaseReportActivity.caseReportNeedsAssesment.WhoIsTakingCareOfTheBeneficiaryOtherSpecify);
        txt_HowManyPeopleLiveWithTheBeneficiary.setText(CreateEditCaseReportActivity.caseReportNeedsAssesment.HowManyPeopleLiveWithTheBeneficiary + "");
        txt_HowManyPeopleLiveWithTheBeneficiaryRelation.setText(CreateEditCaseReportActivity.caseReportNeedsAssesment.HowManyPeopleLiveWithTheBeneficiaryRelation);
        txt_IfNoHowDoesHeSheCommunicate.setText(CreateEditCaseReportActivity.caseReportNeedsAssesment.IfNoHowDoesHeSheCommunicate);

        for (int i = 0; i < txt_WhereDoesTheBeneficiaryLive.getAdapter().getCount(); i++) {
            String val = txt_WhereDoesTheBeneficiaryLive.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditCaseReportActivity.caseReportNeedsAssesment.WhereDoesTheBeneficiaryLive)) {
                txt_WhereDoesTheBeneficiaryLive.setSelection(i);
            }
        }
        for (int i = 0; i < txt_WhoIsTakingCareOfTheBeneficiary.getAdapter().getCount(); i++) {
            String val = txt_WhoIsTakingCareOfTheBeneficiary.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditCaseReportActivity.caseReportNeedsAssesment.WhoIsTakingCareOfTheBeneficiary)) {
                txt_WhoIsTakingCareOfTheBeneficiary.setSelection(i);
            }
        }
        for (int i = 0; i < IsTheBeneficiaryAbleToVerballyCommunicate.getAdapter().getCount(); i++) {
            String val = IsTheBeneficiaryAbleToVerballyCommunicate.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditCaseReportActivity.caseReportNeedsAssesment.IsTheBeneficiaryAbleToVerballyCommunicate)) {
                IsTheBeneficiaryAbleToVerballyCommunicate.setSelection(i);
            }
        }
        for (int i = 0; i < txt_IsTheBeneficiaryAbleToMoveIndependently.getAdapter().getCount(); i++) {
            String val = txt_IsTheBeneficiaryAbleToMoveIndependently.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditCaseReportActivity.caseReportNeedsAssesment.IsTheBeneficiaryAbleToMoveIndependently)) {
                txt_IsTheBeneficiaryAbleToMoveIndependently.setSelection(i);
            }
        }
        for (int i = 0; i < txt_BeneficiaryWellBeingAtThePointOfIntake.getAdapter().getCount(); i++) {
            String val = txt_BeneficiaryWellBeingAtThePointOfIntake.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditCaseReportActivity.caseReportNeedsAssesment.BeneficiaryWellBeingAtThePointOfIntake)) {
                txt_BeneficiaryWellBeingAtThePointOfIntake.setSelection(i);
            }
        }


        return view;

    }

    public static void saveRecord() {
        if(!fragment_can_save)return;
        CreateEditCaseReportActivity.caseReportNeedsAssesment.WhereDoesTheBeneficiaryLive = txt_WhereDoesTheBeneficiaryLive.getSelectedItem().toString();
        CreateEditCaseReportActivity.caseReportNeedsAssesment.WhoIsTakingCareOfTheBeneficiary = txt_WhoIsTakingCareOfTheBeneficiary.getSelectedItem().toString();
        CreateEditCaseReportActivity.caseReportNeedsAssesment.WhoIsTakingCareOfTheBeneficiaryOtherSpecify = txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify.getText().toString();
        CreateEditCaseReportActivity.caseReportNeedsAssesment.HowManyPeopleLiveWithTheBeneficiary = Integer.parseInt(txt_HowManyPeopleLiveWithTheBeneficiary.getText().toString());
        CreateEditCaseReportActivity.caseReportNeedsAssesment.HowManyPeopleLiveWithTheBeneficiaryRelation = txt_HowManyPeopleLiveWithTheBeneficiaryRelation.getText().toString();
        CreateEditCaseReportActivity.caseReportNeedsAssesment.IsTheBeneficiaryAbleToVerballyCommunicate = IsTheBeneficiaryAbleToVerballyCommunicate.getSelectedItem().toString().equals("Yes");
        CreateEditCaseReportActivity.caseReportNeedsAssesment.IfNoHowDoesHeSheCommunicate = txt_IfNoHowDoesHeSheCommunicate.getText().toString();
        CreateEditCaseReportActivity.caseReportNeedsAssesment.IsTheBeneficiaryAbleToMoveIndependently = txt_IsTheBeneficiaryAbleToMoveIndependently.getSelectedItem().toString().equals("Yes");
        CreateEditCaseReportActivity.caseReportNeedsAssesment.IfNotWhatAssistanceIsNeededMovement = txt_IfNotWhatAssistanceIsNeededMovement.getText().toString();
        CreateEditCaseReportActivity.caseReportNeedsAssesment.BeneficiaryWellBeingAtThePointOfIntake = txt_BeneficiaryWellBeingAtThePointOfIntake.getSelectedItem().toString();
        CreateEditCaseReportActivity.caseReportNeedsAssesment.BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify = txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify.getText().toString();
        CreateEditCaseReportActivity.caseReportNeedsAssesment.BeneficiaryImmediateConcerns = txt_BeneficiaryImmediateConcerns.getText().toString();
        CreateEditCaseReportActivity.caseReportNeedsAssesment.OtherRelevantIssues = txt_OtherRelevantIssues.getText().toString();

        try {
            CreateEditCaseReportActivity.pd.setTitle("Saving...");
            CreateEditCaseReportActivity.pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseReportActivity.context);
                    db.caseReportNeedsAssesmentDao().update(CreateEditCaseReportActivity.caseReportNeedsAssesment);
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