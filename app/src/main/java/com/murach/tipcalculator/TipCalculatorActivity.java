package com.murach.tipcalculator;

import java.text.NumberFormat;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;
import android.widget.Toast;

import com.murach.tipcalculator.databinding.UsingTableViewBinding;

public class TipCalculatorActivity extends Activity 
implements OnEditorActionListener, OnClickListener
{

    // define instance variables for the widgets
    //Anas : No need
//    private EditText billAmountEditText;
//    private TextView percentTextView;
//    private Button   percentUpButton;
//    private Button   percentDownButton;
//    private TextView tipTextView;
//    private TextView totalTextView;
    private UsingTableViewBinding binding;

    
    // define an instance variable for tip percent
    private float tipPercent = .15f;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UsingTableViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        // get references to the widgets

        // Anas : No need
//        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
//        percentTextView = (TextView) findViewById(R.id.percentTextView);
//        percentUpButton = (Button) findViewById(R.id.percentUpButton);
//        percentDownButton = (Button) findViewById(R.id.percentDownButton);
//        tipTextView = (TextView) findViewById(R.id.tipTextView);
//        totalTextView = (TextView) findViewById(R.id.totalTextView);

        // set the listeners
        //Anas : change
//        billAmountEditText.setOnEditorActionListener(this);
//        percentUpButton.setOnClickListener(this);
//        percentDownButton.setOnClickListener(this);
        binding.billAmountEditText.setOnEditorActionListener(this);
        binding.percentUpButton.setOnClickListener(this);
        binding.percentDownButton.setOnClickListener(this);

        Toast.makeText( getApplicationContext() , "onCreate()", Toast.LENGTH_SHORT).show();

        calculateAndDisplay();
    }

    public void calculateAndDisplay() {        

        // get the bill amount
        //Anas :  add binding. before
        String billAmountString = binding.billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")) {
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);//cannot convert "34.60" to float!
        }
        
        // calculate tip and total 
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;
        
        // display the results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        //Anas :  add binding. before
        binding.tipTextView.setText(currency.format(tipAmount));//currency.format(tipAmount)
        binding.totalTextView.setText(currency.format(totalAmount));
        
        NumberFormat percent = NumberFormat.getPercentInstance();
        //Anas :  add binding. before

        binding.percentTextView.setText(percent.format(tipPercent));
    }
    
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();
        return false;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.percentDownButton:
            if (tipPercent >= 0.01f) { //TS bug fix
                tipPercent = tipPercent - .01f;
                calculateAndDisplay();
            }

            break;
        case R.id.percentUpButton:
            tipPercent = tipPercent + .01f;
            calculateAndDisplay();
            break;
        }
    }
}