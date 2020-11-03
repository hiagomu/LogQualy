package com.example.logqualy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView mainNovaConta;
    private TextView mainEsqueceuSenha;
    private EditText mainEmailEditTxt;
    private EditText mainPassEditTxt;
    private Button mainLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        novaConta();
        carregarCampos();

    }

    private void carregarCampos() {
        mainNovaConta = findViewById(R.id.mainCreateAccTxt);
        mainEsqueceuSenha = findViewById(R.id.mainForgetPassTxt);
        mainEmailEditTxt = findViewById(R.id.mainEmailEditTxt);
        mainPassEditTxt = findViewById(R.id.mainPassEditTxt);
        mainLoginButton = findViewById(R.id.mainLoginButton);
    }

    private void novaConta() {
        mainNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNewUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        mainLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}