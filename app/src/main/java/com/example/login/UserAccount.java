package com.example.login;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

public class UserAccount extends AppCompatActivity {

    private static float AMOUNT_PER_POINT = 100.00f;
    final EditText textInput = (EditText) findViewById(R.id.amountSpentInput);
    double amountSpent = Double.valueOf(textInput.getText().toString());
    Button submit = (Button) findViewById(R.id.submitButton);

    private TextView numPointsAdded = (TextView)findViewById(R.id.tvNumPointsAdded);


    //Database
    //CustomerDatabase customerDatabase;

    private BottomNavigationView bottom_navigation_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalPoints("GOLD", amountSpent);
            }
        });

        //customerDatabase = new CustomerDatabase(this);
        //adding customers
        //customerDatabase.addCustomer("90016844", "Ananya", "ananyagarg", "Silver", 200);
        //customerDatabase.addCustomer("87926754", "John", "johndoe", "Gold", 400);
        //customerDatabase.addCustomer("90276728", "Terry", "terrylovesyoghurt", "Bronze", 150);
        //customerDatabase.addCustomer("83004882", "Mary", "maryslambs", "Silver", 300);
        //customerDatabase.addCustomer("98208378", "Kevin", "kevinthepigeon", "Bronze", 50);

        bottom_navigation_bar = findViewById(R.id.bottom_navigation_bar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment());

        bottom_navigation_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.nav_scan:
                        fragment = new ScanFragment();
                        break;

                    case R.id.nav_transactions:
                        fragment = new TransactionsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

                return true;
            }
        });


    }

    //calculating points



    public void calculateTotalPoints(String tier, double amountSpent) {


        final int earnedPoints = (int) earnedPoints(amountSpent);
        int totalPointsEarned = (int) (earnedPoints + bonus(tier, earnedPoints));
        numPointsAdded.setText("Number of Points Added: " + totalPointsEarned);
    }



    private static double earnedPoints(double amountSpent){
        return amountSpent * AMOUNT_PER_POINT;
    }

    private static double bonus(String tier, int earnedPoints){
        if (tier == "BRONZE"){
            return 0.15f * earnedPoints;
        }

        if (tier == "SILVER"){
            return 0.3f * earnedPoints;
        }

        if (tier == "GOLD"){
            return 0.5f * earnedPoints;
        }

        return 0f;
    }

}



