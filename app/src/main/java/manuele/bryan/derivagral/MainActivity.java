package manuele.bryan.derivagral;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import manuele.bryan.derivagral.Adapters.SectionsPagerAdapter;


public class MainActivity extends Activity implements ActionBar.TabListener, FragComm {
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    
    TextView equationTextView;

    String equationString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equationTextView = (TextView) findViewById(R.id.equationText);

        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void respond(String data) {
        if (data.equals(getString(R.string.enter))) {
            createAnswerDialog();
            return;
        }
        else if (data.equals(getString(R.string.c))) {
            equationString = "";
        }
        else if (data.equals(getString(R.string.delete))) {
            if (equationString.length() > 0) {
                equationString = equationString.substring(0, equationString.length() - 1);
            }
        } else {
            equationString = equationString + data;
        }

        equationTextView.setText(equationString);
    }

    private void createAnswerDialog() {
        SolutionDialog solutionDialog = SolutionDialog.newInstance(equationString);

        solutionDialog.show(getFragmentManager(), "solutionDialog");
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

}
