package CaseReportFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0)
            fragment = new BasicInformationFragment();
        else if (position == 1)
            fragment = new ClientInformationFragment();
        else if (position == 2)
            fragment = new PGSInformationFragment();
        else if (position == 3)
            fragment = new NextOfKinFragment();
        else if (position == 4)
            fragment = new DescriptionOfCaseFragment();
        else if (position == 5)
            fragment = new NeedsAssesmentFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Basic Information";
        else if (position == 1)
            title = "Client Information";
        else if (position == 2)
            title = "P/G/S Information";
        else if (position == 3)
            title = "Next of kin";
        else if (position == 4)
            title = "Description of Case";
        else if (position == 5)
            title = "Needs Assesment";

        return title;
    }
}
