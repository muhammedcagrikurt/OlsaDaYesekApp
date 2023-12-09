package com.kurt.olsadayesekapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kurt.olsadayesekapp.data.entity.SepetYemek;
import com.kurt.olsadayesekapp.databinding.CardSepetBinding;
import com.kurt.olsadayesekapp.ui.viewmodel.SepetViewModel;

import java.util.List;

public class SepetYemekAdapter extends RecyclerView.Adapter<SepetYemekAdapter.CardSepetHolder> {
    private List<SepetYemek> sepetYemekListesi;
    private Context context;
    private SepetViewModel viewModel;

    public SepetYemekAdapter(List<SepetYemek> sepetYemekListesi, Context context, SepetViewModel viewModel) {
        this.sepetYemekListesi = sepetYemekListesi;
        this.context = context;
        this.viewModel = viewModel;
    }

    public class CardSepetHolder extends RecyclerView.ViewHolder{
        private CardSepetBinding cardSepetBinding;

        public CardSepetHolder(CardSepetBinding cardSepetBinding) {
            super(cardSepetBinding.getRoot());
            this.cardSepetBinding=cardSepetBinding;
        }
    }

    @NonNull
    @Override
    public CardSepetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("sonuc","SepetYemekAdapter onCreateViewHolder başarılı");
        CardSepetBinding binding = CardSepetBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CardSepetHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardSepetHolder holder, int position) {
        Log.e("sonuc","SepetYemekAdapter onBindViewHolder başarılı");
        SepetYemek sepetYemek = sepetYemekListesi.get(position);

        CardSepetBinding cardSepetBinding = holder.cardSepetBinding;

        cardSepetBinding.textViewYemekAdiSepet.setText(sepetYemek.getYemek_adi());
        cardSepetBinding.textViewYemekFiyatSepet.setText(sepetYemek.getYemek_fiyat());
        cardSepetBinding.textViewYemekSiparisAdetSepet.setText(sepetYemek.getYemek_siparis_adet());


        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+sepetYemek.getYemek_resim_adi();
        Glide.with(context).load(url).override(300,300).into(cardSepetBinding.imageViewSepet);

        cardSepetBinding.buttonSil.setOnClickListener(view -> {
            deleteItem(position);
            viewModel.sepetYemekSil(Integer.parseInt(sepetYemek.getSepet_yemek_id()), sepetYemek.getKullanici_adi());


        });
    }
    // Bu kod, recyler viewdeki verileri silmek için adapter sınıfında tanımlanmış bir örnek silme metodudur.
    public void deleteItem(int position) {
        sepetYemekListesi.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return sepetYemekListesi == null ? 0 : sepetYemekListesi.size();
    }


}
