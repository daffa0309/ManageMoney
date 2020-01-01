package com.example.manageyourmoney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class list extends Fragment {

    ListView lv;
    Transaksi transaksi;
    TransaksiAdapter tA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        transaksi = new Transaksi(getActivity());
        ArrayList<HashMap<String, String>> trxList = transaksi.getList();
        TransaksiAdapter adapter = new TransaksiAdapter(getActivity(), trxList);

        View rootView = inflater.inflate(R.layout.list_fragment, container, false);
        lv = (ListView) rootView.findViewById(R.id.list_transaksi);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView keterangan = (TextView) view.findViewById(R.id.title);
                TextView amount = (TextView) view.findViewById(R.id.amount);


                ShowTransaksi showTransaksi = new ShowTransaksi();
                Bundle args = new Bundle();
                args.putString("keterangan", keterangan.getText().toString());
                args.putString("amount", amount.getText().toString());
                showTransaksi.setArguments(args);
                showTransaksi.show(getFragmentManager(), keterangan.getText().toString());
            }
        });

        // BERHUBUNGAN DENGAN USER
        TextView uangDompetText = (TextView) rootView.findViewById(R.id.uang_dompet);
        SqliteHelper dataUser = new SqliteHelper(this.getActivity());
        JSONObject user = dataUser.getUser();
        try {
            Double balance = Double.parseDouble(user.getString("amount"));
            uangDompetText.setText("Rp` " + String.format("%,.2f", balance) + ",-");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_dompet, container, false);
    }
}
