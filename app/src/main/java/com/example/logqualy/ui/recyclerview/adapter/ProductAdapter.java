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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> prudutos;
    private Context context;
    private ProductItemClickListener onItemClickListener;

    public ProductAdapter(Context context, List<Product> prudutos) {
        this.prudutos = prudutos;
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
        Product product = prudutos.get(position);
        holder.vicula(product);
    }

    @Override
    public int getItemCount() {
        return prudutos.size();
    }

    public void setOnItemClickListener(ProductItemClickListener productItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//    public void removeProduct(int adapterPosition) {
//        Product product = productList.get(adapterPosition);
//        FirebaseFirestore.getInstance()
//                .collection(PRODUCTS_COLLECTION)
//                .document(product.getId())
//                .delete();
//        productList.remove(adapterPosition);
//        notifyItemRemoved(adapterPosition);
//    }

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

        public void vicula(Product product) {
            nome.setText(product.getTitulo());
            descricao.setText(product.getDescricao());
            data.setText(product.getData());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicao = getAdapterPosition();
                    Product product = prudutos.get(getAdapterPosition());
                    onItemClickListener.itemClick(product, posicao);
                }
            });
        }
    }
}
