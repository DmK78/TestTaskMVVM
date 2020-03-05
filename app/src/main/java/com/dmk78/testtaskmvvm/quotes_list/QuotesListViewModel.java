package com.dmk78.testtaskmvvm.quotes_list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dmk78.testtaskmvvm.App;
import com.dmk78.testtaskmvvm.model.Quote;
import com.dmk78.testtaskmvvm.network.NetworkService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class QuotesListViewModel extends AndroidViewModel {
    private MutableLiveData<List<Quote>> listMutableLiveData = new MutableLiveData<>();
    @Inject
    NetworkService networkService;
    private boolean isAllQuotesLoaded = false;
    private int offset = 1;
    private ArrayList<Quote> quotes = new ArrayList<>();

    public QuotesListViewModel(@NonNull Application application) {
        super(application);
        App.getComponent().injectTo(this);
        networkService.getQuotesList(10,offset,listMutableLiveData);
    }

    private void getQuotesListFromNetwork(){
            networkService.getQuotesList(10, offset,listMutableLiveData);
    }
    List<Quote> getCashedQuotesList(){
        return this.quotes;
    }

    MutableLiveData<List<Quote>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    void onReachEndOfList() {
        if (!isAllQuotesLoaded) {
            this.offset += 10;
            getQuotesListFromNetwork();
        }
    }

    boolean isAllQuotesLoaded() {
        return isAllQuotesLoaded;
    }

    void setAllQuotesLoaded() {
        isAllQuotesLoaded = true;
    }
}
