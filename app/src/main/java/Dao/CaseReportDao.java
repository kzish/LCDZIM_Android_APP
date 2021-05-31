package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Models.CaseReport;

@Dao
public interface CaseReportDao {
    @Insert
    long insert(CaseReport item);
    @Update
    void update(CaseReport item);
    @Delete
    void delete(CaseReport item);
    @Delete
    long[] delete(CaseReport... items);
    @Query("select * from casereport where id = :id")
    CaseReport find(long id);
}
