package com.koch.sampleproject.di;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koch.sampleproject.model.MovieApi;
import com.koch.sampleproject.model.TestApi;

import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }

    @Provides
    @Named("test_api")
    @Singleton
    Retrofit provideRetrofitTestApi(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://git.eclipse.org/r/")
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Named("tmdb")
    @Singleton
    Retrofit provideRetrofitTmdb(@Named("okhttp_tmdb") OkHttpClient okHttpClient) {
        ObjectMapper mapper = new ObjectMapper();
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .baseUrl("https://api.themoviedb.org/")
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(ArrayList<Interceptor> interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().followRedirects(false);
        interceptors.forEach(builder::addInterceptor);
        return builder.build();
    }

    @Provides
    @Named("okhttp_tmdb")
    @Singleton
    OkHttpClient provideOkHttpClientTmdb(ArrayList<Interceptor> interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().followRedirects(false);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                 //       .addHeader("Authorization", "0502e000edfc354b128cf682348660a3")
                //        .addHeader("Content-Type", "application/json;charset=utf-8")
                        .build();
                return chain.proceed(request);
            }
        });
        interceptors.forEach(builder::addInterceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    ArrayList<Interceptor> provideInterceptors() {
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        interceptors.add(loggingInterceptor);
        return interceptors;
    }

    @Provides
    @Singleton
    @NonNull
    TestApi provideTestApi(@Named("test_api") Retrofit retrofit) {
        return retrofit.create(TestApi.class);
    }

    @Provides
    @Singleton
    @NonNull
    MovieApi provideMovieApi(@Named("tmdb") Retrofit retrofit) {
        return retrofit.create(MovieApi.class);
    }
}
