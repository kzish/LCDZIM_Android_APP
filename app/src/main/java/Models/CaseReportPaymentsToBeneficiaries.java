package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CaseReportPaymentsToBeneficiaries {
    public CaseReportPaymentsToBeneficiaries() {
        Id = UUID.randomUUID().toString();
    }

    public CaseReportPaymentsToBeneficiaries(String caseId) {
        CaseId = caseId;
        Id = UUID.randomUUID().toString();
    }
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long _Id;//db identity column
    public boolean SavedAtLeastOnce;
    public String Id;//shared guid id client and server
    public String CaseId;
    public String Date;
    public String Purpose;
    public String Program;
    public String Name;
    public String IdNumber;
    public Double BusFare;
    public Double Breakfast;
    public Double Lunch;
    public Double Dinner;
    public Double Accomodation;
    public Double PerDiem;
    public Double Other;
    public String SignatureOfRecipient;
    public String ExpenseCode;
    public String PaidByName;
    public String PaidBySignature;
    public String PaidByDate;
    public String CheckedByName;
    public String CheckedBySignature;
    public String CheckedByDate;
    public String AuthorisedByName;
    public String AuthorisedBySignature;
    public String AuthorisedByDate;
}
