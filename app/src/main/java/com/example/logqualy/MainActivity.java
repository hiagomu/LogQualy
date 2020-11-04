package com.example.logqualy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CreateNewUserActivity";
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

        carregarCampos();
        novaConta();
        login();
    }

    private void carregarCampos() {
        mAuth = FirebaseAuth.getInstance();
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
                String email = mainEmailEditTxt.getText().toString();
                String password = mainPassEditTxt.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }

            private void updateUI (FirebaseUser user) {
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}