package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CasePlanCaseLog {
    public CasePlanCaseLog() {
        Id = UUID.randomUUID().toString();
    }

    public CasePlanCaseLog(String casePlanId) {
        CasePlanId = casePlanId;
        Id = UUID.randomUUID().toString();
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;//db identity column
    public String Id;//shared guid id client and server
    public String CasePlanId;
    public String Date;
    public String ActionTakenActivity;
    public String Outcome;
    public String AttendingPerson;
}
