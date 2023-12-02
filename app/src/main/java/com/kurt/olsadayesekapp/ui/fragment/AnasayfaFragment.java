package com.kurt.olsadayesekapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kurt.olsadayesekapp.R;
import com.kurt.olsadayesekapp.databinding.FragmentAnasayfaBinding;
import com.kurt.olsadayesekapp.ui.adapter.YemekAdapter;
import com.kurt.olsadayesekapp.ui.viewmodel.AnasayfaViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment {

    private FragmentAnasayfaBinding binding;

    private AnasayfaViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("sonuc","AnasayfaFragment başarılı");
        binding = FragmentAnasayfaBinding.inflate(inflater,container,false);

        binding.yemekRV.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.yemekListesi.observe(getViewLifecycleOwner(),yemekListesi ->{
            Log.e("sonuc","viewModel yemekListesi başarılı");
            YemekAdapter yemekAdapter = new YemekAdapter(yemekListesi,requireContext(),viewModel);
            binding.yemekRV.setAdapter(yemekAdapter);
        });

        binding.buttonSepet.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.anasayfaSepetGecis);
        });



















        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
        Log.e("sonuc","AnasayfaFragment onCreate başarılı");
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.yemekGetir();
    }
}