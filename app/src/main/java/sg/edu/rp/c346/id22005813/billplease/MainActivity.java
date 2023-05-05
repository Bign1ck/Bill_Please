package sg.edu.rp.c346.id22005813.billplease;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //Declaring filed variables
    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    EditText number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking Variables
        amount = findViewById(R.id.editAmount);
        numPax = findViewById(R. id. editPax);
        totalBill = findViewById(R.id.viewDisplayTotal);
        eachPays = findViewById(R.id.viewDisplayEach);
        svs = findViewById(R.id.toggleSVS);
        gst = findViewById(R.id.toggleSVS);
        split = findViewById(R.id.splitButton);
        reset = findViewById(R.id.resetButton);
        discount = findViewById(R.id.editDiscount);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().trim().length() != 0 &&
                    numPax.getText().toString().trim().length() != 0) {

                double origAmt = Double.parseDouble(amount.getText().toString());
                double newAmount = 0.0;

                if (!svs.isChecked() && !gst.isChecked()) {
                    newAmount = origAmt;
                } else if (svs.isChecked() && !gst.isChecked()) {
                    newAmount = origAmt * 1.1;
                } else if (!svs.isChecked() && gst.isChecked()) {
                    newAmount = origAmt * 1.07;
                } else{
                    newAmount = origAmt * 1.17;
                }
                //Discount
                if (discount.getText().toString().trim().length() != 0) {
                    newAmount *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                }

                totalBill.setText("Total Bill: $" + String.format("%.2f", newAmount));
                int numPerson = Integer.parseInt(numPax.getText().toString());
                if (numPerson != 1) {
                    eachPays.setText("Each Pays: $" + String.format("%.2f", newAmount / numPerson) +
                            " to 91324387");
                } else {
                    eachPays.setText("Each Pays: $" + newAmount + " to 91324387");
                }
                reset.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amount.setText("");
                        numPax.setText("");
                        svs.setChecked(false);
                        gst.setChecked(false);
                        discount.setText("");

                    }
                }));
            }
        }
    });

}
}

