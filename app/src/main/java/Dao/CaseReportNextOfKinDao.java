package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Models.CaseReportNextOfKin;

@Dao
public interface CaseReportNextOfKinDao {
    @Insert
    long insert(CaseReportNextOfKin item);
    @Update
    void update(CaseReportNextOfKin item);
    @Delete
    void delete(CaseReportNextOfKin item);
    @Query("select * from casereportnextofkin where caseid = :case_id")
    CaseReportNextOfKin findByCaseId(String case_id);
    @Query("delete from CaseReportNextOfKin where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
