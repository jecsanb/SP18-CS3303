//package com.example.jecsan.dowjonesindustrialaverage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by jecsan on 3/23/18.
 */

    // 1. get tickers either via a string or array.
    // 2. download raw ticker data for a given ticker or multiple
    // 3. Extracts   values from one or more tickers (jason)
    // 4.Returns the values for the given ticker or a collection of them


 public class  TickerDataManager{

     private final String yahoofUrl;
     private final String[] tickers;
     private HashMap<String,String> rawTickerData;
     private HashMap<String,String> tickerDataValues;


    /**
     * Init the manager with an array of tickers to process
     * @param tickers
     */
     TickerDataManager(String[] tickers) {
         rawTickerData = new HashMap<>(0);
         yahoofUrl = "https://query1.finance.yahoo.com/v8/finance/chart/@?interval=2m";

         this.tickers = tickers;
         for(String ticker : tickers){
             rawTickerData.put(ticker.trim(),"DNE");
         }
     }

    /**
     * Returns a string of the tickers provided.
     * @return
     */
     public String getTickers(){
         return Arrays.toString(tickers);
     }

    /**
     *Populates a hashmap with all the tickers and the data downloaded for each ticker.
     * @param tickers
     * @return a hashMap of the tickers and the data
     */
     public HashMap<String,String> getTickersAndData(String[] tickers){
         HashMap<String,String> answer = new HashMap<>();

         if(!rawTickerData.isEmpty() && tickers.length > 0){
             String trimendTkr, data;
             for( String ticker : tickers){
                 trimendTkr = ticker.trim();
                 data = rawTickerData.get(trimendTkr);

                 if(data == null)
                     throw new IllegalArgumentException("Ticker " + ticker  + "does not exist in data.");

                 answer.put(trimendTkr,data);
             }

         }
        return answer;
     }
     public String getTickerData(String ticker){
         HashMap<String,String> answer = getTickersAndData(new String[]{ticker});
         return  answer.get(ticker);
     }
     private void DLTickerData(){
         if(rawTickerData.isEmpty()){
            throw new IllegalStateException("No tickers to download or there is an error with the tickers") ;
         }else{

         }

     }

    /**
     * Downloads the data of the provided ticker from the URL.
     * @param ticker
     */
     public void DLTickerData(String ticker){
        if(rawTickerData.isEmpty()) { //there are no keys
            throw new IllegalStateException("Error with ticker list! Has it been init?");
        }
        StringBuilder urlQuery = new StringBuilder(yahoofUrl);
        int indexOfDelim = urlQuery.indexOf("@");
        urlQuery.replace(indexOfDelim,indexOfDelim +1,ticker);

         try {

             URL website = new URL(urlQuery.toString());
             URLConnection urlConnection = website.openConnection();
             InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             rawTickerData.put(ticker,bufferedReader.readLine());

         }catch (MalformedURLException mle){
             mle.printStackTrace();
         }
         catch (IOException ioe){
             ioe.printStackTrace();
         }

     }

     public static void main(String[] args){
         String[] ticker = {"MS"};
         TickerDataManager manager = new TickerDataManager(ticker);

         System.out.println("Tickers:" + manager.getTickers());
         System.out.println("Before dl");
         System.out.println( manager.getTickerData(ticker[0]));
         manager.DLTickerData(ticker[0]);

         System.out.println("After dl");
         System.out.println( manager.getTickerData(ticker[0]));

     }

}
