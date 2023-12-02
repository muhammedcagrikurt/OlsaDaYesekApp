package com.kurt.olsadayesekapp.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kurt.olsadayesekapp.data.entity.CRUDCevap;
import com.kurt.olsadayesekapp.data.entity.SepetYemek;
import com.kurt.olsadayesekapp.data.entity.SepetYemekler;
import com.kurt.olsadayesekapp.data.entity.Yemek;
import com.kurt.olsadayesekapp.data.entity.Yemekler;
import com.kurt.olsadayesekapp.retrofit.UygulamaDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UygulamaDaoRepository {
    public MutableLiveData<List<Yemek>> yemekListesi = new MutableLiveData<>();
    public MutableLiveData<List<SepetYemek>> sepetYemekListesi = new MutableLiveData<>();
    private UygulamaDao uygulamaDao;

    public UygulamaDaoRepository(UygulamaDao uygulamaDao){
        this.uygulamaDao=uygulamaDao;
    }

    public void yemekGetir(){
        uygulamaDao.yemekGetir().enqueue(new Callback<Yemekler>() {
            @Override
            public void onResponse(Call<Yemekler> call, Response<Yemekler> response) {
                Log.e("Dao","yemekGetir başarılı");
                List<Yemek> gelenYemek = response.body().getYemekList();
                yemekListesi.setValue(gelenYemek);
            }

            @Override
            public void onFailure(Call<Yemekler> call, Throwable t) {
                Log.e("Dao","yemekGetir başarısız");
            }
        });
    }

    public void sepetYemekEkle(String yemek_adi,String yemek_resim_adi, int yemek_fiyat, int yemek_siperis_adet, String kullanici_adi){
        uygulamaDao.sepetYemekEkle(yemek_adi,yemek_resim_adi,
                yemek_fiyat,yemek_siperis_adet,kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                Log.e("Dao","sepetYemekEkle başarılı");
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {
                Log.e("Dao","sepetYemekEkle başarısız");
            }
        });
    }
    public void sepetYemekSil(int sepet_yemek_id, String kullanici_adi){
        uygulamaDao.sepetYemekSil(sepet_yemek_id,kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                sepetYemekGetir(kullanici_adi);
                Log.e("Dao","sepetYemekSil başarılı");
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {
                Log.e("Dao","sepetYemekSil başarısız");
            }
        });
    }
    public void sepetYemekGetir(String kullanici_adi){
        uygulamaDao.sepetYemekGetir(kullanici_adi).enqueue(new Callback<SepetYemekler>() {
            @Override
            public void onResponse(Call<SepetYemekler> call, Response<SepetYemekler> response) {
                List<SepetYemek> gelenSepetYemek = response.body().getSepetYemekList();
                sepetYemekListesi.setValue(gelenSepetYemek);
                Log.e("Dao","sepetYemekGetir başarılı");
            }

            @Override
            public void onFailure(Call<SepetYemekler> call, Throwable t) {
                Log.e("Dao","sepetYemekGetir başarısız");
                Log.e("Dao",t.getMessage());
                Log.e("Dao",t.getLocalizedMessage());

            }
        });
    }

}
