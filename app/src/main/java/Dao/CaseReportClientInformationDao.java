package Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import Models.CaseReportClientInformation;

@Dao
public interface CaseReportClientInformationDao {
    @Insert
    void insert(CaseReportClientInformation item);
    @Update
    void update(CaseReportClientInformation item);
    @Delete
    void delete(CaseReportClientInformation item);
    @Delete
    void delete(CaseReportClientInformation... items);
}
