package CaseReportFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lcdzim.R;

public class CaseWorkPlanListFragment extends Fragment {

    public CaseWorkPlanListFragment() {
        // Required empty public constructor
    }

    public static CaseWorkPlanListFragment newInstance(String param1, String param2) {
        CaseWorkPlanListFragment fragment = new CaseWorkPlanListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_case_work_plan_list, container, false);
        return view;
    }
}