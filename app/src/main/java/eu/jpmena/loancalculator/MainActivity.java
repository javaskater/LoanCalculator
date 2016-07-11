package eu.jpmena.loancalculator;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // currency and percent formatter objects
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    private double loanAmount = 0.0; // the amount I have to (entered by the user)
    private double rate = 2; // rate I negociated for  my loan percentage
    private int years = 15; //initial number of years for my loan
    private TextView amountTextView; // shows formatted bill amount
    private TextView rateTextView; // shows rate Amount I should negociate
    private TextView yearsTextView; // shows the number of years I expect to pay ...
    private TextView monthlyTextView; // shows calculated value have to pay (to be compared with my actual rent)
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        rateSeekBar.setOnSeekBarChangeListener(rateBarListener); //Problème à la définition du rateSeekBarListener !!!!

        // set percentSeekBar's OnSeekBarChangeListener
        SeekBar yearsSeekBar =
                (SeekBar) findViewById(R.id.yearsSeekBar);
        yearsSeekBar.setOnSeekBarChangeListener(yearsBarListener);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /* calculate how much money you owe for the loan
    ** following the underneath formula !!!
    ** http://forum.actufinance.fr/formule-mathematique-de-vpm-vc-et-va-P45065/
     */
    private void calculate() {
        // format rate and display in rateTextView
        rateTextView.setText(percentFormat.format(rate));

        // format rate and display in rateTextView
        Integer yearsInt = new Integer(years);
        yearsTextView.setText(yearsInt.toString());
        // calculate  what I will have to pay monthly ...
        double monthlyamount = Math.pow(loanAmount * rate / (1 - (1 + rate)), -years * 12.0);
        ///show it to me !!!
        monthlyTextView.setText(currencyFormat.format(monthlyamount));
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
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            };

    // listener object for the SeekBar's progress changed events
    private final SeekBar.OnSeekBarChangeListener yearsBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                // update percent, then call calculate
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    years = progress; // set percent based on progress
                    calculate(); // calculate and display tip and total
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            };

    // listener object for the EditText's text-changed events
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get bill amount and display currency formatted value
                loanAmount = Double.parseDouble(s.toString());
                amountTextView.setText(currencyFormat.format(loanAmount));
            } catch (NumberFormatException e) { // if s is empty or non-numeric
                amountTextView.setText("");
                loanAmount = 0.0;
            }

            calculate(); // update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://eu.jpmena.loancalculator/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://eu.jpmena.loancalculator/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

