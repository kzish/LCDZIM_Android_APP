package CaseReportFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lcdzim.R;

public class CaseLogListFragment extends Fragment {


    public CaseLogListFragment() {
        // Required empty public constructor
    }

    public static CaseLogListFragment newInstance(String param1, String param2) {
        CaseLogListFragment fragment = new CaseLogListFragment();
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
        View view = inflater.inflate(R.layout.fragment_case_log_list, container, false);
        return view;
    }
}