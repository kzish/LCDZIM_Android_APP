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
    CaseReportDescriptionOfTheCaseProblem findByCaseId(String case_id);
    @Query("delete from CaseReportDescriptionOfTheCaseProblem where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
