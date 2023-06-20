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

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadProfile();
    }

    private void loadProfile(){
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        //create obj service
        ApiService apiService = httpClient.create(ApiService.class);

        //load data
        Call<Profile> task = apiService.loadProfileList();
        task.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    showProfile(response.body());
                }else{
                    Toast.makeText(getContext(),"Data Loading . . .",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getContext(),"Data Failed . . .",Toast.LENGTH_LONG).show();
            }
        });

    }
    private void showProfile(Profile profile) {

        String fullName = (profile.getFirst_name() + " "+ profile.getLast_name());

        Picasso.get().load(profile.getImage_url()).into(binding.imageViewProfile);
        binding.textViewName.setText(fullName);
        binding.textViewEmail.setText(profile.getEmail());

        binding.editTextEmail.setText(profile.getEmail());
        binding.editTextPhone.setText(profile.getPhone_number());
        binding.editTextGender.setText(profile.getGender());
        binding.editTextBirthday.setText(profile.getBirthday());
        binding.textViewAddress.setText(profile.getAddress());
    }

}
