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

import com.example.lcdzim.CreateEditCaseLogActivity;
import com.example.lcdzim.CreateEditCaseWorkPlanActivity;
import com.example.lcdzim.R;

import java.util.List;

import Models.CasePlanCaseLog;
import Models.CasePlanCaseWorkplan;

public class CaseLogAdapter extends RecyclerView.Adapter<CaseLogAdapter.ViewHolder> {

    public static List<CasePlanCaseLog> casePlanCaseLogs;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt_Date;
        private final TextView txt_ActionTakenActivity;
        private final TextView txt_Outcome;
        private final TextView txt_AttendingPerson;
        private final CardView card_view;


        public ViewHolder(View view) {
            super(view);
            txt_Date = (TextView) view.findViewById(R.id.txt_Date);
            txt_ActionTakenActivity = (TextView) view.findViewById(R.id.txt_ActionToBeTaken);
            txt_Outcome = (TextView) view.findViewById(R.id.txt_Outcome);
            txt_AttendingPerson = (TextView) view.findViewById(R.id.txt_AttendingPerson);
            card_view = (CardView) view.findViewById(R.id.card_view);
        }


    }

    public CaseLogAdapter(List<CasePlanCaseLog> _casePlanCaseLogs, Context _context) {
        casePlanCaseLogs = _casePlanCaseLogs;
        context = _context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.case_log_view_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        CasePlanCaseLog casePlanCaseLog = casePlanCaseLogs.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        if (casePlanCaseLog.Date != null && !casePlanCaseLog.Date.equals("")) {
            viewHolder.txt_Date.setText(casePlanCaseLog.Date);
        }

        if (casePlanCaseLog.ActionTakenActivity != null && !casePlanCaseLog.ActionTakenActivity.equals("")) {
            viewHolder.txt_ActionTakenActivity.setText(casePlanCaseLog.ActionTakenActivity);
        }

        if (casePlanCaseLog.AttendingPerson != null && !casePlanCaseLog.AttendingPerson.equals("")) {
            viewHolder.txt_AttendingPerson.setText(casePlanCaseLog.AttendingPerson);
        }

        if (casePlanCaseLog.Outcome != null && !casePlanCaseLog.Outcome.equals("")) {
            viewHolder.txt_Outcome.setText(casePlanCaseLog.Outcome);
        }

        viewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateEditCaseLogActivity.created_item_id = casePlanCaseLog._Id;
                Intent intent = new Intent(context, CreateEditCaseLogActivity.class);
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return casePlanCaseLogs.size();
    }
}