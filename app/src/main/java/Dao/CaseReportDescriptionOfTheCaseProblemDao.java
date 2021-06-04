package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Models.CaseReport;
import Models.CaseReportClientInformation;
import Models.CaseReportDescriptionOfTheCaseProblem;

@Dao
public interface CaseReportDescriptionOfTheCaseProblemDao {
    @Insert
    long insert(CaseReportDescriptionOfTheCaseProblem item);
    @Update
    void update(CaseReportDescriptionOfTheCaseProblem item);
    @Delete
    void delete(CaseReportDescriptionOfTheCaseProblem item);
    @Query("select * from casereportdescriptionofthecaseproblem where caseid = :case_id")
    CaseReportDescriptionOfTheCaseProblem findByCaseId(long case_id);
}
