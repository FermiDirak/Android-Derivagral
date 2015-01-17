package manuele.bryan.derivagral.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.Locale;

import manuele.bryan.derivagral.NumberPadOne;
import manuele.bryan.derivagral.NumberPadTwo;
import manuele.bryan.derivagral.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    protected Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new NumberPadOne();
            case 1:
                return new NumberPadTwo();
            default:
                return new NumberPadOne();
        }
    }

    @Override
    public int getCount() {
        //show two total pages
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return mContext.getString(R.string.title_section1);
            case 1:
                return mContext.getString(R.string.title_section2);
            default:
                return mContext.getString(R.string.title_section3);
        }
    }
}
