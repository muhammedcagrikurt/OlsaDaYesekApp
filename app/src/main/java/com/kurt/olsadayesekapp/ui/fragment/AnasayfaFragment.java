package com.kurt.olsadayesekapp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.kurt.olsadayesekapp.MainActivity;
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

        binding.yemekRV.setLayoutManager(new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false));
        viewModel.yemekListesi.observe(getViewLifecycleOwner(),yemekListesi ->{
            Log.e("sonuc","viewModel yemekListesi başarılı");
            YemekAdapter yemekAdapter = new YemekAdapter(yemekListesi,requireContext(),viewModel);
            binding.yemekRV.setAdapter(yemekAdapter);

            binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    viewModel.yemekAraGetir(s);
                    yemekAdapter.notifyDataSetChanged();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    viewModel.yemekAraGetir(s);
                    yemekAdapter.notifyDataSetChanged();
                    return true;
                }

            });
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
