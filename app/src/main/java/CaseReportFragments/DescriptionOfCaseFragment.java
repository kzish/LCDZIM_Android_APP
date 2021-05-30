package CaseReportFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_description_of_case, container, false);
    }
}