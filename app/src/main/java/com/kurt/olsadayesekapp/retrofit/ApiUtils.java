package com.kurt.olsadayesekapp.retrofit;

public class ApiUtils {
    public static final String BASE_URL = "http://kasimadalan.pe.hu/";

    public static UygulamaDao getUygulamaDao(){
        return RetrofitClient.getClient(BASE_URL).create(UygulamaDao.class);
    }
}
