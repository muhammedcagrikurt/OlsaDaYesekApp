package com.kurt.olsadayesekapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.badge.BadgeDrawable;
import com.kurt.olsadayesekapp.data.entity.SepetYemek;
import com.kurt.olsadayesekapp.databinding.ActivityMainBinding;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("sonuc","MainActivity başarılı");
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navHostFragment.getNavController());
        navController=navHostFragment.getNavController();

        BadgeDrawable profilBadgeDrawable = binding.bottomNavigationView.getOrCreateBadge(R.id.profilFragment);
        BadgeDrawable sepetBadgeDrawable = binding.bottomNavigationView.getOrCreateBadge(R.id.sepetFragment);
        profilBadgeDrawable.setVisible(true);
        sepetBadgeDrawable.setVisible(true);



        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId()==R.id.anasayfaFragment){
                    binding.bottomNavigationView.setVisibility(View.VISIBLE);
                }
                else if(navDestination.getId()==R.id.sepetFragment){
                    binding.bottomNavigationView.setVisibility(View.GONE);
                    sepetBadgeDrawable.setVisible(false);
                }
                else if(navDestination.getId()==R.id.profilFragment){
                    binding.bottomNavigationView.setVisibility(View.GONE);
                    profilBadgeDrawable.setVisible(false);
                }
                else {
                    binding.bottomNavigationView.setVisibility(View.GONE);
                }
            }
        });

    }

}