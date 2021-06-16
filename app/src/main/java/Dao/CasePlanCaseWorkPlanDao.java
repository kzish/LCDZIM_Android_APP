package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.CasePlanCaseLog;
import Models.CasePlanCaseWorkplan;

@Dao
public interface CasePlanCaseWorkPlanDao {
    @Insert
    long insert(CasePlanCaseWorkplan item);
    @Update
    void update(CasePlanCaseWorkplan item);
    @Delete
    void delete(CasePlanCaseWorkplan item);
    @Query("select * from CasePlanCaseWorkplan where _id = :id")
    CasePlanCaseWorkplan findById(long id);
    @Query("select * from CasePlanCaseWorkplan where id = :id")
    CasePlanCaseWorkplan findByKeyId(String id);
    @Query("select * from CasePlanCaseWorkplan")
    List<CasePlanCaseWorkplan> findAll();
    @Query("select * from CasePlanCaseWorkplan where caseid=:case_id")
    List<CasePlanCaseWorkplan> findAllByCaseId(String case_id);
    @Query("delete from CasePlanCaseWorkplan where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
