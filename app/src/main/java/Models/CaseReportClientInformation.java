package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class CaseReportClientInformation {

    public CaseReportClientInformation() {

    }

    public CaseReportClientInformation(long caseId) {
        CaseId = caseId;
    }

    @PrimaryKey(autoGenerate = true)
    public long Id;
    public long CaseId;
    public String NameOfClient;
    public Date Dob;
    public int Age;
    public String Sex;
    public String LevelOfEducation;
    public String ClientsAddress;
    public String PhoneNumberHome;
    public String Mobile;
    public String DescriptionOfDisability;
    public String GiveDetailsOfTheDisability;
}