package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import Models.CaseReport;

@Dao
public interface CaseReportDao {
    @Insert
    void insert(CaseReport item);
    @Update
    void update(CaseReport item);
    @Delete
    void delete(CaseReport item);
    @Delete
    void delete(CaseReport... items);
}
