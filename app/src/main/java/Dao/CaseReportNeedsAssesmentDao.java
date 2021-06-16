package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Models.CaseReportClientInformation;
import Models.CaseReportNeedsAssesment;

@Dao
public interface CaseReportNeedsAssesmentDao {
    @Insert
    long insert(CaseReportNeedsAssesment item);
    @Update
    void update(CaseReportNeedsAssesment item);
    @Delete
    void delete(CaseReportNeedsAssesment item);
    @Query("select * from casereportneedsassesment where caseid = :case_id")
    CaseReportNeedsAssesment findByCaseId(String case_id);
    @Query("delete from CaseReportNeedsAssesment where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
