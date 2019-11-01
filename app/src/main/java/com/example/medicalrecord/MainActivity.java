package com.example.medicalrecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void upload(View V)
    {
        final EditText email=findViewById(R.id.edit);
        EditText password=findViewById(R.id.edit1);
        final String str=email.getText().toString().trim();
        final String str1=password.getText().toString();
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(str, str1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this,"Sucess",Toast.LENGTH_LONG);
                            Log.d("Sucess ", "signInWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent in=new Intent(getApplicationContext(), upload_fetch.class);
                            String[] em=str.split("@");
                            in.putExtra("user", em[0]);
                            startActivity(in);

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this," Failure ",Toast.LENGTH_LONG).show();
                            Log.w("Fail ", "signInWithEmail:failure", task.getException());

                          //  Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_LONG);
                            //updateUI(null);
                        }

                        // ...
                    }
                });
       /* DatabaseReference db;
        db=FirebaseDatabase.getInstance().getReference("users");
       // ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        String id = db.push().getKey();


        Toast.makeText(this," DATA IS UPLOADED "+db,Toast.LENGTH_LONG).show();

        //db.setValue("Hello world");
        // Read from the database
        Log.d("Message ","upload "+str);
        Log.d("Message ", "upload: "+db.child(id).setValue(new Artist(id,str)));;
     /*   ValueEventListener valueEventListener = db.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Message ", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Message ", "Failed to read value.", error.toException());
            }
        });
*/

    }
    public void register(View v)
    {
        Intent in=new Intent(getApplicationContext(), Register.class);
        startActivity(in);
    }

}
