package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CaseReportParentsGuardiansSpousesInformation {

    public CaseReportParentsGuardiansSpousesInformation(){}
    public CaseReportParentsGuardiansSpousesInformation(long caseId) {
        CaseId = caseId;
    }

    @PrimaryKey(autoGenerate = true)
    public long IdAtClient;
    public long CaseId;
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
