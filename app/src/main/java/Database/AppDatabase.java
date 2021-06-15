package Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;
import androidx.room.TypeConverters;

import Converters.Converters;
import Dao.CasePlanCaseLogDao;
import Dao.CasePlanCaseWorkPlanDao;
import Dao.CaseReportCareGiverDao;
import Dao.CaseReportCasePlanAndFollowUpDao;
import Dao.CaseReportJustificationReportForAttendedCasesDao;
import Dao.CaseReportPaymentsToBeneficiariesDao;
import Models.CasePlanCaseLog;
import Models.CasePlanCaseWorkplan;
import Models.CaseReport;
import Models.CaseReportCareGiver;
import Models.CaseReportCasePlanAndFollowUp;
import Models.CaseReportClientInformation;
import Models.CaseReportDescriptionOfTheCaseProblem;
import Models.CaseReportJustificationReportForAttendedCases;
import Models.CaseReportNeedsAssesment;
import Models.CaseReportNextOfKin;
import Models.CaseReportParentsGuardiansSpousesInformation;

import Dao.CaseReportClientInformationDao;
import Dao.CaseReportDao;
import Dao.CaseReportDescriptionOfTheCaseProblemDao;
import Dao.CaseReportNeedsAssesmentDao;
import Dao.CaseReportNextOfKinDao;
import Dao.CaseReportParentsGuardiansSpousesInformationDao;
import Models.CaseReportPaymentsToBeneficiaries;

@Database(entities = {
        CaseReport.class,
        CaseReportClientInformation.class,
        CaseReportDescriptionOfTheCaseProblem.class,
        CaseReportNeedsAssesment.class,
        CaseReportNextOfKin.class,
        CaseReportCareGiver.class,
        CaseReportParentsGuardiansSpousesInformation.class,
        CaseReportJustificationReportForAttendedCases.class,
        CaseReportPaymentsToBeneficiaries.class,
        CasePlanCaseWorkplan.class,
        CasePlanCaseLog.class,
        CaseReportCasePlanAndFollowUp.class,
}, version = 17)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CaseReportDao caseReportDao();

    public abstract CaseReportClientInformationDao caseReportClientInformationDao();

    public abstract CaseReportDescriptionOfTheCaseProblemDao caseReportDescriptionOfTheCaseProblemDao();

    public abstract CaseReportNeedsAssesmentDao caseReportNeedsAssesmentDao();

    public abstract CaseReportNextOfKinDao caseReportNextOfKinDao();

    public abstract CaseReportCareGiverDao caseReportCareGiverDao();

    public abstract CaseReportParentsGuardiansSpousesInformationDao caseReportParentsGuardiansSpousesInformationDao();

    public abstract CaseReportJustificationReportForAttendedCasesDao caseReportJustificationReportForAttendedCasesDao();

    public abstract CaseReportPaymentsToBeneficiariesDao caseReportPaymentsToBeneficiariesDao();

    public abstract CaseReportCasePlanAndFollowUpDao caseReportCasePlanAndFollowUpDao();

    public abstract CasePlanCaseLogDao casePlanCaseLogDao();

    public abstract CasePlanCaseWorkPlanDao casePlanCaseWorkPlanDao();

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
