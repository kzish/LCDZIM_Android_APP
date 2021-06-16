package CaseReportFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.CreateEditCaseWorkPlanActivity;
import com.example.lcdzim.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.CaseReportJustificationReportAdapter;
import Adapters.WorkPlanAdapter;
import Database.AppDatabase;
import Models.CasePlanCaseWorkplan;
import Models.CaseReport;
import Models.CaseReportJustificationReportForAttendedCases;

public class CaseWorkPlanListFragment extends Fragment {

    public static RecyclerView list_work_plans;
    public static List<CasePlanCaseWorkplan> casePlanCaseWorkplans;
    static ProgressDialog pd;
    public static WorkPlanAdapter adapter;
    static LinearLayout ll_empty;
    ImageView img_add_work_plan;

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
        pd = new ProgressDialog(getContext());
        list_work_plans = (RecyclerView) view.findViewById(R.id.list_work_plans);
        ll_empty = (LinearLayout) view.findViewById(R.id.ll_empty);
        img_add_work_plan = (ImageView) view.findViewById(R.id.img_add_work_plan);

        img_add_work_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_create_edit_work_plan = new Intent(getContext(), CreateEditCaseWorkPlanActivity.class);
                CasePlanCaseWorkplan casePlanCaseWorkplan = new CasePlanCaseWorkplan(CreateEditCaseReportActivity.case_id);
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getAppDatabase(getContext());
                            long created_item_id = db.casePlanCaseWorkPlanDao().insert(casePlanCaseWorkplan);
                            CreateEditCaseWorkPlanActivity.created_item_id = created_item_id;
                            db = null;
                        }
                    });
                    t.start();
                    t.join();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getMessage());
                }
                getContext().startActivity(activity_create_edit_work_plan);
            }
        });

        return view;
    }

    public static void loadWorkPlans() {

        casePlanCaseWorkplans = new ArrayList<>();
        pd.setTitle("Loading...");
        pd.show();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseReportActivity.context);
                    List<CasePlanCaseWorkplan> _casePlanCaseWorkplans = db.casePlanCaseWorkPlanDao().findAllByCaseId(CreateEditCaseReportActivity.case_id);
                    for (CasePlanCaseWorkplan cwp : _casePlanCaseWorkplans
                    ) {
                        if (cwp.SavedAtLeastOnce) {
                            casePlanCaseWorkplans.add(cwp);
                        } else {
                            db.casePlanCaseWorkPlanDao().delete(cwp);
                        }
                    }
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.e("kzzex", ex.getMessage());
        }
        pd.hide();

        if (casePlanCaseWorkplans.size() == 0) {
            ll_empty.setVisibility(View.VISIBLE);
        } else {
            ll_empty.setVisibility(View.GONE);
        }

        adapter = new WorkPlanAdapter(casePlanCaseWorkplans, CreateEditCaseReportActivity.context);
        list_work_plans.addItemDecoration(new DividerItemDecoration(CreateEditCaseReportActivity.context, DividerItemDecoration.VERTICAL));
        list_work_plans.setHasFixedSize(true);
        list_work_plans.setLayoutManager(new LinearLayoutManager(CreateEditCaseReportActivity.context));
        list_work_plans.setAdapter(adapter);
    }

}