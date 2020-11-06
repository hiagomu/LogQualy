package com.example.logqualy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.logqualy.R;
import com.example.logqualy.model.Product;

import static com.example.logqualy.ui.Constantes.PRODUCT_SAVE;

public class FormProductActivity extends AppCompatActivity {

    private EditText titleFormProd;
    private EditText descFormProd;
    private EditText dateFormProd;
    private Button saveFormProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product);

        carregarCampos();
        salvarForm();
    }

    private void carregarCampos() {
        titleFormProd = findViewById(R.id.titleFormProdEditTxt);
        descFormProd = findViewById(R.id.descFormProdEditTxt);
        dateFormProd = findViewById(R.id.dateFormProdEditTxt);
        saveFormProd = findViewById(R.id.saveFormProdButton);
    }

    private void salvarForm() {
        saveFormProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = getDados();
                Intent intent = new Intent(FormProductActivity.this, ProductListActivity.class);
                intent.putExtra(PRODUCT_SAVE, product);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private Product getDados() {
        if (validateForm()) {
            String title = titleFormProd.getText().toString();
            String desc = descFormProd.getText().toString();
            String date = dateFormProd.getText().toString();

            return new Product(title, desc, date);
        }
        return null;
    }

    private boolean validateForm() {
        if(TextUtils.isEmpty(titleFormProd.getText())) {
            titleFormProd.setError("Informe o nome do produto!");
            titleFormProd.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(descFormProd.getText())) {
            descFormProd.setError("Informe a descrição!");
            descFormProd.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(dateFormProd.getText())) {
            dateFormProd.setError("Informe a data!");
            dateFormProd.requestFocus();
            return false;
        }

        return true;
    }

}