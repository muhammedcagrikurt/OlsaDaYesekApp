package com.kurt.olsadayesekapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kurt.olsadayesekapp.data.entity.Yemek;
import com.kurt.olsadayesekapp.databinding.CardYemekBinding;
import com.kurt.olsadayesekapp.ui.fragment.AnasayfaFragmentDirections;
import com.kurt.olsadayesekapp.ui.viewmodel.AnasayfaViewModel;

import java.util.List;

public class YemekAdapter extends RecyclerView.Adapter<YemekAdapter.CardYemekHolder> {
    private List<Yemek> yemekListesi;
    private Context context;
    private AnasayfaViewModel viewModel;

    public YemekAdapter(List<Yemek> yemekListesi, Context context, AnasayfaViewModel viewModel) {
        this.yemekListesi = yemekListesi;
        this.context = context;
        this.viewModel = viewModel;
    }
    public class CardYemekHolder extends RecyclerView.ViewHolder{
        private CardYemekBinding cardYemekBinding;

        public CardYemekHolder(CardYemekBinding cardYemekBinding) {
            super(cardYemekBinding.getRoot());
            this.cardYemekBinding=cardYemekBinding;
        }
    }

    @NonNull
    @Override
    public CardYemekHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("sonuc","YemekAdapter onCreateViewHolder başarılı");
        CardYemekBinding binding =CardYemekBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CardYemekHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardYemekHolder holder, int position) {
        Log.e("sonuc","YemekAdapter onBindViewHolder başarılı");
        Yemek yemek = yemekListesi.get(position);
        CardYemekBinding cardYemekBinding = holder.cardYemekBinding;

        cardYemekBinding.textViewYemekAdi.setText(yemek.getYemek_adi());
        cardYemekBinding.textViewYemekFiyat.setText(yemek.getYemek_fiyat());
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+yemek.getYemek_resim_adi();
        Glide.with(context).load(url).override(500,500).into(cardYemekBinding.imageViewYemek);

        cardYemekBinding.yemekCardView.setOnClickListener(view -> {
            AnasayfaFragmentDirections.AnasayfaDetayGecis detayGecis = AnasayfaFragmentDirections.anasayfaDetayGecis(yemek);
            Navigation.findNavController(view).navigate(detayGecis);
        });
    }

    @Override
    public int getItemCount() {
        return yemekListesi == null ? 0 : yemekListesi.size();
    }


}
