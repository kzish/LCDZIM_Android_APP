package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity
public class CaseReportBeneficiaryInformation {

    public CaseReportBeneficiaryInformation() {
        Id = UUID.randomUUID().toString();
    }

    public CaseReportBeneficiaryInformation(String caseId) {
        CaseId = caseId;
        Id = UUID.randomUUID().toString();
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;
    public String Id;
    public String CaseId;
    public String NameOfClient;
    public String Dob;
    public int Age;
    public String Sex;
    public String LevelOfEducation;
    public String ClientsAddress;
    public String PhoneNumberHome;
    public String Mobile;
    public String DescriptionOfDisability;
    public String GiveDetailsOfTheDisability;
}