package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CaseReportCasePlanAndFollowUp {
    public CaseReportCasePlanAndFollowUp() {
        Id = UUID.randomUUID().toString();
    }

    public CaseReportCasePlanAndFollowUp(String caseId) {
        CaseId = caseId;
        Id = UUID.randomUUID().toString();
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;//db identity column
    public String Id;//shared guid id client and server
    public String CaseId;
    public String StageCaseRefered;
    public String ActionTakenByAnyoneDateThisFormWasCompleted;
    public String AnyOtherImportantNotes;
    public String StateOtherNeedsOfTheBeneficiaryForReferalToOtherServiceProviders;
    public String CompiledBy;
    public String ApprovedBy;
    public String ApprovedBySignature;
    public String Date;    
}
