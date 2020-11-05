package com.example.logqualy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class ProductListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private FloatingActionButton addProdForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }

        addNewProduct();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Com if/else
//        if(item.getItemId()==R.id.menu_item_logout){
//            logOut();
//            return true;
//        } else {
//            return super.onOptionsItemSelected(item);
//        }
        switch (item.getItemId()) {
            case R.id.menu_item_logout:
                logOut();
                goToLoginAct();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void goToLoginAct() {
        Intent intent = new Intent(ProductListActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void addNewProduct() {
        addProdForm = findViewById(R.id.productListAddFAB);

        addProdForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, FormProductActivity.class);
                startActivity(intent);
            }
        });
    }
}