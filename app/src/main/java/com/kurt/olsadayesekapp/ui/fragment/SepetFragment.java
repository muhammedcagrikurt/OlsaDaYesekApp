package com.kurt.olsadayesekapp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
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
import android.view.Window;

import com.kurt.olsadayesekapp.MainActivity;
import com.kurt.olsadayesekapp.R;
import com.kurt.olsadayesekapp.data.entity.SepetYemek;
import com.kurt.olsadayesekapp.databinding.FragmentSepetBinding;
import com.kurt.olsadayesekapp.ui.adapter.SepetYemekAdapter;
import com.kurt.olsadayesekapp.ui.adapter.YemekAdapter;
import com.kurt.olsadayesekapp.ui.viewmodel.SepetViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetFragment extends Fragment {

    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("sonuc","SepetFragment başarılı");

        binding=FragmentSepetBinding.inflate(inflater,container,false);
        binding.animationView3.setVisibility(View.GONE);
        binding.textView6.setVisibility(View.GONE);

        binding.recyclerViewSepet.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.sepetYemekListesi.observe(getViewLifecycleOwner(),sepetYemekListesi -> {
            SepetYemekAdapter sepetYemekAdapter = new SepetYemekAdapter(sepetYemekListesi,requireContext(),viewModel);

            binding.recyclerViewSepet.setAdapter(sepetYemekAdapter);
            binding.buttonAlisverisiTamamla.setText("Alışverişi Tamamla ("+sepetYemekListesi.size()+" ürün)");
            binding.buttonAlisverisiTamamla.setOnClickListener(view -> {
                for (SepetYemek sepetYemek:sepetYemekListesi) {
                    viewModel.sepetYemekSil(Integer.parseInt(sepetYemek.getSepet_yemek_id()), sepetYemek.getKullanici_adi());
                }
                sepetYemekListesi.clear();
                sepetYemekAdapter.notifyDataSetChanged();
                binding.buttonAlisverisiTamamla.setText("Sepet Boş");
                binding.animationView3.setVisibility(View.VISIBLE);
                binding.textView6.setVisibility(View.VISIBLE);

            });
            if (sepetYemekListesi.size()<1){
               binding.animationView.setVisibility(View.VISIBLE);

            }
            else {
                binding.animationView.setVisibility(View.GONE);
            }
        });

        binding.imageView.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.anasayfaFragment);
        });






        return binding.getRoot();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SepetViewModel.class);
        Log.e("sonuc","SepetFragment onCreate başarılı");
    }


}