package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.CaseReport;

@Dao
public interface CaseReportDao {
    @Insert
    long insert(CaseReport item);
    @Update
    void update(CaseReport item);
    @Delete
    void delete(CaseReport item);
    @Query("select * from casereport where IdAtClient = :IdAtClient")
    CaseReport find(long IdAtClient);
    @Query("select * from casereport")
    List<CaseReport> findAll();
}
