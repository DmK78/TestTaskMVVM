package com.dmk78.testtaskmvvm.quotes_list;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dmk78.testtaskmvvm.R;
import com.dmk78.testtaskmvvm.model.Quote;
import com.dmk78.testtaskmvvm.quote_details.QuoteDetailsActivity;

import java.util.List;

public class QuotesListActivity extends AppCompatActivity {
    private QuotesAdapter adapter;
    private QuotesListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);
        viewModel = new ViewModelProvider(this).get(QuotesListViewModel.class);
        setupAdapter();
        viewModel.getListMutableLiveData().observe(this, new Observer<List<Quote>>() {
            @Override
            public void onChanged(List<Quote> quotes) {
                if (quotes.size() == 0) {
                    viewModel.setAllQuotesLoaded();
                } else adapter.addData(quotes);
            }
        });
    }

    private void setupAdapter() {
        adapter = new QuotesAdapter();
        RecyclerView recyclerQuotes = findViewById(R.id.recyclerQuotes);
        recyclerQuotes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerQuotes.setAdapter(adapter);
        adapter.initData(viewModel.getCashedQuotesList());
        adapter.setOnClickListener(quote -> startActivity(QuoteDetailsActivity.create(this, quote.id)));
        adapter.setOnReachOfEndListener(() -> {
            if (!viewModel.isAllQuotesLoaded()) {
                Toast.makeText(this, "Loading ...", Toast.LENGTH_SHORT).show();
                viewModel.onReachEndOfList();
            }
        });
    }
}
