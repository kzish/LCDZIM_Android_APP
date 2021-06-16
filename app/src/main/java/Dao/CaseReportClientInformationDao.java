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
    long insert(CaseReportClientInformation item);
    @Update
    void update(CaseReportClientInformation item);
    @Delete
    void delete(CaseReportClientInformation item);
    @Query("select * from casereportclientinformation where caseid = :case_id")
    CaseReportClientInformation findByCaseId(String case_id);
    @Query("delete from CaseReportClientInformation where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
