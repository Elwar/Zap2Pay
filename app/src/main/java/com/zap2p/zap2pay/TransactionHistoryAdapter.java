package com.zap2p.zap2pay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder> {

    private ArrayList<HashMap<String, String>> transactions;
    private LayoutInflater inflater;

    public TransactionHistoryAdapter(Context context, ArrayList<HashMap<String, String>> transactions) {
        this.transactions = transactions;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.transaction_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String, String> transaction = transactions.get(position);
        String transactionText = transaction.get("date") + " " + transaction.get("time") + " " + transaction.get("amount") + " " + transaction.get("status");
        holder.transactionTextView.setText(transaction.get("date") + " - " + transaction.get("time") + " - " + transaction.get("amount") + " BTC\n" + transaction.get("company"));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView transactionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionTextView = itemView.findViewById(R.id.transaction_text);
        }
    }
}
