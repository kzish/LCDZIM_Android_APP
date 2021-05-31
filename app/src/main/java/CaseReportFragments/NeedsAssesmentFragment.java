package CaseReportFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.lcdzim.R;

import Database.AppDatabase;
import Models.CaseReportClientInformation;
import Models.CaseReportNeedsAssesment;

public class NeedsAssesmentFragment extends Fragment {

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

    Spinner txt_WhereDoesTheBeneficiaryLive;
    Spinner txt_WhoIsTakingCareOfTheBeneficiary;
    Spinner IsTheBeneficiaryAbleToVerballyCommunicate;
    Spinner txt_IsTheBeneficiaryAbleToMoveIndependently;
    Spinner txt_BeneficiaryWellBeingAtThePointOfIntake;
    EditText txt_IfNotWhatAssistanceIsNeededMovement;
    EditText txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify;
    EditText txt_BeneficiaryImmediateConcerns;
    EditText txt_OtherRelevantIssues;
    EditText txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify;
    EditText txt_HowManyPeopleLiveWithTheBeneficiary;
    EditText txt_HowManyPeopleLiveWithTheBeneficiaryRelation;
    EditText txt_IfNoHowDoesHeSheCommunicate;
    LinearLayout layout_cannot_communicate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_needs_assesment, container, false);
        txt_WhereDoesTheBeneficiaryLive=(Spinner)view.findViewById(R.id.txt_WhereDoesTheBeneficiaryLive);
        txt_WhoIsTakingCareOfTheBeneficiary=(Spinner)view.findViewById(R.id.txt_WhoIsTakingCareOfTheBeneficiary);
        IsTheBeneficiaryAbleToVerballyCommunicate=(Spinner)view.findViewById(R.id.IsTheBeneficiaryAbleToVerballyCommunicate);
        txt_IsTheBeneficiaryAbleToMoveIndependently=(Spinner)view.findViewById(R.id.txt_IsTheBeneficiaryAbleToMoveIndependently);
        txt_BeneficiaryWellBeingAtThePointOfIntake=(Spinner)view.findViewById(R.id.txt_BeneficiaryWellBeingAtThePointOfIntake);
        txt_IfNotWhatAssistanceIsNeededMovement=(EditText) view.findViewById(R.id.txt_IfNotWhatAssistanceIsNeededMovement);
        txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify=(EditText) view.findViewById(R.id.txt_BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify);
        txt_BeneficiaryImmediateConcerns=(EditText) view.findViewById(R.id.txt_BeneficiaryImmediateConcerns);
        txt_OtherRelevantIssues=(EditText) view.findViewById(R.id.txt_OtherRelevantIssues);
        txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify=(EditText) view.findViewById(R.id.txt_WhoIsTakingCareOfTheBeneficiaryOtherSpecify);
        txt_HowManyPeopleLiveWithTheBeneficiary=(EditText) view.findViewById(R.id.txt_HowManyPeopleLiveWithTheBeneficiary);
        txt_HowManyPeopleLiveWithTheBeneficiaryRelation=(EditText) view.findViewById(R.id.txt_HowManyPeopleLiveWithTheBeneficiaryRelation);
        txt_IfNoHowDoesHeSheCommunicate=(EditText) view.findViewById(R.id.txt_IfNoHowDoesHeSheCommunicate);
        layout_cannot_communicate = (LinearLayout)view.findViewById(R.id.layout_cannot_communicate);
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
        Intent intent = getActivity().getIntent();
        long case_id = intent.getLongExtra("case_id",0);
        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        //
        CaseReportNeedsAssesment caseReportNeedsAssesment = db.caseReportNeedsAssesmentDao().findByCaseId(case_id);


        return view;

    }
}