package CaseReportFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lcdzim.R;

public class PGSInformationFragment extends Fragment {

    public PGSInformationFragment() {
    }

    public static PGSInformationFragment newInstance(String param1, String param2) {
        PGSInformationFragment fragment = new PGSInformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pgs_information, container, false);
    }
}