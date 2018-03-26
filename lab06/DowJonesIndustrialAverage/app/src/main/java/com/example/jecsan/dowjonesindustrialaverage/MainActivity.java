
package com.example.jecsan.dowjonesindustrialaverage;

/*
 * DJIA Calculator
 * Copyright (C) 2018 Jecsan Blanco <jblancolicano1@buffs.wtamu.edu>
 *
 * Distributed under terms of the MIT license.
 *
 *   @author Jecsan Blanco
 *   @version 1.0
 *   @since  03/25/2018
 *
 *   This program connects to the internet to download the previous day
 *   price of stocks belonging to the companies that make up the DJIA.
 */


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private Button submit_button;
    private ProgressBar pbar;
    private  String[] stock_symbols;
    private TextView result_text, time_elapsed_text;
    private double WSJ_MAGIC_CONST = 0.14523396877348;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stock_symbols = new String[]{"AXP", "AAPL", "BA", "CAT", "CVX", "CSCO", "KO", "DIS", "DWDP", "XOM", "GE", "GS", "HD", "IBM",
                "INTC", "JNJ", "JPM", "MCD", "MRK", "MMM", "MSFT", "NKE", "PFE", "PG", "TRV", "UTX", "UNH", "VZ",
                "V", "WMT"};

        pbar = findViewById(R.id.progressBar);
        pbar.setMax(stock_symbols.length-1);

        submit_button = findViewById(R.id.submit_button);
        result_text = findViewById(R.id.status_text);
        time_elapsed_text = findViewById(R.id.elapsed_time);



        //starts the background process
        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               //where the @ is a char that is to be replaced by each ticker.
                String webURL = "https://query1.finance.yahoo.com/v8/finance/chart/@?interval=2m";
                Log.i("UIThread", webURL);
                result_text.setText(R.string.djia_in_progress_str);
                new FindTickerPriceSum().execute(webURL);

            }
        });


    }

    //downlands stock prices, sums them and calculates the DJIA
    private  class FindTickerPriceSum extends AsyncTask<String, Integer, String> {
        long startTime,endTime;

        @Override
        protected String doInBackground(String... params) {
            String priceString;
            Double totalSum = 0.0;
            String webURL = params[0];
            String[] tickers  = stock_symbols;
            try {
                for(int i = 0; i < tickers.length; i++) {
                    Log.i("doInBackground", "Stock symbol is " + tickers[i]);
                    URL url = new URL(webURL.replace("@",tickers[i]));
                    BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(url.openStream()));
                    String line = bufferedReader.readLine();
                    String[] ar = line.split("\"previousClose\":");
                    String[] sr = ar[1].split(",");
                    priceString = sr[0];
                    Log.i("doInBackground", "Stock price " + priceString);
                    bufferedReader.close();
                    totalSum += Double.parseDouble(priceString);

                    super.publishProgress(i);
                }


            }
            catch (MalformedURLException mle){
                mle.printStackTrace();
            }
            catch (IOException ioe){
                ioe.printStackTrace();
            }
            return totalSum.toString();
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbar.setProgress(0);
           startTime = System.nanoTime();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            endTime = System.nanoTime();

            Double price_p = Double.parseDouble(s);
            result_text.setText("DJIA for the previous day " + String.format("$%,.2f",price_p/ WSJ_MAGIC_CONST));
            time_elapsed_text.setText("Time taken for the computation is " + String.format("%.2f",(double)(endTime - startTime)/1000000000.0) + " sec.");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbar.setProgress(values[0]);
        }
    }

}
