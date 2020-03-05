package com.dmk78.testtaskmvvm.quote_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.dmk78.testtaskmvvm.R;
import com.dmk78.testtaskmvvm.databinding.ActivityQuoteDetailsBinding;
import com.dmk78.testtaskmvvm.model.Quote;
import com.dmk78.testtaskmvvm.quotes_list.QuotesListViewModel;


public class QuoteDetailsActivity extends AppCompatActivity {
    private static final String QUOTE_ID = "quote_id";
    private QuoteDetailsViewModel viewModel;
    private ActivityQuoteDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(QuoteDetailsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quote_details);
        binding.setViewmodel(viewModel);
        viewModel.getQuoteMutableLiveData().observe(this, new Observer<Quote>() {
            @Override
            public void onChanged(Quote quote) {
                renderView(quote);
            }
        });

        int id = getIntent().getIntExtra(QUOTE_ID, 0);
        viewModel.loadQuote(id);
    }

    public void renderView(Quote quote) {

        binding.tvQuoteDetCreated.setText(quote.getCreated());
        binding.tvQuoteDetText.setText(quote.text);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quote.tagList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1) {
                    view.setBackgroundColor(Color.parseColor("#FFB6B546"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#FFCCCB4C"));
                }
                return view;
            }
        };
        binding.listViewQuotes.setAdapter(arrayAdapter);
    }

    public static Intent create(Context context, int id) {
        Intent intent = new Intent(context, QuoteDetailsActivity.class);
        intent.putExtra(QUOTE_ID, id);
        return intent;
    }


}
