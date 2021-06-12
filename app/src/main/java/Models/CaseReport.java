package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity
public class CaseReport {
    public CaseReport() {
        Id = UUID.randomUUID().toString();
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;//db identity column
    public String Id;//shared guid id client and server
    public String CaseNumber;
    public String date;
    public String ReferredByNameAndInstitution;
    public String PoliceStation;
    public String CrRef;
    public String NameOfInvestigatingOfficer;
    public String MobileNumber;
    public String CourtHandlingTheCase;
    public String DateCaseWasReported;
    public String ForceNumber;
    public String CompiledBy;
    public String DateCompiled;
    public boolean SavedAtLeastOnce;//saved at least once then wont be deleted
    public boolean Uploaded;//is this case uploaded or not
}
