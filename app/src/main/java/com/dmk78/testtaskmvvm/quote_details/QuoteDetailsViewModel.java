package com.dmk78.testtaskmvvm.quote_details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dmk78.testtaskmvvm.App;
import com.dmk78.testtaskmvvm.model.Quote;
import com.dmk78.testtaskmvvm.network.NetworkService;

import javax.inject.Inject;

public class QuoteDetailsViewModel extends AndroidViewModel {
    @Inject
    NetworkService networkService;
    private MutableLiveData<Quote> quoteMutableLiveData = new MutableLiveData<>();
    public QuoteDetailsViewModel(@NonNull Application application) {
        super(application);
        App.getComponent().injectTo(this);
    }

    void loadQuote(int id) {
        networkService.getQuote(id,quoteMutableLiveData);
    }

    MutableLiveData<Quote> getQuoteMutableLiveData() {
        return quoteMutableLiveData;
    }
}
