package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CaseReportNextOfKin {

    public CaseReportNextOfKin(){}
    public CaseReportNextOfKin(long caseId) {
        CaseId = caseId;
    }
    @PrimaryKey(autoGenerate = true)
    public long Id;
    public long CaseId;
    public String Name;
    public int Age;
    public String Address;
    public String PhoneNumber;
    public String Email;
    public String Occupation;
    public String Employer;
}