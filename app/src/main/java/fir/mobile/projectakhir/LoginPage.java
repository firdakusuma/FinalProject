package fir.mobile.projectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private EditText etEmail, etPw;
    private Button btMasuk;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        etEmail = this.findViewById(R.id.lgnEmail);
        etPw = this.findViewById(R.id.lgnPw);

        btMasuk = this.findViewById(R.id.btMasuk);

        mAuth = FirebaseAuth.getInstance();

        btMasuk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email, password;
        email = etEmail.getText().toString();
        password = etPw.getText().toString();
        switch (view.getId()) {
            case R.id.btMasuk:
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginPage.this, "Enter your Email or Password", Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void
                            onComplete(@NonNull Task<AuthResult> task) {
                                if
                                (task.isSuccessful()) {
// Sign in success,update UI with the signed-in user's information
                                    Toast.makeText(LoginPage.this, "Login Success.", Toast.LENGTH_SHORT).show();
                                           /* Intent i = new
                                                    Intent(LoginPage.this, Profil.class);
                                            startActivity(i);*/
                                }
                                else {
// If sign infails, display a message to the user.
                                    Toast.makeText(LoginPage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
        }
    }
}
