package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Models.CaseReportBeneficiaryInformation;

@Dao
public interface CaseReportBeneficiaryInformationDao {
    @Insert
    long insert(CaseReportBeneficiaryInformation item);
    @Update
    void update(CaseReportBeneficiaryInformation item);
    @Delete
    void delete(CaseReportBeneficiaryInformation item);
    @Query("select * from CaseReportBeneficiaryInformation where caseid = :case_id")
    CaseReportBeneficiaryInformation findByCaseId(String case_id);
    @Query("delete from CaseReportBeneficiaryInformation where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
