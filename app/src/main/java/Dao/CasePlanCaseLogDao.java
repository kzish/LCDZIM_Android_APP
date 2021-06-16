package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.CasePlanCaseLog;
import Models.CasePlanCaseLog;
import Models.CaseReportCasePlanAndFollowUp;

@Dao
public interface CasePlanCaseLogDao {
    @Insert
    long insert(CasePlanCaseLog item);
    @Update
    void update(CasePlanCaseLog item);
    @Delete
    void delete(CasePlanCaseLog item);
    @Query("select * from CasePlanCaseLog where _id = :id")
    CasePlanCaseLog findById(long id);
    @Query("select * from CasePlanCaseLog where id = :id")
    CasePlanCaseLog findByKeyId(String id);
    @Query("select * from CasePlanCaseLog")
    List<CasePlanCaseLog> findAll();
    @Query("select * from CasePlanCaseLog where caseid=:case_id")
    List<CasePlanCaseLog> findAllByCaseId(String case_id);
    @Query("delete from CasePlanCaseLog where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
