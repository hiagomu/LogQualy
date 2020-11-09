package com.example.logqualy.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logqualy.R;
import com.example.logqualy.model.Product;
import com.example.logqualy.ui.ProductListActivity;
import com.example.logqualy.ui.recyclerview.listener.ProductItemClickListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import static com.example.logqualy.ui.Constantes.PRODUCTS_COLLECTION;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> produtos;
    private Context context;
    private ProductItemClickListener onItemClickListener;

    public ProductAdapter(Context context, List<Product> produtos) {
        this.produtos = produtos;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = produtos.get(position);
        holder.mergeViewData(product);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.itemClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void setOnItemClickListener(ProductItemClickListener productItemClickListener) {
        this.onItemClickListener = productItemClickListener;
    }

    public void removeProduct(int adapterPosition) {
        Product product = produtos.get(adapterPosition);
        FirebaseFirestore.getInstance()
                .collection(PRODUCTS_COLLECTION)
                .document(product.getId())
                .delete();
        produtos.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private TextView descricao;
        private TextView data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nameItemEditTxt);
            descricao = itemView.findViewById(R.id.descItemEditTxt);
            data = itemView.findViewById(R.id.dateItemEditTxt);
        }

        void mergeViewData(Product product) {
            nome.setText(product.getTitulo());
            descricao.setText(product.getDescricao());
            data.setText(product.getData());
        }
    }
}
