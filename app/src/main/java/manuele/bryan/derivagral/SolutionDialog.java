package manuele.bryan.derivagral;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class SolutionDialog extends DialogFragment {

    public static final String FUNCTION_BUNDLE_KEY = "function";

    private Context context;

    private TextView functionTextView;
    private TextView derivativeTextView;
    private TextView integralTextView;

    public String function;

    public static SolutionDialog newInstance(String function) {
        SolutionDialog solutionDialog = new SolutionDialog();

        Bundle bundle = new Bundle();
        bundle.putString(FUNCTION_BUNDLE_KEY, function);
        solutionDialog.setArguments(bundle);

        return solutionDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        function = getArguments().getString(FUNCTION_BUNDLE_KEY);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_solution, null);

        functionTextView = (TextView) view.findViewById(R.id.functionTextView);
        derivativeTextView = (TextView) view.findViewById(R.id.derivativeTextView);
        integralTextView = (TextView) view.findViewById(R.id.integralTextView);

        new DownloadSolutionTask().execute(function);

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    private class DownloadSolutionTask extends AsyncTask<String, String, AlphaAPI> {
        @Override
        protected AlphaAPI doInBackground(String... strings) {
            return new AlphaAPI(context, function);
        }

        @Override
        protected void onPostExecute(AlphaAPI alpha) {
            functionTextView.setText(alpha.getInputFunction());
            derivativeTextView.setText(alpha.getDerivativeFunction());
            integralTextView.setText(alpha.getIntegralFunction());

        }

    }

}
