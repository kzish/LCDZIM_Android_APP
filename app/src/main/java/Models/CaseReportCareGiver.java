package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CaseReportCareGiver {
    public CaseReportCareGiver() {
        Id = UUID.randomUUID().toString();
    }

    public CaseReportCareGiver(String caseId) {
        CaseId = caseId;
        Id = UUID.randomUUID().toString();
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;//db identity column
    public String Id;//shared guid id client and server
    public String CaseId;
    public String CareGiverName;
    public String CareGiverDob;
    public String CareGiverSex;
    public String CareGiverPhoneNumber;
    public String CareGiverAddress;
}
