package com.dmk78.testtaskmvvm.di;




import com.dmk78.testtaskmvvm.quote_details.QuoteDetailsViewModel;
import com.dmk78.testtaskmvvm.quotes_list.QuotesListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkServiceModule.class})
public interface AppComponent {
    void injectTo(QuotesListViewModel quotesListViewModel);
    void injectTo(QuoteDetailsViewModel quoteDetailsViewModel);





}
