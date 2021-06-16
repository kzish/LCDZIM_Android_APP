package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.CaseReportJustificationReportForAttendedCases;
import Models.CaseReportParentsGuardiansSpousesInformation;

@Dao
public interface CaseReportJustificationReportForAttendedCasesDao {
    @Insert
    long insert(CaseReportJustificationReportForAttendedCases item);
    @Update
    void update(CaseReportJustificationReportForAttendedCases item);
    @Delete
    void delete(CaseReportJustificationReportForAttendedCases item);
    @Query("select * from casereportjustificationreportforattendedcases where _id = :id")
    CaseReportJustificationReportForAttendedCases findById(long id);
    @Query("select * from casereportjustificationreportforattendedcases")
    List<CaseReportJustificationReportForAttendedCases> findAll();
    @Query("select * from casereportjustificationreportforattendedcases where caseid=:case_id")
    List<CaseReportJustificationReportForAttendedCases> findAllByCaseId(String case_id);
    @Query("delete from CaseReportJustificationReportForAttendedCases where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
