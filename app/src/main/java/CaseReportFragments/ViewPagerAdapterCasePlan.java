package CaseReportFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapterCasePlan extends FragmentPagerAdapter {

    public ViewPagerAdapterCasePlan(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0)
            fragment = new CasePlanAndFollowUpFragment();
        else if (position == 1)
            fragment = new CaseWorkPlanListFragment();//renamed to beneficiary
        else if (position == 2)
            fragment = new CaseLogListFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Case Plan & Follow Up";
        else if (position == 1)
            title = "Work Plan";
        else if (position == 2)
            title = "Case Log";

        return title;
    }
}
