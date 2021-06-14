package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lcdzim.CreateEditJustificationReportActivity;
import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.R;

import java.util.List;

import Models.CaseReportJustificationReportForAttendedCases;

public class CaseReportJustificationReportAdapter extends RecyclerView.Adapter<CaseReportJustificationReportAdapter.ViewHolder> {

    public static List<CaseReportJustificationReportForAttendedCases> caseReportJustificationReportForAttendedCasess;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt_NameOfBeneficiary;
        private final TextView txt_PlaceOfOriginResidence;
        private final TextView txt_PlaceOfActivity;
        private final TextView txt_DateWhenTheBeneficiaryWasAssisted;
        private final CardView card_view;


        public ViewHolder(View view) {
            super(view);
            txt_NameOfBeneficiary = (TextView) view.findViewById(R.id.txt_NameOfBeneficiary);
            txt_PlaceOfOriginResidence = (TextView) view.findViewById(R.id.txt_PlaceOfOriginResidence);
            txt_PlaceOfActivity = (TextView) view.findViewById(R.id.txt_PlaceOfActivity);
            txt_DateWhenTheBeneficiaryWasAssisted = (TextView) view.findViewById(R.id.txt_DateWhenTheBeneficiaryWasAssisted);
            card_view = (CardView) view.findViewById(R.id.card_view);
        }


    }

    public CaseReportJustificationReportAdapter(List<CaseReportJustificationReportForAttendedCases> _caseReportJustificationReportForAttendedCases, Context _context) {
        caseReportJustificationReportForAttendedCasess = _caseReportJustificationReportForAttendedCases;
        context = _context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.case_report_justification_report_view_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        CaseReportJustificationReportForAttendedCases caseReportJustificationReportForAttendedCases = caseReportJustificationReportForAttendedCasess.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        if (caseReportJustificationReportForAttendedCases.NameOfBeneficiary.equals("")) {
            viewHolder.txt_NameOfBeneficiary.setText("Name of Beneficiary...");
        } else {
            viewHolder.txt_NameOfBeneficiary.setText(caseReportJustificationReportForAttendedCases.NameOfBeneficiary);
        }

        if (caseReportJustificationReportForAttendedCases.PlaceOfActivity.equals("")) {
            viewHolder.txt_PlaceOfActivity.setText("Empty...");
        } else {
            viewHolder.txt_PlaceOfActivity.setText(caseReportJustificationReportForAttendedCases.PlaceOfActivity);
        }
        if (caseReportJustificationReportForAttendedCases.PlaceOfOriginResidence.equals("")) {
            viewHolder.txt_PlaceOfOriginResidence.setText("Empty...");
        } else {
            viewHolder.txt_PlaceOfOriginResidence.setText(caseReportJustificationReportForAttendedCases.PlaceOfOriginResidence);
        }
        if (caseReportJustificationReportForAttendedCases.DateWhenTheBeneficiaryWasAssisted.equals("")) {
            viewHolder.txt_DateWhenTheBeneficiaryWasAssisted.setText("Empty...");
        } else {
            viewHolder.txt_DateWhenTheBeneficiaryWasAssisted.setText(caseReportJustificationReportForAttendedCases.DateWhenTheBeneficiaryWasAssisted);
        }

        viewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateEditCaseReportActivity.case_id = caseReportJustificationReportForAttendedCases.CaseId;
                Intent intent = new Intent(context, CreateEditJustificationReportActivity.class);
                intent.putExtra("created_item_id",caseReportJustificationReportForAttendedCases._Id);
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return caseReportJustificationReportForAttendedCasess.size();
    }
}