package com.kurt.olsadayesekapp.retrofit;

import android.util.Log;

import com.kurt.olsadayesekapp.data.entity.CRUDCevap;
import com.kurt.olsadayesekapp.data.entity.SepetYemekler;
import com.kurt.olsadayesekapp.data.entity.Yemekler;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UygulamaDao {


    @GET("yemekler/tumYemekleriGetir.php")
    Call<Yemekler> yemekGetir();

    @POST("/yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<SepetYemekler> sepetYemekGetir(@Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepetYemekEkle(@Field("yemek_adi")String yemek_adi,@Field("yemek_resim_adi") String yemek_resim_adi,
                                   @Field("yemek_fiyat") int yemek_fiyat,@Field("yemek_siparis_adet") int yemek_siparis_adet,
                                   @Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepetYemekSil(@Field("sepet_yemek_id") int sepet_yemek_id,
                                  @Field("kullanici_adi") String kullanici_adi);




}
