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

    }
    public void getQuotesListFromNetwork(){
        if (!isAllQuotesLoaded || this.quotes.size()==0) {
            networkService.getQuotesList(10, offset,listMutableLiveData);
        } else listMutableLiveData.postValue(this.quotes);

    }
    public List<Quote> getCashedQuotesList(){
        return this.quotes;
    }

    public MutableLiveData<List<Quote>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void onReachEndOfList() {
        if (!isAllQuotesLoaded) {
            this.offset += 10;
            getQuotesListFromNetwork();
        }
    }
}
