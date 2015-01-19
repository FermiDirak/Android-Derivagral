package manuele.bryan.derivagral;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class SolutionDialog extends DialogFragment {

    public static final String FUNCTION_BUNDLE_KEY = "function";

    private Context context;

    private ImageView functionImageView;
    private ImageView derivativeImageView;
    private ImageView integralImageView;

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

        functionImageView = (ImageView) view.findViewById(R.id.functionImageView);
        derivativeImageView = (ImageView) view.findViewById(R.id.derivativeImageView);
        integralImageView = (ImageView) view.findViewById(R.id.integralImageView);


        AlphaAPI alpha = new AlphaAPI(getActivity().getBaseContext(), function);

        String originalFunction = function;
        String derivativeFunction = alpha.getFunctionURL();
        String integralFunction = alpha.getDerivativeURL();

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

}
