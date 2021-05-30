package CaseReportFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lcdzim.R;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_needs_assesment, container, false);
    }
}