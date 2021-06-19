package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Models.CaseReportParentsGuardiansSpousesInformation;

@Dao
public interface CaseReportParentsGuardiansSpousesInformationDao {
    @Insert
    long insert(CaseReportParentsGuardiansSpousesInformation item);
    @Update
    void update(CaseReportParentsGuardiansSpousesInformation item);
    @Delete
    void delete(CaseReportParentsGuardiansSpousesInformation item);
    @Query("select * from casereportparentsguardiansspousesinformation where caseid = :case_id")
    CaseReportParentsGuardiansSpousesInformation findByCaseId(String case_id);
    @Query("delete from CaseReportParentsGuardiansSpousesInformation where caseid=:case_id")
    void deleteAllByCaseId(String case_id);
}
