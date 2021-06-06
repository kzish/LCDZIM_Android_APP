package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.CaseReport;
import Models.CaseReportDescriptionOfTheCaseProblem;

@Dao
public interface CaseReportDao {
    @Insert
    long insert(CaseReport item);
    @Update
    void update(CaseReport item);
    @Delete
    void delete(CaseReport item);
    @Query("select * from casereport where Id = :Id")//this id is the case id
    CaseReport findByCaseId(String Id);
    @Query("select * from casereport where _Id = :Id")//this is the identity
    CaseReport findById(long Id);
    @Query("select * from casereport")
    List<CaseReport> findAll();
}
