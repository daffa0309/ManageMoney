package com.example.manageyourmoney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class ShowTransaksi extends DialogFragment {
    private String keterangan;
    private String amount;

    public ShowTransaksi() {

    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        keterangan = getArguments().getString("keterangan");
        amount = getArguments().getString("amount");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_transaksi, container);
        TextView show_keterangan = (TextView) view.findViewById(R.id.show_keterangan);
        TextView show_amount = (TextView) view.findViewById(R.id.show_amount);

        show_keterangan.setText(keterangan);
        show_amount.setText(amount);

        getDialog().setTitle("Lihat Transaksi");
        return view;
    }
}
