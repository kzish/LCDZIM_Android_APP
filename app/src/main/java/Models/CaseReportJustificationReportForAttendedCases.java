package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CaseReportJustificationReportForAttendedCases {
    public CaseReportJustificationReportForAttendedCases() {
        Id = UUID.randomUUID().toString();
    }

    public CaseReportJustificationReportForAttendedCases(String caseId) {
        CaseId = caseId;
        Id = UUID.randomUUID().toString();
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;//db identity column
    public String Id;//shared guid id client and server
    public String CaseId;
    public boolean SavedAtLeastOnce;
    public String NameOfBeneficiary;
    public String PlaceOfOriginResidence;
    public String PlaceOfActivity;
    public String DateWhenTheBeneficiaryWasAssisted;
    public boolean TypeOfAssistanceLogisticalSupport;
    public boolean TypeOfAssistanceDisabilityExpert;
    public boolean TypeOfAssistanceHomeVisit;
    public String SummaryOfActivity;
    public String Outcome;
    public String DateOfCourtHearing;
    public String PreparedBy;
    public String PreparedByDate;
    public String ApprovedBy;
    public String ApprovedByDate;
}
