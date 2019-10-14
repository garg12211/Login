package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView info;
    private Button login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       email = (EditText)findViewById(R.id.etEmail);
       password = (EditText)findViewById(R.id.etPassword);
       info = (TextView)findViewById(R.id.tvInfo);
       login = (Button)findViewById((R.id.btnLogin));
       userRegistration = (TextView)findViewById(R.id.tvRegister);

       info.setText("No. of attempts remaining: " + String.valueOf(counter));

       firebaseAuth = FirebaseAuth.getInstance();

       FirebaseUser user = firebaseAuth.getCurrentUser();

       if(user != null){
           finish();
           startActivity(new Intent(MainActivity.this, SecondActivity.class));
       }

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Log.d("Useremail", email.getText().toString());
                validatePassword(email.getText().toString(), password.getText().toString());
           }
       });

       userRegistration.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
           }
       });

    }


    private void validatePassword(String userEmail, String userPassword){
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login was successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Login was not successful", Toast.LENGTH_SHORT).show();
                    counter--;
                    info.setText("No. of attempts remaining: " + counter);
                    if(counter == 0){
                        login.setEnabled(false);
                    }
                }
            }
        });

    }
}
