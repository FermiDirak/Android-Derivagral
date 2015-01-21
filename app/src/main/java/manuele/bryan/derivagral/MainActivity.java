package manuele.bryan.derivagral;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import manuele.bryan.derivagral.Adapters.SectionsPagerAdapter;


public class MainActivity extends Activity implements FragComm {
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    ProgressBar progressBar;
    TextView fXTextView;
    TextView equationTextView;

    String equationString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fXTextView = (TextView) findViewById(R.id.fXTextView);
        equationTextView = (TextView) findViewById(R.id.equationText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Typeface typeface = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Light.ttf");
        fXTextView.setTypeface(typeface);
        equationTextView.setTypeface(typeface);


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
        } else{
            equationString = equationString + data;
        }

        equationTextView.setText(equationString);
    }

    private void createAnswerDialog() {
        new DownloadSolutionTask().execute();
    }

    private class DownloadSolutionTask extends AsyncTask<String, String, AlphaAPI> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

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
            progressBar.setVisibility(View.GONE);

        }

        private void createAnswerDialog(String function, String derivative, String integral) {
            SolutionDialog solutionDialog = SolutionDialog.newInstance(function, derivative, integral);
            solutionDialog.show(getFragmentManager(), "solutionDialog");
        }

    }

}
