package eu.jpmena.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

        // currency and percent formatter objects
        private static final NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance();
        private static final NumberFormat percentFormat =
                NumberFormat.getPercentInstance();

        private double loanAmount = 0.0; // the amount I have to (entered by the user)
        private double rate = 0.15; // rate I negociated for  my loan percentage
        private int years = 1; //initial number of years for my loan
        private TextView amountTextView; // shows formatted bill amount
        private TextView rateTextView; // shows rate Amount I should negociate
        private TextView yearsTextView; // shows the number of years I expect to pay ...
        private TextView monthlyTextView; // shows calculated value have to pay (to be compared with my actual rent)

        // called when the activity is first created
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState); // call superclass onCreate
            setContentView(R.layout.activity_main); // inflate the GUI

            // get references to programmatically manipulated TextViews
            amountTextView = (TextView) findViewById(R.id.amountTextView);
            rateTextView = (TextView) findViewById(R.id.rateTextView);
            yearsTextView = (TextView) findViewById(R.id.yearsTextView);
            monthlyTextView = (TextView) findViewById(R.id.monthlyTextView);
            monthlyTextView.setText(currencyFormat.format(0));

            // set amountEditText's TextWatcher
            EditText amountEditText =
                    (EditText) findViewById(R.id.amountEditText);
            amountEditText.addTextChangedListener(amountEditTextWatcher);

            // set percentSeekBar's OnSeekBarChangeListener
            SeekBar rateSeekBar =
                    (SeekBar) findViewById(R.id.rateSeekBar);
            rateSeekBar.setOnSeekBarChangeListener(rateBarListener);

            // set percentSeekBar's OnSeekBarChangeListener
            SeekBar yearsSeekBar =
                    (SeekBar) findViewById(R.id.yearsSeekBar);
            rateSeekBar.setOnSeekBarChangeListener(yearsBarListener);
        }

        /* calculate how much money you owe for the loan
        ** following the underneath formula !!!
        ** http://forum.actufinance.fr/formule-mathematique-de-vpm-vc-et-va-P45065/
         */
        private void calculate() {
            // format percent and display in percentTextView
            percentTextView.setText(percentFormat.format(percent));

            // calculate the tip and total
            double tip = billAmount * percent;
            double total = billAmount + tip;

            // display tip and total formatted as currency
            tipTextView.setText(currencyFormat.format(tip));
            totalTextView.setText(currencyFormat.format(total));
        }

        // listener object for the SeekBar's progress changed events
        private final SeekBar.OnSeekBarChangeListener rateBarListener =
                new SeekBar.OnSeekBarChangeListener() {
                    // update percent, then call calculate
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        rate = progress / 100.0; // set percent based on progress
                        calculate(); // calculate and display tip and total
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) { }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) { }
                };

    // listener object for the SeekBar's progress changed events
    private final SeekBar.OnSeekBarChangeListener yearsBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                // update percent, then call calculate
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    percent = progress / 100.0; // set percent based on progress
                    calculate(); // calculate and display tip and total
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            }

        // listener object for the EditText's text-changed events
        private final TextWatcher amountEditTextWatcher = new TextWatcher() {
            // called when the user modifies the bill amount
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                try { // get bill amount and display currency formatted value
                    billAmount = Double.parseDouble(s.toString()) / 100.0;
                    amountTextView.setText(currencyFormat.format(billAmount));
                }
                catch (NumberFormatException e) { // if s is empty or non-numeric
                    amountTextView.setText("");
                    billAmount = 0.0;
                }

                calculate(); // update the tip and total TextViews
            }

            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) { }
        };
    }

