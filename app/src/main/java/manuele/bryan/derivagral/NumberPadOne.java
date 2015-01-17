package manuele.bryan.derivagral;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

public class NumberPadOne extends Fragment {
    FragComm comm;

    Button bBlackThree, bDelete, bC, bDivide,
            bLeftParen, bRightParen, bPower, bMultiply,
            bSeven, bEight, bNine, bMinus,
            Bfour, bFive, bSix, bPlus,
            bOne, bTwo, bThree, bBlankTwo,
            bZero, bDecimal, bBlank;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_number_pad_one, container, false);
        ButterKnife.inject(this, view);

        setUpFont();

        return view;
    }

    private void setUpFont() {
        bBlackThree.findViewById(R.id.blankThree);
        bDelete.findViewById(R.id.delete);
        bC.findViewById(R.id.c);
        bDivide.findViewById(R.id.divide);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (FragComm) getActivity();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({  R.id.blankThree, R.id.delete, R.id.c, R.id.divide,
            R.id.leftParen, R.id.rightParen, R.id.power, R.id.multiply,
            R.id.seven, R.id.eight, R.id.nine, R.id.minus,
            R.id.four, R.id.five, R.id.six, R.id.plus,
            R.id.one, R.id.two, R.id.three, R.id.blankTwo,
            R.id.zero, R.id.decimal, R.id.blank })
    public void click(Button button) {
        String typed = button.getText().toString();

        //special cases
        if (button.getText().toString().equals(getString(R.string.power))) {
            typed = "^(";
        }

        comm.respond(typed);
    }

}
