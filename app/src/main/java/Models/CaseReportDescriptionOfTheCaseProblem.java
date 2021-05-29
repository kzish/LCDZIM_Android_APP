package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CaseReportDescriptionOfTheCaseProblem {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    public int CaseId;
    public String BeneficiaryStatus;
    public String BeneficiaryStatusOtherSpecify;
    public String RelationshipClientAndAccused;
    public String RelationshipClientAndWitness;
    public String RelationshipClientAndSurvivorVictimComplainant;
    public String NatureOfTheMatterCase;
    public String NatureOfTheMatterCaseOtherSpecify;
    public String DetailsOfCaseAndCharge;
}

