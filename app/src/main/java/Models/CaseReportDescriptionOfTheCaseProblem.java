package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CaseReportDescriptionOfTheCaseProblem {

    public CaseReportDescriptionOfTheCaseProblem() {
        Id = UUID.randomUUID().toString();
    }

    public CaseReportDescriptionOfTheCaseProblem(String caseId) {
        CaseId = caseId;
        Id = UUID.randomUUID().toString();
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;
    public String Id;
    public String CaseId;
    public String BeneficiaryStatus;
    public String BeneficiaryStatusOtherSpecify;
    public String RelationshipClientAndAccused;
    public String RelationshipClientAndWitness;
    public String RelationshipClientAndSurvivorVictimComplainant;
    public String NatureOfTheMatterCase;
    public String NatureOfTheMatterCaseOtherSpecify;
    public String DetailsOfCaseAndCharge;
}

