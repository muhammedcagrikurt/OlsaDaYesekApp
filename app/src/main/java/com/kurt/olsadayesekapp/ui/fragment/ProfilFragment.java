package com.kurt.olsadayesekapp.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kurt.olsadayesekapp.R;
import com.kurt.olsadayesekapp.databinding.FragmentProfilBinding;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfilFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FragmentProfilBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProfilBinding.inflate(inflater,container,false);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        final boolean[] createUser = {false};
        binding.textViewCreate.setOnClickListener(view -> {
            if (!createUser[0]){
                binding.buttonLoginOrCreate.setText("Hesap Oluştur");
                binding.textViewCreate.setText("Hesabın var mı? Giriş yap.");
                createUser[0] =!createUser[0];}
            else {
                binding.buttonLoginOrCreate.setText("Giriş Yap");
                binding.textViewCreate.setText("Hesabın yok mu?");
                createUser[0] =!createUser[0];
            }
        });

       /*
            binding.animationView2.setVisibility(View.VISIBLE);
            binding.animationView.setVisibility(View.GONE);
            binding.textFieldEmail.setVisibility(View.GONE);
            binding.textFieldSifre.setVisibility(View.GONE);
            binding.buttonLoginOrCreate.setVisibility(View.GONE);
            binding.textViewCreate.setText("Hoşgeldiniz");
        */

        binding.buttonLoginOrCreate.setOnClickListener(view -> {
            String email = binding.textFieldEmail.getEditText().getText().toString();
            String sifre = binding.textFieldSifre.getEditText().getText().toString();

            if(!createUser[0]){
            mAuth.signInWithEmailAndPassword(email,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Navigation.findNavController(view).navigate(R.id.anasayfaFragment);
                }

            });
            }

            if (createUser[0]){
                mAuth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Snackbar.make(view,"Hesabınız Oluşturuldu",Snackbar.LENGTH_SHORT);
                        }else {
                            Log.e("CreateUser",task.getException()+"");
                        }
                    }
                });
            }


        });














        return binding.getRoot();
    }
}