package kh.edu.rupp.ite.onlineshop.ui.fragment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.databinding.ActivityMainBinding;
import kh.edu.rupp.ite.onlineshop.ui.fragment.HomeFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.MoreFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProductFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // button item action to fragment
        binding.buttonNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.itm_home){
                showFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.itm_product) {
                showFragment(new ProductFragment());
            } else if (item.getItemId() == R.id.itm_profile) {
                showFragment(new ProfileFragment());
            } else {
                showFragment(new MoreFragment());
            }
            return true;
        });
    }


    // Function Show Fragment
    private void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containView, fragment);
        fragmentTransaction.commit();
    }

}