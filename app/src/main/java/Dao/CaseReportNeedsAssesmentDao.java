package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import Models.CaseReportNeedsAssesment;

@Dao
public interface CaseReportNeedsAssesmentDao {
    @Insert
    void insert(CaseReportNeedsAssesment item);
    @Update
    void update(CaseReportNeedsAssesment item);
    @Delete
    void delete(CaseReportNeedsAssesment item);
    @Delete
    void delete(CaseReportNeedsAssesment... items);
}
