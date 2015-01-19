package manuele.bryan.derivagral;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAImage;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class AlphaAPI {

//brah. You need three libraries:
//commons-codec-1.3
//commons-logging-1.1.1
//http-client-4.0.1
//http-core-4.0.1


    // PUT YOUR APPID HERE:
    private static String appid = "PAYY7V-78PRPAP9WA";

    public static void main(String[] args) {

        // Use "pi" as the default query, or caller can supply it as the lone command-line argument.
        String input = "x^2 + 4";
        if (args.length > 0)
            input = args[0];

        // The WAEngine is a factory for creating WAQuery objects,
        // and it also used to perform those queries. You can set properties of
        // the WAEngine (such as the desired API output format types) that will
        // be inherited by all WAQuery objects created from it. Most applications
        // will only need to create one WAEngine object, which is used throughout
        // the life of the application.
        WAEngine engine = new WAEngine();

        // These properties will be set in all the WAQuery objects created from this WAEngine.
        engine.setAppID(appid);
        engine.addFormat("image,plaintext");

        // Create the query.
        WAQuery query = engine.createQuery();

        // Set properties of the query.
        query.setInput(input);

        try {
            // For educational purposes, print out the URL we are about to send:
            System.out.println("Query URL:");
            System.out.println(engine.toURL(query));
            System.out.println("");

            WAQueryResult queryResult = engine.performQuery(query);

            if (queryResult.isError()) {
                //query didn't go through.
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
            } else if (!queryResult.isSuccess()) {
                //query went through, but not understood.
                System.out.println("Query was not understood; no results available.");
            } else {
                // Got a result.
                System.out.println("Successful query. Pods follow:\n");

                System.out.println("Function: \n ----------- \n" + input);

                for (WAPod pod : queryResult.getPods()) {
                    if (!pod.isError()) {

                        if (pod.getID().toString().equals("Derivative")) {
                            System.out.println("Derivative");

                            System.out.println("------------");
                            printSubPods(pod);
                        }

                        else if (pod.getID().toString().equals("IndefiniteIntegral")) {
                            System.out.println("IndefiniteIntegral");

                            System.out.println("------------");
                            printSubPods(pod);
                        }
                        System.out.println("");
                    }
                }
                // We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
                // These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
            }
        } catch (WAException e) {
            e.printStackTrace();
        }
    }

    public static void printSubPods(WAPod pod) {
        for (WASubpod subpod : pod.getSubpods()) {

            System.out.println(subpod.getContents().length);

            for (Object element : subpod.getContents()) {

                if (element instanceof WAPlainText) {
                    System.out.println(((WAPlainText) element).getText());
                    System.out.println("");
                } else if (element instanceof WAImage) {
                    System.out.println("still here!");
                    System.out.println(((WAImage) element).getURL());

                }
            }
        }
    }

}


