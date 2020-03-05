package com.dmk78.testtaskmvvm.quotes_list;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dmk78.testtaskmvvm.R;
import com.dmk78.testtaskmvvm.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesHolder> {
    private List<Quote> quotes = new ArrayList<>();
    private OnReachOfEndListener onReachOfEndListener;
    private OnQuotesClickListener onQuotesClickListener;

    void setOnClickListener(OnQuotesClickListener onQuotesClickListener) {
        this.onQuotesClickListener = onQuotesClickListener;
    }

    void setOnReachOfEndListener(OnReachOfEndListener onReachOfEndListener) {
        this.onReachOfEndListener = onReachOfEndListener;
    }

    @NonNull
    @Override
    public QuotesHolder onCreateViewHolder(@NonNull final ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false);
        return new QuotesHolder(view);
    }

    void addData(List<Quote> quotes) {
        this.quotes.addAll(quotes);
        notifyDataSetChanged();
    }

    void initData(List<Quote> quotes){
        this.quotes = quotes;
    }




    @Override
    public void onBindViewHolder(@NonNull final QuotesHolder holder, int i) {
        if (i > quotes.size() - 2 && onReachOfEndListener != null) {
            onReachOfEndListener.onReachEnd();
        }
        final Quote quote = quotes.get(i);
        holder.textViewQuote.setText(quote.text);
        if(quote.createdBy==0){
            holder.textViewQuote.setGravity(Gravity.START);
        } else {
            holder.textViewQuote.setGravity(Gravity.END);
        }
        holder.view.setOnClickListener(v -> onQuotesClickListener.onQuoteClick(quote));

    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class QuotesHolder extends RecyclerView.ViewHolder {
        TextView textViewQuote;
        View view;

        public QuotesHolder(View itemView) {
            super(itemView);
            textViewQuote = itemView.findViewById(R.id.tvListQuoteName);
            this.view = itemView;
        }


    }

    public interface OnQuotesClickListener {
        void onQuoteClick(Quote quote);
    }

    public interface OnReachOfEndListener {
        void onReachEnd();
    }

}
