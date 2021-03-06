package com.example.hexcalc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class HexCalc extends ActionBarActivity {
    final String TAG = "HexCalc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Typeface type;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hex);
        LinearLayout topLevel = (LinearLayout) findViewById(R.id.top_level);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        // FIXME -- is this the correct size?
        int margin = 5;
        int buttonSize = (width - (margin * 4)) / 10;
        int textSize = (int) ((double) buttonSize * .5);

        type = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        ((TextView) findViewById(R.id.hexLabel)).setTypeface(type, Typeface.BOLD);
        ((TextView) findViewById(R.id.hexLabel)).setTextSize(textSize);

        ((TextView) findViewById(R.id.decimalLabel)).setTypeface(type, Typeface.BOLD);
        ((TextView) findViewById(R.id.decimalLabel)).setTextSize(textSize);

        ((TextView) findViewById(R.id.hex)).setTypeface(type, Typeface.BOLD);
        ((TextView) findViewById(R.id.hex)).setTextSize(textSize);

        ((TextView) findViewById(R.id.decimal)).setTypeface(type, Typeface.BOLD);
        ((TextView) findViewById(R.id.decimal)).setTextSize(textSize);

        ((TextView) findViewById(R.id.errorMessage)).setTextSize(textSize);


        final String[] labels = {
                "0",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
        };

        int[] buttons = {
                R.id.b0,
                R.id.b1,
                R.id.b2,
                R.id.b3,
                R.id.b4,
                R.id.b5,
                R.id.b6,
                R.id.b7,
                R.id.b8,
                R.id.b9,
                R.id.ba,
                R.id.bb,
                R.id.bc,
                R.id.bd,
                R.id.be,
                R.id.bf
        };

        for (int i = 0; i < buttons.length; i++) {
            ((Button) findViewById(buttons[i])).setTypeface(type, Typeface.BOLD);
            ((Button) findViewById(buttons[i])).setTextSize(textSize);
            ((Button) findViewById(buttons[i])).setWidth(buttonSize);
            ((Button) findViewById(buttons[i])).setHeight(buttonSize);
            ((Button) findViewById(buttons[i])).setText(labels[i]);

            final int ii = i;
            findViewById(buttons[i]).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ((TextView) findViewById(R.id.hex))
                            .setText(((TextView) findViewById(R.id.hex)).getText()
                                    .toString() + labels[ii]);
                    convert();
                }
            });
        }

        ((Button) findViewById(R.id.back)).setTextSize(textSize);
        ((Button) findViewById(R.id.back)).setTypeface(type, Typeface.BOLD);
//        ((Button) findViewById(R.id.back)).setText("\u2b05");
        ((Button) findViewById(R.id.back)).setText("\uf0a5");
        ((Button) findViewById(R.id.back)).setWidth(buttonSize);
        ((Button) findViewById(R.id.back)).setHeight(buttonSize);
        findViewById(R.id.back).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                back();
                convert();
            }
        });

        ((Button) findViewById(R.id.clear)).setTextSize(textSize);
        ((Button) findViewById(R.id.clear)).setTypeface(type, Typeface.BOLD);
        ((Button) findViewById(R.id.clear)).setText("\uf00d");
        ((Button) findViewById(R.id.clear)).setWidth(buttonSize);
        ((Button) findViewById(R.id.clear)).setHeight(buttonSize);
        findViewById(R.id.clear).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                clear();

            }
        });
    }

    private void clear() {
        ((TextView) findViewById(R.id.hex)).setText("");
        ((TextView) findViewById(R.id.decimal)).setText("");
        ((TextView) findViewById(R.id.errorMessage)).setText("");
    }

    private void back() {
        TextView ed = (TextView) findViewById(R.id.hex);
        String str = ed.getText().toString();
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
            ed.setText(str);
        }
    }

    private void convert() {

        ((TextView) findViewById(R.id.errorMessage)).setText(" ");

        TextView hex = (TextView) findViewById(R.id.hex);
        TextView dec = (TextView) findViewById(R.id.decimal);

        String str;
        try {
            str =
                    NumberFormat.getNumberInstance(Locale.getDefault()).
                            format(Long.parseLong(hex.getText().toString(), 16));
            dec.setText(str);
        } catch (Exception e) {
            //((TextView) findViewById(R.id.errorMessage)).setText("" + e);
            dec.setText("");
            if (!hex.getText().toString().trim().equals("")) {
                ((TextView) findViewById(R.id.errorMessage)).setText("Overflow");
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, " onStart called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, " onResume called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy called");
    }
}
