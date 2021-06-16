package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.CreateEditCaseWorkPlanActivity;
import com.example.lcdzim.CreateEditJustificationReportActivity;
import com.example.lcdzim.R;

import java.util.List;

import CaseReportFragments.CaseWorkPlanListFragment;
import Models.CasePlanCaseWorkplan;
import Models.CaseReportJustificationReportForAttendedCases;

public class WorkPlanAdapter extends RecyclerView.Adapter<WorkPlanAdapter.ViewHolder> {

    public static List<CasePlanCaseWorkplan> casePlanCaseWorkplans;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt_Date;
        private final TextView txt_ActionToBeTaken;
        private final TextView txt_Responsibility;
        private final CheckBox chk_Done;
        private final CardView card_view;


        public ViewHolder(View view) {
            super(view);
            txt_Date = (TextView) view.findViewById(R.id.txt_Date);
            txt_ActionToBeTaken = (TextView) view.findViewById(R.id.txt_ActionToBeTaken);
            txt_Responsibility = (TextView) view.findViewById(R.id.txt_Responsibility);
            chk_Done = (CheckBox) view.findViewById(R.id.chk_Done);
            card_view = (CardView) view.findViewById(R.id.card_view);
        }


    }

    public WorkPlanAdapter(List<CasePlanCaseWorkplan> _casePlanCaseWorkplans, Context _context) {
        casePlanCaseWorkplans = _casePlanCaseWorkplans;
        context = _context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.work_plan_view_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        CasePlanCaseWorkplan casePlanCaseWorkplan = casePlanCaseWorkplans.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        if (casePlanCaseWorkplan.Date != null && !casePlanCaseWorkplan.Date.equals("")) {
            viewHolder.txt_Date.setText(casePlanCaseWorkplan.Date);
        }

        if (casePlanCaseWorkplan.ActionToBeTaken != null && !casePlanCaseWorkplan.ActionToBeTaken.equals("")) {
            viewHolder.txt_ActionToBeTaken.setText(casePlanCaseWorkplan.ActionToBeTaken);
        }

        if (casePlanCaseWorkplan.Responsibility != null && !casePlanCaseWorkplan.Responsibility.equals("")) {
            viewHolder.txt_Responsibility.setText(casePlanCaseWorkplan.Responsibility);
        }
        viewHolder.chk_Done.setChecked(casePlanCaseWorkplan.Done);

        viewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateEditCaseWorkPlanActivity.created_item_id = casePlanCaseWorkplan._Id;
                Intent intent = new Intent(context, CreateEditCaseWorkPlanActivity.class);
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return casePlanCaseWorkplans.size();
    }
}