package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class CaseReportClientInformation {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    public int CaseId;
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