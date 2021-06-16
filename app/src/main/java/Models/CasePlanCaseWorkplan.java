package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CasePlanCaseWorkplan {
    public CasePlanCaseWorkplan() {
        Id = UUID.randomUUID().toString();
    }

    public CasePlanCaseWorkplan(String caseId) {
        CaseId = caseId;
        Id = UUID.randomUUID().toString();
        ActionToBeTaken = "";
        Date = "";
        Responsibility = "";
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;//db identity column
    public String Id;//shared guid id client and server
    public String CaseId;
    public String ActionToBeTaken;
    public String Date;
    public String Responsibility;
    public boolean Done;
    public boolean SavedAtLeastOnce;
}
