package manuele.bryan.derivagral;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class NumberPadTwo extends Fragment {
    FragComm comm;

    List<Button> buttons = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number_pad_two, container, false);

        Button sin = (Button) view.findViewById(R.id.sin);
        Button cos = (Button) view.findViewById(R.id.cos);
        Button tan = (Button) view.findViewById(R.id.tan);
        Button pi  = (Button) view.findViewById(R.id.pi);
        Button arcsin = (Button) view.findViewById(R.id.arcsin);
        Button arccos = (Button) view.findViewById(R.id.arccos);
        Button arctan = (Button) view.findViewById(R.id.arctan);
        Button e   = (Button) view.findViewById(R.id.e);

        buttons.add(sin);
        buttons.add(cos);
        buttons.add(tan);
        buttons.add(pi);
        buttons.add(arcsin);
        buttons.add(arccos);
        buttons.add(arctan);
        buttons.add(e);

        for (final Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String typed = button.getText().toString();

                    comm.respond(typed);
                }
            });
        }

        return view;
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
