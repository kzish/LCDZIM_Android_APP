package CaseReportFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapterCaseReport extends FragmentPagerAdapter {

    public ViewPagerAdapterCaseReport(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0)
            fragment = new BasicInformationFragment();
        else if (position == 1)
            fragment = new BeneficiaryInformationFragment();//renamed to beneficiary
        else if (position == 2)
            fragment = new PGSInformationFragment();
        else if (position == 3)
            fragment = new NextOfKinFragment();
        else if (position == 4)
            fragment = new CareGiverInformationFragment();
        else if (position == 5)
            fragment = new DescriptionOfCaseFragment();
        else if (position == 6)
            fragment = new NeedsAssesmentFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Basic Information";
        else if (position == 1)
            title = "Beneficiary Information";
        else if (position == 2)
            title = "P/G/S Information";
        else if (position == 3)
            title = "Next of kin";
        else if (position == 4)
            title = "Care Giver";
        else if (position == 5)
            title = "Description of Case";
        else if (position == 6)
            title = "Needs Assesment";

        return title;
    }
}
