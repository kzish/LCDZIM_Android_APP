package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

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
    @Query("select * from CasePlanCaseLog")
    List<CasePlanCaseLog> findAll();
    @Query("select * from CasePlanCaseLog where caseid=:case_plan_id")
    List<CasePlanCaseLog> findAllByCasePlanId(String case_plan_id);
    @Query("delete from CasePlanCaseLog where caseid=:case_plan_id")
    void deleteAllByCasePlanId(String case_plan_id);
}
