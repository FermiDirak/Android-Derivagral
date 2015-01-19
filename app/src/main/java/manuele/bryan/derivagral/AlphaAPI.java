package manuele.bryan.derivagral;

import android.content.Context;
import android.widget.Toast;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class AlphaAPI {
    private static String APP_ID = "PAYY7V-78PRPAP9WA";

    public static final int FUNCTION = 0;
    public static final int DERIVATIVE = 1;
    public static final int INTEGRAL = 2;

    public String functionURL = "";
    public String derivativeFunction = "";
    public String integralFunction = "";

    public AlphaAPI(Context context, String input) {
        WAEngine engine = new WAEngine();
        engine.setAppID(APP_ID);
        engine.addFormat("plaintext");

        WAQuery query = engine.createQuery();
        query.setInput(input);

        try {
            WAQueryResult queryResult = engine.performQuery(query);

            if (queryResult.isError()) {
                Toast.makeText(context, "Query didn't go through. Check internet connection.", Toast.LENGTH_SHORT).show();
            } else if (!queryResult.isSuccess()) {
                Toast.makeText(context, "Query was not understood; no results available.",Toast.LENGTH_SHORT).show();
            } else {
                // Got a result.

                for (WAPod pod : queryResult.getPods()) {
                    if (!pod.isError()) {
                        if (pod.getID().equals("Input")) {
                            retrieveSubPod(pod, FUNCTION);
                        } else if (pod.getID().equals("Derivative")) {
                            retrieveSubPod(pod, DERIVATIVE);
                        } else if (pod.getID().equals("IndefiniteIntegral")) {
                            retrieveSubPod(pod, INTEGRAL);
                        }
                    }
                }
            }
        } catch (WAException e) {
            e.printStackTrace();
        }
    }

    public void retrieveSubPod(WAPod pod, int type) {
        for (WASubpod subpod : pod.getSubpods()) {
            for (Object element : subpod.getContents()) {
                if (element instanceof WAPlainText) {
                    String function = ((WAPlainText) element).getText();
                    System.out.println("TEST: " + function);

                    switch (type) {
                        case FUNCTION:
                            functionURL = function;
                            return;
                        case DERIVATIVE:
                            derivativeFunction = function;
                            return;
                        case INTEGRAL:
                            integralFunction = function;
                            return;
                    }
                }
            }
        }
    }

    public String getFunctionURL() {
        return functionURL;
    }

    public String getDerivativeFunction() {
        return derivativeFunction;
    }

    public String getIntegralFunction() {
        return integralFunction;
    }



}


