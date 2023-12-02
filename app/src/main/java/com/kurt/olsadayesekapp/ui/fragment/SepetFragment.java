package com.kurt.olsadayesekapp.ui.fragment;

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

import com.kurt.olsadayesekapp.R;
import com.kurt.olsadayesekapp.databinding.FragmentSepetBinding;
import com.kurt.olsadayesekapp.ui.adapter.SepetYemekAdapter;
import com.kurt.olsadayesekapp.ui.adapter.YemekAdapter;
import com.kurt.olsadayesekapp.ui.viewmodel.SepetViewModel;

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

        if(viewModel.sepetYemekListesi==null){
            binding.textViewSepetBos.setVisibility(View.VISIBLE);
        }
        else{
            binding.textViewSepetBos.setVisibility(View.GONE);
        }
        binding.recyclerViewSepet.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.sepetYemekListesi.observe(getViewLifecycleOwner(),sepetYemekListesi -> {
            SepetYemekAdapter sepetYemekAdapter = new SepetYemekAdapter(sepetYemekListesi,requireContext(),viewModel);

            binding.recyclerViewSepet.setAdapter(sepetYemekAdapter);
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