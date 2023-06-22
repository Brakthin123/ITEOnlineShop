package kh.edu.rupp.ite.onlineshop.ui.fragment.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import kh.edu.rupp.ite.onlineshop.api.model.Product;
import kh.edu.rupp.ite.onlineshop.databinding.ViewHolderHomeBinding;
import kh.edu.rupp.ite.onlineshop.databinding.ViewHolderProductBinding;

public class HomeAdapter extends ListAdapter<Product, HomeAdapter.HomeViewHolder> {


    public HomeAdapter() {
        super(new DiffUtil.ItemCallback<Product>() {
            @Override
            public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return Objects.equals(oldItem.getId(), newItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderHomeBinding binding = ViewHolderHomeBinding.inflate(layoutInflater, parent,false);
        return new HomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Product item = getItem(position);
        holder.bind(item);
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        private final ViewHolderHomeBinding itemBinding;

        public HomeViewHolder(ViewHolderHomeBinding itemBinding ) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
        public void bind(Product product) {
            Picasso.get().load(product.getImageUrl()).into(itemBinding.imgHome);
            itemBinding.textHomePrice.setText(String.valueOf(product.getPrice()));
        }
    }
}
