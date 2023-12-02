package com.kurt.olsadayesekapp.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kurt.olsadayesekapp.data.entity.SepetYemek;
import com.kurt.olsadayesekapp.data.repo.UygulamaDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepetViewModel extends ViewModel {
    public MutableLiveData<List<SepetYemek>> sepetYemekListesi;
    public UygulamaDaoRepository uygulamaDaoRepository;
    @Inject
    public SepetViewModel(UygulamaDaoRepository uygulamaDaoRepository){
        this.uygulamaDaoRepository=uygulamaDaoRepository;
        sepetYemekGetir("muhammedcagrikurt");
        sepetYemekListesi=uygulamaDaoRepository.sepetYemekListesi;
    }
    public void sepetYemekGetir(String kullanici_adi){
        uygulamaDaoRepository.sepetYemekGetir(kullanici_adi);
    }
    public void sepetYemekSil(int sepet_yemek_id, String kullanici_adi){
        uygulamaDaoRepository.sepetYemekSil(sepet_yemek_id,kullanici_adi);
    }
}
