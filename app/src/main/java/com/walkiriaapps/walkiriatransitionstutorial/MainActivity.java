package com.walkiriaapps.walkiriatransitionstutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Button button;

    public static int slideDown = R.anim.slide_down;
    public static int slideUpInterpolator = R.anim.slide_in_up;
    public static int slideOutUp = R.anim.slide_out_up;
    public static int slideUp = R.anim.slide_up;
    public static int translateLeft = R.anim.translate_left_side;
    public static int translateRight = R.anim.translate_right_side;
    public static int zoomIn = R.anim.zoom_in;
    public static int zoomOut = R.anim.zoom_out;

    public static int[] transitions = new int[]{slideDown, slideUpInterpolator, slideOutUp, slideUp, translateLeft, translateRight, zoomIn, zoomOut};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchFragment(new FirstFragment(), 0, 0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        button = findViewById(R.id.button_next_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondaryActivity.class));
                overridePendingTransition(translateLeft, 0);
            }
        });
    }

    public void switchFragment(Fragment fragment, int enterAnimation, int exitAnimation) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, 0, 0, exitAnimation);
        ft.replace(R.id.frame_container, fragment);
        ft.addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            getSupportFragmentManager().popBackStack();
        else
            finish();
    }
}