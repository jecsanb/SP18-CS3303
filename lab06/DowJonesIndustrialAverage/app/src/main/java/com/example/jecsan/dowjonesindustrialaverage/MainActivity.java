package com.example.jecsan.dowjonesindustrialaverage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static String TICKERS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TICKERS ="AXP,AAPL, BA, CAT, CVX, CSCO, KO, DIS, DWDP, XOM, GE, GS, HD, IBM,INTC, JNJ,"+
                "JPM, MCD, MRK, MMM, MSFT, NKE, PFE, PG, TRV, UTX, UNH, VZ, V, WMT";
        TickerDataManager manager = new TickerDataManager(TICKERS);
    }
}
