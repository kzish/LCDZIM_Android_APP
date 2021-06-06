package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CaseReportNeedsAssesment {

    public CaseReportNeedsAssesment() {
        Id = UUID.randomUUID().toString();
    }

    public CaseReportNeedsAssesment(String caseId) {
        CaseId = caseId;
        Id = UUID.randomUUID().toString();
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;
    public String Id;
    public String CaseId;
    public String WhereDoesTheBeneficiaryLive;
    public String WhoIsTakingCareOfTheBeneficiary;
    public String WhoIsTakingCareOfTheBeneficiaryOtherSpecify;
    public int HowManyPeopleLiveWithTheBeneficiary;
    public String HowManyPeopleLiveWithTheBeneficiaryRelation;
    public boolean IsTheBeneficiaryAbleToVerballyCommunicate;
    public String IfNoHowDoesHeSheCommunicate;
    public boolean IsTheBeneficiaryAbleToMoveIndependently;
    public String IfNotWhatAssistanceIsNeededMovement;
    public String BeneficiaryWellBeingAtThePointOfIntake;
    public String BeneficiaryWellBeingAtThePointOfIntakeOtherSpecify;
    public String BeneficiaryImmediateConcerns;
    public String OtherRelevantIssues;
}
