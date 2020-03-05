package com.dmk78.testtaskmvvm.di;




import com.dmk78.testtaskmvvm.network.NetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkServiceModule {
    @Singleton
    @Provides
    public NetworkService providesNetworkService() {
        return new NetworkService();
    }
}
