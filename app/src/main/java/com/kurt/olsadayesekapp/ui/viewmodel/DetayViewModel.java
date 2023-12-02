package com.kurt.olsadayesekapp.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.kurt.olsadayesekapp.data.repo.UygulamaDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetayViewModel extends ViewModel {
    public UygulamaDaoRepository uygulamaDaoRepository;

    @Inject
    public DetayViewModel(UygulamaDaoRepository uygulamaDaoRepository){
        this.uygulamaDaoRepository=uygulamaDaoRepository;
    }

    public void sepetYemekEkle(String yemek_adi,String yemek_resim_adi, int yemek_fiyat, int yemek_siperis_adet, String kullanici_adi){
        uygulamaDaoRepository.sepetYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siperis_adet,kullanici_adi);
    }

}
