package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.CaseReportCareGiver;
import Models.CaseReportNextOfKin;

@Dao
public interface CaseReportCareGiverDao {
    @Insert
    long insert(CaseReportCareGiver item);
    @Update
    void update(CaseReportCareGiver item);
    @Delete
    void delete(CaseReportCareGiver item);
    @Query("select * from casereportcaregiver where caseid = :case_id")
    CaseReportCareGiver findByCaseId(String case_id);
    @Query("select * from casereportcaregiver")
    List<CaseReportCareGiver> findAll();
}
