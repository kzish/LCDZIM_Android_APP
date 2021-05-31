package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CaseReportNeedsAssesment {

    public CaseReportNeedsAssesment(long caseId) {
        CaseId = caseId;
    }

    @PrimaryKey(autoGenerate = true)
    public long Id;
    public long CaseId;
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
