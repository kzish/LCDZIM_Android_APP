package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import Models.CaseReportNextOfKin;

@Dao
public interface CaseReportNextOfKinDao {
    @Insert
    void insert(CaseReportNextOfKin item);
    @Update
    void update(CaseReportNextOfKin item);
    @Delete
    void delete(CaseReportNextOfKin item);
    @Delete
    void delete(CaseReportNextOfKin... items);
}
