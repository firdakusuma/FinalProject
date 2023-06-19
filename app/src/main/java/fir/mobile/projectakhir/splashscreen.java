package fir.mobile.projectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class splashscreen extends AppCompatActivity implements View.OnClickListener {
    private Button btnDaftar, btnMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        btnDaftar = findViewById(R.id.btnDaftar);
        btnMasuk = findViewById(R.id.btnLogin);

        btnDaftar.setOnClickListener(this);
        btnMasuk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDaftar) {
            Intent intent = new Intent(splashscreen.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(splashscreen.this, LoginPage.class);
            startActivity(intent);
        }
    }
}


