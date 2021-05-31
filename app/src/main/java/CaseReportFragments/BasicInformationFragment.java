package CaseReportFragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.lcdzim.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

    EditText txt_ReferredByNameAndInstitution;
    EditText txt_PoliceStation;
    EditText txt_CrRef;
    EditText txt_NameOfInvestigatingOfficer;
    EditText txt_MobileNumber;
    EditText txt_CourtHandlingTheCase;
    EditText txt_DateCaseWasReported;
    EditText txt_ForceNumber;
    EditText txt_CompiledBy;
    EditText txt_DateCompiled;

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
        //
        return view;
    }
}