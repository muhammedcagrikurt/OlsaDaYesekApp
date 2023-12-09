package com.kurt.olsadayesekapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kurt.olsadayesekapp.R;
import com.kurt.olsadayesekapp.databinding.FragmentProfilBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfilFragment extends Fragment {

    private FragmentProfilBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProfilBinding.inflate(inflater,container,false);

















        return binding.getRoot();
    }
}