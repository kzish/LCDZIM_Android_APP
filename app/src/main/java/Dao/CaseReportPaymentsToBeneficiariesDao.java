package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.CaseReportJustificationReportForAttendedCases;
import Models.CaseReportNextOfKin;
import Models.CaseReportPaymentsToBeneficiaries;

@Dao
public interface CaseReportPaymentsToBeneficiariesDao {
    @Insert
    long insert(CaseReportPaymentsToBeneficiaries item);
    @Update
    void update(CaseReportPaymentsToBeneficiaries item);
    @Delete
    void delete(CaseReportPaymentsToBeneficiaries item);
    @Query("select * from CaseReportPaymentsToBeneficiaries where _id = :id")
    CaseReportPaymentsToBeneficiaries findById(long id);
    @Query("select * from CaseReportPaymentsToBeneficiaries")
    List<CaseReportPaymentsToBeneficiaries> findAll();
    @Query("select * from CaseReportPaymentsToBeneficiaries where caseid=:case_id")
    List<CaseReportPaymentsToBeneficiaries> findAllByCaseId(String case_id);
    @Query("delete from CaseReportPaymentsToBeneficiaries where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
