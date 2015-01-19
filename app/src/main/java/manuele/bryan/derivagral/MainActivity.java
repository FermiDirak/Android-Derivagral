package manuele.bryan.derivagral;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
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
        } else if (data.equals(getString(R.string.decimal))) {
            equationString = equationString + "x";
        } else{
            equationString = equationString + data;
        }

        equationTextView.setText(equationString);
    }

    private void createAnswerDialog() {
        new DownloadSolutionTask().execute();
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

    private class DownloadSolutionTask extends AsyncTask<String, String, AlphaAPI> {
        @Override
        protected AlphaAPI doInBackground(String... strings) {
            return new AlphaAPI(getBaseContext(), equationString);
        }

        @Override
        protected void onPostExecute(AlphaAPI alpha) {
            String function = alpha.getInputFunction();
            String derivative = alpha.getDerivativeFunction();
            String integral = alpha.getIntegralFunction();

            createAnswerDialog(function, derivative, integral);

        }

        private void createAnswerDialog(String function, String derivative, String integral) {
            SolutionDialog solutionDialog = SolutionDialog.newInstance(function, derivative, integral);
            solutionDialog.show(getFragmentManager(), "solutionDialog");
        }

    }

}
