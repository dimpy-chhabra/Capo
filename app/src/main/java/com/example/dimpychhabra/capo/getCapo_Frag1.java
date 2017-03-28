package com.example.dimpychhabra.capo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
/*
*Project : CAPO, fully created by
* Dimpy Chhabra, IGDTUW, BTech, IT
* Second year (as of 2017)
* Expected Class of 2019
* Please do not circulate as your own
* Criticism is appreciated to work on memory leaks and bugs
* Contact Info : Find me on Linked in : linkedin.com/in/dimpy-chhabra
*
*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class getCapo_Frag1 extends Fragment implements View.OnClickListener {

    private static View view;
    private static Button button;
    private static EditText to, from, fromTime;
    private static FragmentManager fragmentManager;

    public getCapo_Frag1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_get_capo__frag1, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        button = (Button) view.findViewById(R.id.b1);
        to = (EditText) view.findViewById(R.id.to);
        from = (EditText) view.findViewById(R.id.from);
        fromTime = (EditText) view.findViewById(R.id.fromTime);
    }

    private void setListeners() {
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                if (to.getText().toString().equals("") || fromTime.getText().toString().equals("") || from.getText().toString().equals("")) {
                    Log.e("in lets capo frag1", " equals null");
                    new CustomToast().Show_Toast(getActivity(), view, "Please enter All details in order to proceed.");
                } else {

                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                            .replace(R.id.frameContainer, new getCapo_Frag2(),
                                    BaseActivity.getCapo_Frag2)
                            .commit();

                }
                break;
            case R.id.to:
                Log.e("ablah ", " ablhad ");
                break;
        }
    }
}
