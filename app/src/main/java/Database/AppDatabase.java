package Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;
import androidx.room.TypeConverters;

import Converters.Converters;
import Models.CaseReport;
import Models.CaseReportClientInformation;
import Models.CaseReportDescriptionOfTheCaseProblem;
import Models.CaseReportNeedsAssesment;
import Models.CaseReportNextOfKin;
import Models.CaseReportParentsGuardiansSpousesInformation;

import Dao.CaseReportClientInformationDao;
import Dao.CaseReportDao;
import Dao.CaseReportDescriptionOfTheCaseProblemDao;
import Dao.CaseReportNeedsAssesmentDao;
import Dao.CaseReportNextOfKinDao;
import Dao.CaseReportParentsGuardiansSpousesInformationDao;

@Database(entities = {
        CaseReport.class,
        CaseReportClientInformation.class,
        CaseReportDescriptionOfTheCaseProblem.class,
        CaseReportNeedsAssesment.class,
        CaseReportNextOfKin.class,
        CaseReportParentsGuardiansSpousesInformation.class,
}, version = 4)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CaseReportDao caseReportDao();

    public abstract CaseReportClientInformationDao caseReportClientInformationDao();

    public abstract CaseReportDescriptionOfTheCaseProblemDao caseReportDescriptionOfTheCaseProblemDao();

    public abstract CaseReportNeedsAssesmentDao caseReportNeedsAssesmentDao();

    public abstract CaseReportNextOfKinDao caseReportNextOfKinDao();

    public abstract CaseReportParentsGuardiansSpousesInformationDao caseReportParentsGuardiansSpousesInformationDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "LCDZIM")
                    //.allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
