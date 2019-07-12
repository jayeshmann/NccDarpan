package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.PositionSpringAnimation;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;


public class CertificateTypeFragment extends Fragment {
    View view;

    private TextView animateView1;
    private TextView animateView2;
    private TextView animateView3;


    private String url=GLOBAL.baseURL+"content/EXAMINATION/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aims_layout, content_frame, false);
        initUI(view);
        prepareAnimations();

        return view;

    }


    private void initUI(View view) {
        animateView1 = view.findViewById(R.id.animate_tv1);
        animateView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url = url + "A_CERT/cert_A.docx";
                Toast.makeText(getActivity(), "Toast", Toast.LENGTH_SHORT).show();
                ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, "CERTIFICATE A", true);
            }
        });

        animateView2 = view.findViewById(R.id.animate_tv2);
        animateView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url = url + "B_CERT/cert_B.docx";
                ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, "CERTIFICATE B", true);
            }
        });

        animateView3 = view.findViewById(R.id.animate_tv3);
        animateView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url = url + "C_CERT/cert_C.docx";
                ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, "CERTIFICATE C", true);
            }
        });

    }


    private void prepareAnimations() {

       /* PositionSpringAnimation positionSpringAnimation =
                new PositionSpringAnimation(animateView1);

        PositionSpringAnimation positionSpringAnimation2 =
                new PositionSpringAnimation(animateView2);

        PositionSpringAnimation positionSpringAnimation3 =
                new PositionSpringAnimation(animateView3);*/

    }


    private void startSpringAnimation(View view){
        // create an animation for your view and set the property you want to animate
        SpringAnimation animation = new SpringAnimation(view, SpringAnimation.X);
        // create a spring with desired parameters
        SpringForce spring = new SpringForce();
        // can also be passed directly in the constructor
        spring.setFinalPosition(100f);
        // optional, default is STIFFNESS_MEDIUM
        spring.setStiffness(SpringForce.STIFFNESS_LOW);
        // optional, default is DAMPING_RATIO_MEDIUM_BOUNCY
        spring.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        // set your animation's spring
        animation.setSpring(spring);
        // animate!
        animation.start();
    }

}