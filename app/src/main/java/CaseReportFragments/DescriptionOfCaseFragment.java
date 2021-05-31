package CaseReportFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lcdzim.R;

public class DescriptionOfCaseFragment extends Fragment {

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

    EditText txt_BeneficiaryStatusOtherSpecify;
    EditText txt_RelationshipClientAndAccused;
    EditText txt_RelationshipClientAndWitness;
    EditText txt_RelationshipClientAndSurvivorVictimComplainant;
    EditText txt_NatureOfTheMatterCaseOtherSpecify;
    EditText txt_DetailsOfCaseAndCharge;
    Spinner txt_BeneficiaryStatus;
    Spinner txt_NatureOfTheMatterCase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description_of_case, container, false);
        //
        txt_BeneficiaryStatusOtherSpecify = (EditText) view.findViewById(R.id.txt_BeneficiaryStatusOtherSpecify);
        txt_RelationshipClientAndAccused = (EditText) view.findViewById(R.id.txt_RelationshipClientAndAccused);
        txt_RelationshipClientAndWitness = (EditText) view.findViewById(R.id.txt_RelationshipClientAndWitness);
        txt_RelationshipClientAndSurvivorVictimComplainant = (EditText) view.findViewById(R.id.txt_RelationshipClientAndSurvivorVictimComplainant);
        txt_DetailsOfCaseAndCharge = (EditText) view.findViewById(R.id.txt_DetailsOfCaseAndCharge);
        txt_BeneficiaryStatus = (Spinner) view.findViewById(R.id.txt_BeneficiaryStatus);
        txt_NatureOfTheMatterCase = (Spinner) view.findViewById(R.id.txt_NatureOfTheMatterCase);
        txt_NatureOfTheMatterCaseOtherSpecify =(EditText)view.findViewById(R.id.txt_NatureOfTheMatterCaseOtherSpecify);
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

        return view;
    }
}