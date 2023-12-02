package com.kurt.olsadayesekapp.di;

import com.kurt.olsadayesekapp.data.repo.UygulamaDaoRepository;
import com.kurt.olsadayesekapp.retrofit.ApiUtils;
import com.kurt.olsadayesekapp.retrofit.UygulamaDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public UygulamaDaoRepository provideUygulamaDaoRepository(UygulamaDao uygulamaDao){
        return new UygulamaDaoRepository(uygulamaDao);
    }

    @Provides
    @Singleton
    public UygulamaDao provideUygulamaDao(){
        return ApiUtils.getUygulamaDao();
    }
}
