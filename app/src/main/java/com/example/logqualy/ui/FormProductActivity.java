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

import static com.example.logqualy.ui.Constantes.EXTRA_PRODUCT_EDIT;
import static com.example.logqualy.ui.Constantes.PRODUCT_EDIT;
import static com.example.logqualy.ui.Constantes.PRODUCT_SAVE;
import static com.example.logqualy.ui.Constantes.REQUEST_EDIT_PRODUCT;

public class FormProductActivity extends AppCompatActivity {

    private EditText titleFormProd;
    private EditText descFormProd;
    private EditText dateFormProd;
    private Button saveFormProd;
    private Product product;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product);

        carregarCampos();
        salvarForm();
        intent = getIntent();

        if (intent.hasExtra(EXTRA_PRODUCT_EDIT)) {
            getSupportActionBar().setTitle("Editar Sinistros");
            product = (Product) intent.getSerializableExtra(EXTRA_PRODUCT_EDIT);
            loadForm();
        }
    }

    private void loadForm() {
        titleFormProd.setText(product.getTitulo());
        descFormProd.setText(product.getDescricao());
        dateFormProd.setText(product.getData());
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
                if (intent.hasExtra(EXTRA_PRODUCT_EDIT)) {
                    updateProductFromForm();
                    goToProductActivity(PRODUCT_EDIT);
                } else {
                    getDados();
                    goToProductActivity(PRODUCT_SAVE);
                }
            }
        });
    }

    private void goToProductActivity(String saveOrEditExtra) {
        Intent intent = new Intent(FormProductActivity.this, ProductListActivity.class);
        intent.putExtra(saveOrEditExtra, product);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void updateProductFromForm() {
        String title = titleFormProd.getText().toString();
        String desc = descFormProd.getText().toString();
        String date = dateFormProd.getText().toString();

        product.setTitulo(title);
        product.setDescricao(desc);
        product.setData(date);
    }

    private void getDados() {
        if (validateForm()) {
            String title = titleFormProd.getText().toString();
            String desc = descFormProd.getText().toString();
            String date = dateFormProd.getText().toString();

            product = new Product(title, desc, date);
        }
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