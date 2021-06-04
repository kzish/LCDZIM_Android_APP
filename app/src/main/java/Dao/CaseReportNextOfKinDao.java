package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Models.CaseReport;
import Models.CaseReportClientInformation;
import Models.CaseReportNextOfKin;

@Dao
public interface CaseReportNextOfKinDao {
    @Insert
    long insert(CaseReportNextOfKin item);
    @Update
    void update(CaseReportNextOfKin item);
    @Delete
    void delete(CaseReportNextOfKin item);
    @Query("select * from casereportnextofkin where caseid = :case_id")
    CaseReportNextOfKin findByCaseId(long case_id);
}
