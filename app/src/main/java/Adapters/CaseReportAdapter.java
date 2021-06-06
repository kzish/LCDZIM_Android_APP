package Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lcdzim.AddRecordActivity;
import com.example.lcdzim.MainActivity;
import com.example.lcdzim.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

import Models.CaseReport;

public class CaseReportAdapter extends RecyclerView.Adapter<CaseReportAdapter.ViewHolder> {

    public static List<CaseReport> caseReports;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt_Date;
        private final TextView txt_CrRef;
        private final TextView txt_PoliceStation;
        private final TextView txt_ReferredByNameAndInstitution;
        private final CardView card_view;
        private final ImageView img_uploaded;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            txt_Date = (TextView) view.findViewById(R.id.txt_Date);
            txt_CrRef = (TextView) view.findViewById(R.id.txt_CrRef);
            txt_PoliceStation = (TextView) view.findViewById(R.id.txt_PoliceStation);
            txt_ReferredByNameAndInstitution = (TextView) view.findViewById(R.id.txt_ReferredByNameAndInstitution);
            card_view = (CardView) view.findViewById(R.id.card_view);
            img_uploaded = (ImageView) view.findViewById(R.id.img_uploaded);
        }


    }

    public CaseReportAdapter(List<CaseReport> _caseReports, Context _context) {
        caseReports = _caseReports;
        context = _context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.case_report, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        CaseReport caseReport = caseReports.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.txt_CrRef.setText(caseReport.CrRef);
        if (caseReport.DateCompiled != null) {
            viewHolder.txt_Date.setText(caseReport.DateCompiled);
        }else{
            viewHolder.txt_Date.setText("...");
        }
        viewHolder.txt_PoliceStation.setText(caseReport.PoliceStation);
        viewHolder.txt_ReferredByNameAndInstitution.setText(caseReport.ReferredByNameAndInstitution);
        if(caseReport.Uploaded) {
            viewHolder.img_uploaded.setVisibility(View.VISIBLE);
        }else {
            viewHolder.img_uploaded.setVisibility(View.INVISIBLE);
        }

        if (position % 2 == 0) {
            viewHolder.card_view.setCardBackgroundColor(0xFFFEFEFE);
        } else {
            viewHolder.card_view.setCardBackgroundColor(0xFFFFFFFF);
        }

        viewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRecordActivity.case_id = caseReport.Id;
                Intent intent = new Intent(context, AddRecordActivity.class);
                intent.putExtra("case_id", caseReport.Id);
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return caseReports.size();
    }
}