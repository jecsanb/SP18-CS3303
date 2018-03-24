package com.example.jecsan.dowjonesindustrialaverage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;

/**
 * Created by jecsan on 3/23/18.
 */

/* This is a container class that holds ticker data for an nth size
list of valid tickers symbols. The container assumes the tickers are valid.
The container pulls the previous day ticker data from yahoo and gives the user
 the value of the ticker for that day for all the tickers in the form of an array
 of doubles.
 */
 public  class  TickerDataManager{
     private static String[] TICKERS;
     private double[] ticker_values;
     private String[] ticker_raw_data;

     private final String URL;

     TickerDataManager(String tickers){
         URL = "https://query1.finance.yahoo.com/v8/finance/chart/@?interval=2m";
         TICKERS = tickers.split(",");
         ticker_values = new double[TICKERS.length];
         ticker_raw_data = new String[TICKERS.length];

     }
     private void downloadTickerData(){
         StringBuilder fullUrl = new StringBuilder(URL);
         int indexOfTicker = fullUrl.indexOf("@");
         for( String ticker : TICKERS){
             fullUrl.replace(indexOfTicker,indexOfTicker+1,ticker.trim().toUpperCase());
             System.out.println(fullUrl.toString());
             try{
                 URL website = new URL(fullUrl.toString());
                 URLConnection urlConnection = website.openConnection();
                 InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                 String line = bufferedReader.readLine();
                 System.out.println(line);
             }
             catch (MalformedURLException malE){
                 malE.printStackTrace();
             }
             catch (IOException ioE){
                 ioE.printStackTrace();
             }
             fullUrl.setLength(0);
             fullUrl = new StringBuilder(URL);
         }

     }
     private double[] extractTickerValues(){
        //for each string of data downloaded
         //extract the needed data and then return the data.
         return ticker_values;
     }
     public int getProgressUpdate(){
         return 1;
     }
     public  static void main(String[] args){
        final String TICKERS ="AXP,AAPL, BA, CAT, CVX, CSCO, KO, DIS, DWDP, XOM, GE, GS, HD, IBM,INTC, JNJ,"+
                 "JPM, MCD, MRK, MMM, MSFT, NKE, PFE, PG, TRV, UTX, UNH, VZ, V, WMT";
         TickerDataManager manager = new TickerDataManager(TICKERS);
         manager.downloadTickerData();

     }


}
