package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Models.CaseReport;
import Models.CaseReportClientInformation;
import Models.CaseReportParentsGuardiansSpousesInformation;

@Dao
public interface CaseReportParentsGuardiansSpousesInformationDao {
    @Insert
    long insert(CaseReportParentsGuardiansSpousesInformation item);
    @Update
    void update(CaseReportParentsGuardiansSpousesInformation item);
    @Delete
    void delete(CaseReportParentsGuardiansSpousesInformation item);
    @Insert
    long[] insert(CaseReportParentsGuardiansSpousesInformation... items);
    @Query("select * from casereportparentsguardiansspousesinformation where caseid = :case_id")
    CaseReportParentsGuardiansSpousesInformation findByCaseId(long case_id);
}
