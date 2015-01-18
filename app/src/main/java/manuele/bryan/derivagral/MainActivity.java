package manuele.bryan.derivagral;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import manuele.bryan.derivagral.Adapters.SectionsPagerAdapter;


public class MainActivity extends Activity implements ActionBar.TabListener, FragComm {
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    
    @InjectView(R.id.editText) TextView textView;

    ArrayList<String> arrayEquation = new ArrayList<String>();
    String yEquals = "y=";
    String equationString = "";
    String equationLatex = "";

    String derivativeEquation = "";
    String intergralEquation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getActionBar();

        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                             .setText(mSectionsPagerAdapter.getPageTitle(i))
                             .setTabListener(this)
            );
        }

        ButterKnife.inject(this);
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
            solveDerivative();
            solveIntegral();
            enterEquation();
            return;
        }
        else if (data.equals(getString(R.string.c))) {
            arrayEquation.clear();
        }
        else if (data.equals(getString(R.string.delete))) {
            if (arrayEquation.size() > 0) {
                arrayEquation.remove(arrayEquation.size() - 1);
            }
        } else {
            arrayEquation.add(data);
        }

        formatEquation();

        textView.setText(yEquals + equationString);
//        textView.setText(Html.fromHtml("2<small><sup>5</sup></small>"));
    }

    public void formatEquation() {
        equationToString();
        equationToLatex();
    }

    public String equationToString() {
        equationString = "";
        for (String part : arrayEquation) {
            equationString = equationString + part;
        }
        return equationString;
    }

    public String equationToLatex() {
        String equation = equationToString();

        String equationLatex = "f(x) = ";

        for (int i = 0; i < equation.length(); i++) {
            Character character = equation.charAt(i);
            equationLatex = equationLatex + character;

            //in the case of exponents
            if(character.equals('(') && i > 0 && ((Character) equation.charAt(i-1)).equals('^')) {
                equationLatex = equationLatex.substring(0, equationLatex.length() - 1);
                equationLatex = equationLatex + "{(";

                int leftParenCount = -1;
                for (int j = i; j < equation.length(); j++) {
                    Character jCharacter = equation.charAt(j);
                    if (jCharacter.equals(')')) {
                        if (leftParenCount == 0) {
                            equation = equation.substring(0, j)
                                    + ")}"
                                    + equation.substring(j+1, equation.length());
                            break;
                        } else {
                            leftParenCount--;
                        }
                    } else if (jCharacter.equals('(')) {
                        leftParenCount++;
                    }
                    if (j == equation.length()-1) {

                        equation = equation.substring(0, j)
                                + ")}";
                        break;
                    }
                }
            }
        }
        return equationLatex;
    }

    private void solveDerivative() {
        
    }

    private void solveIntegral() {

    }

    private void enterEquation() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("y=" + equationString
                                    + "\n"
                                    + "y'=" + equationString)
                          .setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

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

    private String doubleEscapeTeX(String s) {
        String t="";
        for (int i=0; i < s.length(); i++) {
            if (s.charAt(i) == '\'') t += '\\';
            if (s.charAt(i) != '\n') t += s.charAt(i);
            if (s.charAt(i) == '\\') t += "\\";
        }
        return t;
    }

}
