package fir.mobile.projectakhir;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Nama, Email, Pass;
    private Button btDaftar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Nama = (EditText) findViewById(R.id.etNama);
        Email = (EditText) findViewById(R.id.etEmail);
        Pass = (EditText) findViewById(R.id.etPw);
        btDaftar = (Button) findViewById(R.id.btDaftar);

        mAuth = FirebaseAuth.getInstance();

        btDaftar.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
// Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //*updateUI(currentUser);*//*
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btDaftar:
                signUp(Email.getText().toString(), Pass.getText().toString());
                break;
        }
    }

    public void signUp(String email, String password) {
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
// Sign in success, update UI with the signed - in user 's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //*updateUI(user);*//*
                            Toast.makeText(MainActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginPage.class);
                            startActivity(intent);
                        } else {
// If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();
                            //* updateUI(null);*//*
                        }
                    }
                });
    }

    public void login(String email, String password) {
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new
                        OnCompleteListener<AuthResult>() {
                            @Override

                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
// Sign in success, update UI with the signed - in user 's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                                    /*updateUI(user);*/
                                } else {
// If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    /*updateUI(null);*/
                                }
                            }
                        });
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(Nama.getText().toString())) {
            Nama.setError("Required");
            result = false;
        }
        if (TextUtils.isEmpty(Email.getText().toString())) {
            Email.setError("Required");
            result = false;
        }
        else {
            Email.setError(null);
        }
        if (TextUtils.isEmpty(Pass.getText().toString())) {
            Pass.setError("Required");
            result = false;
        }
        else {
            Pass.setError(null);
        }
        return result;
    }


    public void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, LoginPage.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Register First",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
