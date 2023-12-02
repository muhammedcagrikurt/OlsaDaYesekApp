package com.kurt.olsadayesekapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.kurt.olsadayesekapp.R;
import com.kurt.olsadayesekapp.data.entity.SepetYemek;
import com.kurt.olsadayesekapp.data.entity.Yemek;
import com.kurt.olsadayesekapp.databinding.FragmentDetayBinding;
import com.kurt.olsadayesekapp.ui.viewmodel.DetayViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetayFragment extends Fragment {
;
    private FragmentDetayBinding binding;
    private DetayViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("sonuc","DetayFragment başarılı");
        binding = FragmentDetayBinding.inflate(inflater,container,false);


        DetayFragmentArgs bundle = DetayFragmentArgs.fromBundle(getArguments());
        Yemek gelenyemek = bundle.getYemek();
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+gelenyemek.getYemek_resim_adi();
        Glide.with(this).load(url).override(300,300).into(binding.imageViewYemekDetay);
        binding.textViewYemekAdiDetay.setText(gelenyemek.getYemek_adi());
        binding.textViewYemekFiyatDetay.setText(gelenyemek.getYemek_fiyat());
        binding.textFieldAdet.getEditText().setText("1");

        binding.buttonAdetArtir.setOnClickListener(view -> {
            binding.textFieldAdet.setError(null);
            //binding.editTextAdet.setText(""+(Integer.parseInt(binding.editTextAdet.getText().toString())+1));

            binding.textFieldAdet.getEditText().setText(""+(Integer.parseInt(binding.textFieldAdet.getEditText().getText().toString())+1));

        });
        binding.buttonAdetAzalt.setOnClickListener(view -> {
            int adet =Integer.parseInt(binding.textFieldAdet.getEditText().getText().toString());
            if(adet>0){ // 1 de yapılabilinir

                binding.textFieldAdet.getEditText().setText(""+(adet-1));
            }
            else{
                binding.textFieldAdet.setError("Adet eksi olamaz");
            }


        });

        binding.buttonSepeteEkle.setOnClickListener(view -> {
            int yemek_siperis_adet = Integer.parseInt(binding.textFieldAdet.getEditText().getText().toString());
            String kullacini_adi = "muhammedcagrikurt";
            viewModel.sepetYemekEkle(gelenyemek.getYemek_adi(),gelenyemek.getYemek_resim_adi(),Integer.parseInt(gelenyemek.getYemek_fiyat()),yemek_siperis_adet,kullacini_adi);
            Snackbar.make(view,"Sepete Eklendi",Snackbar.LENGTH_SHORT).show();

        });















        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetayViewModel.class);
        Log.e("sonuc","AnasayfaFragment başarılı");
    }
}