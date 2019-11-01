package com.example.medicalrecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medicalrecord.Res;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void register(View v)
    {
        mAuth = FirebaseAuth.getInstance();
        EditText email1=findViewById(R.id.email);
        EditText name1=findViewById(R.id.name);
        final String email=email1.getText().toString();
        final String name =name1.getText().toString();
        EditText password1=findViewById(R.id.pass);
        EditText Addr=findViewById(R.id.pass1);
        final String Ad=Addr.getText().toString();
        String password=password1.getText().toString();
        EditText ph=findViewById(R.id.pass2);
        final String phone=ph.getText().toString();
        EditText ag=findViewById(R.id.age);
        final String age=ag.getText().toString();
        //Log.d("Login ", " Starting ");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            String[] em=email.split("@");
                            Log.d("Login ", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Res r;
                            r = new Res(name,Ad,phone,age);
                            myRef.child(em[0]).setValue(r);
                            Intent in=new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(in);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Login", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
