package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lcdzim.CreateEditCaseReportActivity;
import com.example.lcdzim.CreateEditJustificationReportActivity;
import com.example.lcdzim.CreateEditPaymentsToBeneficiaryActivity;
import com.example.lcdzim.R;

import java.util.List;

import Models.CaseReportJustificationReportForAttendedCases;
import Models.CaseReportPaymentsToBeneficiaries;

public class CaseReportPaymentsToBeneficiariesAdapter extends RecyclerView.Adapter<CaseReportPaymentsToBeneficiariesAdapter.ViewHolder> {

    public static List<CaseReportPaymentsToBeneficiaries> caseReportPaymentsToBeneficiariess;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt_Date;
        private final TextView txt_Name;
        private final TextView txt_IdNumber;
        private final TextView txt_Total;
        private final CardView card_view;


        public ViewHolder(View view) {
            super(view);
            txt_Date = (TextView) view.findViewById(R.id.txt_Date);
            txt_Name = (TextView) view.findViewById(R.id.txt_Name);
            txt_IdNumber = (TextView) view.findViewById(R.id.txt_IdNumber);
            txt_Total = (TextView) view.findViewById(R.id.txt_Total);
            card_view = (CardView) view.findViewById(R.id.card_view);
        }


    }

    public CaseReportPaymentsToBeneficiariesAdapter(List<CaseReportPaymentsToBeneficiaries> _caseReportPaymentsToBeneficiariess, Context _context) {
        caseReportPaymentsToBeneficiariess = _caseReportPaymentsToBeneficiariess;
        context = _context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.case_report_payments_to_beneficiaries_view_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        CaseReportPaymentsToBeneficiaries caseReportPaymentsToBeneficiaries = caseReportPaymentsToBeneficiariess.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        if (!caseReportPaymentsToBeneficiaries.Date.equals("")) {
            viewHolder.txt_Date.setText(caseReportPaymentsToBeneficiaries.Date);
        }

        if (!caseReportPaymentsToBeneficiaries.Name.equals("")) {
            viewHolder.txt_Name.setText(caseReportPaymentsToBeneficiaries.Name);
        }
        if (!caseReportPaymentsToBeneficiaries.IdNumber.equals("")) {
            viewHolder.txt_IdNumber.setText(caseReportPaymentsToBeneficiaries.IdNumber);
        }

        double total = 0;
        total += caseReportPaymentsToBeneficiaries.BusFare;
        total += caseReportPaymentsToBeneficiaries.Breakfast;
        total += caseReportPaymentsToBeneficiaries.Lunch;
        total += caseReportPaymentsToBeneficiaries.Dinner;
        total += caseReportPaymentsToBeneficiaries.Accomodation;
        total += caseReportPaymentsToBeneficiaries.PerDiem;
        total += caseReportPaymentsToBeneficiaries.Other;

        viewHolder.txt_Total.setText(String.format("%,.2f", total));

        viewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CreateEditPaymentsToBeneficiaryActivity.class);
                intent.putExtra("created_item_id", caseReportPaymentsToBeneficiaries._Id);
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return caseReportPaymentsToBeneficiariess.size();
    }
}