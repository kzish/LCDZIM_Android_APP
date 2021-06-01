package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Models.CaseReport;
import Models.CaseReportClientInformation;

@Dao
public interface CaseReportClientInformationDao {
    @Insert
    void insert(CaseReportClientInformation item);
    @Update
    void update(CaseReportClientInformation item);
    @Delete
    void delete(CaseReportClientInformation item);
    @Insert
    void delete(CaseReportClientInformation... items);
    @Query("select * from casereportclientinformation where caseid = :case_id")
    CaseReportClientInformation findByCaseId(long case_id);
}
