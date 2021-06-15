package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.CaseReportCareGiver;
import Models.CaseReportCasePlanAndFollowUp;
import Models.CaseReportJustificationReportForAttendedCases;

@Dao
public interface CaseReportCasePlanAndFollowUpDao {
    @Insert
    long insert(CaseReportCasePlanAndFollowUp item);
    @Update
    void update(CaseReportCasePlanAndFollowUp item);
    @Delete
    void delete(CaseReportCasePlanAndFollowUp item);
    @Query("select * from CaseReportCasePlanAndFollowUp where _id = :id")
    CaseReportCasePlanAndFollowUp findById(long id);
    @Query("select * from CaseReportCasePlanAndFollowUp")
    List<CaseReportCasePlanAndFollowUp> findAll();
    @Query("select * from CaseReportCasePlanAndFollowUp where caseid=:case_id")
    CaseReportCasePlanAndFollowUp findByCaseId(String case_id);
}
