package com.example.logqualy.ui.recyclerview.helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logqualy.ui.recyclerview.adapter.ProductAdapter;

public class ProductItemTouchHelper extends ItemTouchHelper.Callback {

    private ProductAdapter adapter;

    public ProductItemTouchHelper(ProductAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int movimentSwipe = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int movimentDrag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(movimentDrag, movimentSwipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.removeProduct(viewHolder.getAdapterPosition());
    }
}
