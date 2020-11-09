package com.example.logqualy.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.logqualy.R;
import com.example.logqualy.model.Product;
import com.example.logqualy.ui.recyclerview.adapter.ProductAdapter;
import com.example.logqualy.ui.recyclerview.listener.ProductItemClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.logqualy.ui.Constantes.EXTRA_PRODUCT_EDIT;
import static com.example.logqualy.ui.Constantes.PRODUCT_EDIT;
import static com.example.logqualy.ui.Constantes.PRODUCT_SAVE;
import static com.example.logqualy.ui.Constantes.REQUEST_CODE;
import static com.example.logqualy.ui.Constantes.REQUEST_EDIT_PRODUCT;

public class ProductListActivity extends AppCompatActivity {

    private static final String PRODUCTS_COLLECTION = "products";
    private static final String TAG = "Adding Document";

    private FloatingActionButton addProdForm;
    private FirebaseFirestore db;
    private RecyclerView prodListRecycler;
    private ProductAdapter adapter;
    private int posicaoItemClick;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }


        db = FirebaseFirestore.getInstance();
        productList = new ArrayList<>();

        loadData();
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
        if (requestCode == REQUEST_CODE && data.hasExtra(PRODUCT_SAVE)) {
            if (resultCode == RESULT_OK) {
                Product product = (Product) data.getSerializableExtra(PRODUCT_SAVE);

                db.collection(PRODUCTS_COLLECTION).add(product);
                loadData();
            }
        }
        if (requestCode == REQUEST_EDIT_PRODUCT && data.hasExtra(PRODUCT_EDIT)) {
            if (resultCode == RESULT_OK) {
                Product product = (Product)data.getSerializableExtra(PRODUCT_EDIT);
                db.collection(PRODUCTS_COLLECTION).document(product.getId()).set(product);
                loadData();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//   Com if/else
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
                    startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    private void configuraRecycler() {
        prodListRecycler = findViewById(R.id.productListRecycler);
        prodListRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(getApplicationContext(), productList);
        prodListRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductItemClickListener() {
            @Override
            public void itemClick(Product product) {
                Intent intent = new Intent(ProductListActivity.this, FormProductActivity.class);
                intent.putExtra(EXTRA_PRODUCT_EDIT, product);
                startActivityForResult(intent, REQUEST_EDIT_PRODUCT);
            }
        });
    }

    void loadData() {
        db.collection(PRODUCTS_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            productList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = document.toObject(Product.class);
                                product.setId(document.getId());
                                productList.add(product);
                            }
                            configuraRecycler();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void geraUsuario(int quantidadeDeUsuario){
        for (int i = 1; i<= quantidadeDeUsuario; i++){
            productList.add(new Product(
                    "Arroz",
                    "Ruim",
                    "hoje"
            ));
        }
    }


}