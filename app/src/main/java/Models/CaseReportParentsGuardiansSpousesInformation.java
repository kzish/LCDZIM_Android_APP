package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CaseReportParentsGuardiansSpousesInformation {

    public CaseReportParentsGuardiansSpousesInformation(){
        Id = UUID.randomUUID().toString();
    }
    public CaseReportParentsGuardiansSpousesInformation(String caseId) {
        CaseId = caseId;
        Id = UUID.randomUUID().toString();
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;
    public String Id;
    public String CaseId;
    public String Name;
    public int Age;
    public String Address;
    public String PhoneNumber;
    public String Email;
    public String Occupation;
    public String Employer;
    public String MaritalStatus;
    public String MaritalStatusOtherSpecify;
}
