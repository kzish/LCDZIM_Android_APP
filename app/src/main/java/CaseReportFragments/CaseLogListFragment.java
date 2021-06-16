package CaseReportFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lcdzim.CreateEditCaseLogActivity;
import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.CreateEditCaseWorkPlanActivity;
import com.example.lcdzim.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.CaseLogAdapter;
import Adapters.WorkPlanAdapter;
import Database.AppDatabase;
import Models.CasePlanCaseLog;
import Models.CasePlanCaseWorkplan;

public class CaseLogListFragment extends Fragment {

    public static RecyclerView list_work_plans;
    public static List<CasePlanCaseLog> casePlanCaseLogs;
    static ProgressDialog pd;
    public static CaseLogAdapter adapter;
    static LinearLayout ll_empty;
    ImageView img_add_case_log;


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
        pd = new ProgressDialog(getContext());
        list_work_plans = (RecyclerView) view.findViewById(R.id.list_work_plans);
        ll_empty = (LinearLayout) view.findViewById(R.id.ll_empty);
        img_add_case_log = (ImageView) view.findViewById(R.id.img_add_case_log);

        img_add_case_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_create_edit_case_log = new Intent(getContext(), CreateEditCaseLogActivity.class);
                CasePlanCaseLog casePlanCaseLog = new CasePlanCaseLog(CreateEditCaseReportActivity.case_id);
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getAppDatabase(getContext());
                            long created_item_id = db.casePlanCaseLogDao().insert(casePlanCaseLog);
                            CreateEditCaseLogActivity.created_item_id = created_item_id;
                            db = null;
                        }
                    });
                    t.start();
                    t.join();
                } catch (Exception ex) {
                    Log.e("kzzex", ex.getMessage());
                }
                getContext().startActivity(activity_create_edit_case_log);
            }
        });

        loadCaseLogs();
        return view;
    }

    public static void loadCaseLogs() {

        casePlanCaseLogs = new ArrayList<>();
        pd.setTitle("Loading...");
        pd.show();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase db = AppDatabase.getAppDatabase(CreateEditCaseReportActivity.context);
                    List<CasePlanCaseLog> _casePlanCaseLogs = db.casePlanCaseLogDao().findAllByCaseId(CreateEditCaseReportActivity.case_id);
                    for (CasePlanCaseLog cpl : _casePlanCaseLogs
                    ) {
                        if (cpl.SavedAtLeastOnce) {
                            casePlanCaseLogs.add(cpl);
                        } else {
                            db.casePlanCaseLogDao().delete(cpl);
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

        if (casePlanCaseLogs.size() == 0) {
            ll_empty.setVisibility(View.VISIBLE);
        } else {
            ll_empty.setVisibility(View.GONE);
        }

        adapter = new CaseLogAdapter(casePlanCaseLogs, CreateEditCaseReportActivity.context);
        list_work_plans.addItemDecoration(new DividerItemDecoration(CreateEditCaseReportActivity.context, DividerItemDecoration.VERTICAL));
        list_work_plans.setHasFixedSize(true);
        list_work_plans.setLayoutManager(new LinearLayoutManager(CreateEditCaseReportActivity.context));
        list_work_plans.setAdapter(adapter);
    }
}