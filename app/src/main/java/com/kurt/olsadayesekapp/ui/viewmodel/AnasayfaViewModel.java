package com.kurt.olsadayesekapp.ui.viewmodel;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kurt.olsadayesekapp.data.entity.SepetYemek;
import com.kurt.olsadayesekapp.data.entity.Yemek;
import com.kurt.olsadayesekapp.data.repo.UygulamaDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {
    public UygulamaDaoRepository uygulamaDaoRepository;
    public MutableLiveData<List<Yemek>> yemekListesi;


    @Inject
    public AnasayfaViewModel(UygulamaDaoRepository uygulamaDaoRepository){
        Log.e("sonuc","AnasayfaViewModel başarılı");
        this.uygulamaDaoRepository=uygulamaDaoRepository;
        yemekGetir();
        yemekListesi=uygulamaDaoRepository.yemekListesi;
    }

    public void yemekAraGetir(String arananYemekAdi){
        uygulamaDaoRepository.yemekAraGetir(arananYemekAdi);
    }

    public void yemekGetir(){
        uygulamaDaoRepository.yemekGetir();
    }

}
