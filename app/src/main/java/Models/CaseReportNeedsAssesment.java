package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CaseReportNeedsAssesment {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    public int CaseId;
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
