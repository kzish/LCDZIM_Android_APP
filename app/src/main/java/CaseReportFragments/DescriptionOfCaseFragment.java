package CaseReportFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lcdzim.CreateEditRecordActivity;
import com.example.lcdzim.R;

import Database.AppDatabase;
import Models.CaseReportDescriptionOfTheCaseProblem;
import Models.CaseReportParentsGuardiansSpousesInformation;

public class DescriptionOfCaseFragment extends Fragment {

    public static boolean fragment_can_save; //is this fragment active yet?

    public DescriptionOfCaseFragment() {
    }

    public static DescriptionOfCaseFragment newInstance(String param1, String param2) {
        DescriptionOfCaseFragment fragment = new DescriptionOfCaseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    static EditText txt_BeneficiaryStatusOtherSpecify;
    static EditText txt_RelationshipClientAndAccused;
    static EditText txt_RelationshipClientAndWitness;
    static EditText txt_RelationshipClientAndSurvivorVictimComplainant;
    static EditText txt_NatureOfTheMatterCaseOtherSpecify;
    static EditText txt_DetailsOfCaseAndCharge;
    static Spinner txt_BeneficiaryStatus;
    static Spinner txt_NatureOfTheMatterCase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment_can_save = true;
        View view = inflater.inflate(R.layout.fragment_description_of_case, container, false);
        //
        txt_BeneficiaryStatusOtherSpecify = (EditText) view.findViewById(R.id.txt_BeneficiaryStatusOtherSpecify);
        txt_RelationshipClientAndAccused = (EditText) view.findViewById(R.id.txt_RelationshipClientAndAccused);
        txt_RelationshipClientAndWitness = (EditText) view.findViewById(R.id.txt_RelationshipClientAndWitness);
        txt_RelationshipClientAndSurvivorVictimComplainant = (EditText) view.findViewById(R.id.txt_RelationshipClientAndSurvivorVictimComplainant);
        txt_DetailsOfCaseAndCharge = (EditText) view.findViewById(R.id.txt_DetailsOfCaseAndCharge);
        txt_BeneficiaryStatus = (Spinner) view.findViewById(R.id.txt_BeneficiaryStatus);
        txt_NatureOfTheMatterCase = (Spinner) view.findViewById(R.id.txt_NatureOfTheMatterCase);
        txt_NatureOfTheMatterCaseOtherSpecify = (EditText) view.findViewById(R.id.txt_NatureOfTheMatterCaseOtherSpecify);
        //
        txt_NatureOfTheMatterCase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = txt_NatureOfTheMatterCase.getSelectedItem().toString();
                if (selected.equals("Other")) {
                    txt_NatureOfTheMatterCaseOtherSpecify.setVisibility(View.VISIBLE);
                } else {
                    txt_NatureOfTheMatterCaseOtherSpecify.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        txt_BeneficiaryStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = txt_BeneficiaryStatus.getSelectedItem().toString();
                if (selected.equals("Other")) {
                    txt_BeneficiaryStatusOtherSpecify.setVisibility(View.VISIBLE);
                } else {
                    txt_BeneficiaryStatusOtherSpecify.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        txt_BeneficiaryStatusOtherSpecify.setText(CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.BeneficiaryStatusOtherSpecify);
        txt_RelationshipClientAndAccused.setText(CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.RelationshipClientAndAccused);
        txt_RelationshipClientAndWitness.setText(CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.RelationshipClientAndWitness);
        txt_RelationshipClientAndSurvivorVictimComplainant.setText(CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.RelationshipClientAndSurvivorVictimComplainant);
        txt_DetailsOfCaseAndCharge.setText(CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.DetailsOfCaseAndCharge);
        txt_NatureOfTheMatterCaseOtherSpecify.setText(CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.NatureOfTheMatterCaseOtherSpecify);

        for (int i = 0; i < txt_BeneficiaryStatus.getAdapter().getCount(); i++) {
            String val = txt_BeneficiaryStatus.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.BeneficiaryStatus)) {
                txt_BeneficiaryStatus.setSelection(i);
            }
        }
        for (int i = 0; i < txt_NatureOfTheMatterCase.getAdapter().getCount(); i++) {
            String val = txt_NatureOfTheMatterCase.getAdapter().getItem(i).toString();
            if (val.equals(CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.NatureOfTheMatterCase)) {
                txt_NatureOfTheMatterCase.setSelection(i);
            }
        }

        return view;
    }

    public static void saveRecord() {

        if(!fragment_can_save)return;
        CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.BeneficiaryStatus = txt_BeneficiaryStatus.getSelectedItem().toString();
        CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.BeneficiaryStatusOtherSpecify = txt_BeneficiaryStatusOtherSpecify.getText().toString();
        CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.RelationshipClientAndAccused = txt_RelationshipClientAndAccused.getText().toString();
        CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.RelationshipClientAndWitness = txt_RelationshipClientAndWitness.getText().toString();
        CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.RelationshipClientAndSurvivorVictimComplainant = txt_RelationshipClientAndSurvivorVictimComplainant.getText().toString();
        CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.NatureOfTheMatterCase = txt_NatureOfTheMatterCase.getSelectedItem().toString();
        CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.NatureOfTheMatterCaseOtherSpecify = txt_NatureOfTheMatterCaseOtherSpecify.getText().toString();
        CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem.DetailsOfCaseAndCharge = txt_DetailsOfCaseAndCharge.getText().toString();

        ProgressDialog pd = new ProgressDialog(CreateEditRecordActivity.context);
        try {
            pd.setTitle("Saving...");
            pd.show();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditRecordActivity.context);
                    db.caseReportDescriptionOfTheCaseProblemDao().update(CreateEditRecordActivity.caseReportDescriptionOfTheCaseProblem);
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