package com.example.logqualy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logqualy.R;
import com.example.logqualy.model.Product;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> prudutos;

    public ProductAdapter(List<Product> prudutos) {
        this.prudutos = prudutos;
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
        //vicula
    }

    @Override
    public int getItemCount() {
        return prudutos.size();
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
    }
}
