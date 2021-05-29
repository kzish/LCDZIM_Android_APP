package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import Models.CaseReportParentsGuardiansSpousesInformation;

@Dao
public interface CaseReportParentsGuardiansSpousesInformationDao {
    @Insert
    void insert(CaseReportParentsGuardiansSpousesInformation item);
    @Update
    void update(CaseReportParentsGuardiansSpousesInformation item);
    @Delete
    void delete(CaseReportParentsGuardiansSpousesInformation item);
    @Delete
    void delete(CaseReportParentsGuardiansSpousesInformation... items);
}
