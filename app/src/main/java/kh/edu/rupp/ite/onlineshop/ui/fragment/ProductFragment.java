package kh.edu.rupp.ite.onlineshop.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.Product;
import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProductBinding;
import kh.edu.rupp.ite.onlineshop.ui.fragment.adapter.ProductAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFragment extends Fragment {
    private FragmentProductBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentProductBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadProduct();
    }

    private void loadProduct(){
        Retrofit http = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = http.create(ApiService.class);

        Call<List<Product>> task = apiService.loadProductList();
        task.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    showProduct(response.body());
                } else {
                    Toast.makeText(getContext(),"Load product list failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(),"Load product list failed!", Toast.LENGTH_LONG).show();
                Log.e("[ProductFragment]", "Load product failed: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
    private void showProduct(List<Product> productList){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewProduct.setLayoutManager(layoutManager);


        ProductAdapter adapter = new ProductAdapter();
        adapter.submitList(productList);
        binding.recyclerViewProduct.setAdapter(adapter);

    }
}
