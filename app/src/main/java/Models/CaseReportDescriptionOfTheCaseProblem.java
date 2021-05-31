package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CaseReportDescriptionOfTheCaseProblem {

    public CaseReportDescriptionOfTheCaseProblem(long caseId) {
        CaseId = caseId;
    }

    @PrimaryKey(autoGenerate = true)
    public long Id;
    public long CaseId;
    public String BeneficiaryStatus;
    public String BeneficiaryStatusOtherSpecify;
    public String RelationshipClientAndAccused;
    public String RelationshipClientAndWitness;
    public String RelationshipClientAndSurvivorVictimComplainant;
    public String NatureOfTheMatterCase;
    public String NatureOfTheMatterCaseOtherSpecify;
    public String DetailsOfCaseAndCharge;
}

