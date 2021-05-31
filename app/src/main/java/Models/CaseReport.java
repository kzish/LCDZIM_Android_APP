package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class CaseReport {
    @PrimaryKey(autoGenerate = true)
    public long Id;
    public Date date;
    public String ReferredByNameAndInstitution;
    public String PoliceStation;
    public String CrRef;
    public String NameOfInvestigatingOfficer;
    public String MobileNumber;
    public String CourtHandlingTheCase;
    public Date DateCaseWasReported;
    public String ForceNumber;
    public String CompiledBy;
    public Date DateCompiled;
    public boolean SavedAtLeastOnce;//saved at least once then wont be deleted
}
