package manuele.bryan.derivagral;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class NumberPadOne extends Fragment {
    public FragComm comm;

    List<Button> buttons = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number_pad_one, container, false);

        Button bBlankThree = (Button) view.findViewById(R.id.blankThree);
        Button bDelete = (Button) view.findViewById(R.id.delete);
        Button bC = (Button) view.findViewById(R.id.c);
        Button bDivide = (Button) view.findViewById(R.id.divide);

        Button bLeftParen = (Button) view.findViewById(R.id.leftParen);
        Button bRightParen = (Button) view.findViewById(R.id.rightParen);
        Button bPower = (Button) view.findViewById(R.id.power);
        Button bMultiply = (Button) view.findViewById(R.id.multiply);

        Button bSeven = (Button) view.findViewById(R.id.seven);
        Button bEight = (Button) view.findViewById(R.id.eight);
        Button bNine = (Button) view.findViewById(R.id.nine);
        Button bMinus = (Button) view.findViewById(R.id.minus);

        Button bFour = (Button) view.findViewById(R.id.four);
        Button bFive = (Button) view.findViewById(R.id.five);
        Button bSix = (Button) view.findViewById(R.id.six);
        Button bPlus = (Button) view.findViewById(R.id.plus);

        Button bOne = (Button) view.findViewById(R.id.one);
        Button bTwo = (Button) view.findViewById(R.id.two);
        Button bThree = (Button) view.findViewById(R.id.three);
        Button bEquals = (Button) view.findViewById(R.id.enter);

        Button bZero = (Button) view.findViewById(R.id.zero);
        Button bDecimal = (Button) view.findViewById(R.id.decimal);

        buttons.add(bBlankThree);
        buttons.add(bDelete);
        buttons.add(bC);
        buttons.add(bDivide);
        buttons.add(bLeftParen);
        buttons.add(bRightParen);
        buttons.add(bPower);
        buttons.add(bMultiply);
        buttons.add(bSeven);
        buttons.add(bEight);
        buttons.add(bNine);
        buttons.add(bMinus);
        buttons.add(bFour);
        buttons.add(bFive);
        buttons.add(bSix);
        buttons.add(bPlus);
        buttons.add(bOne);
        buttons.add(bTwo);
        buttons.add(bThree);
        buttons.add(bEquals);
        buttons.add(bZero);
        buttons.add(bDecimal);

        for (final Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String typed = button.getText().toString();

                    comm.respond(typed);
                }
            });
        }

        setUpFont();

        return view;
    }

    //TODO: set up font
    private void setUpFont() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (FragComm) getActivity();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
