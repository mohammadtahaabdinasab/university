package ir.mta.myshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<ProductItem> items;

    public ProductAdapter(List<ProductItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageCover;
        private final TextView textTitle;
        private final TextView textSubtitle;
        private final SwitchCompat switchActive;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCover = itemView.findViewById(R.id.imageCover);
            textTitle = itemView.findViewById(R.id.textTitle);
            textSubtitle = itemView.findViewById(R.id.textSubtitle);
            switchActive = itemView.findViewById(R.id.switchActive);
        }

        void bind(ProductItem item) {
            imageCover.setImageResource(item.getImageResId());
            imageCover.setContentDescription(item.getTitle());
            textTitle.setText(item.getTitle());
            textSubtitle.setText(item.getSubtitle());
            switchActive.setOnCheckedChangeListener(null);
            switchActive.setChecked(item.isActive());
            switchActive.setOnCheckedChangeListener((buttonView, isChecked) -> item.setActive(isChecked));
        }
    }
}

