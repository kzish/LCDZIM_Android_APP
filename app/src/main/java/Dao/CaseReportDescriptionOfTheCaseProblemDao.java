package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import Models.CaseReportDescriptionOfTheCaseProblem;

@Dao
public interface CaseReportDescriptionOfTheCaseProblemDao {
    @Insert
    void insert(CaseReportDescriptionOfTheCaseProblem item);
    @Update
    void update(CaseReportDescriptionOfTheCaseProblem item);
    @Delete
    void delete(CaseReportDescriptionOfTheCaseProblem item);
    @Delete
    void delete(CaseReportDescriptionOfTheCaseProblem... items);
}
