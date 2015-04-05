package com.thetaminer.options;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class LiveVolRawOption {
	public String underlying_symbol;
	public String quote_date;
	public String root;
	public String expiration;
	public int strike;
	public String option_type;
	public double open;
	public double high;
	public double low;
	public double close;
	public double trade_volume;
	public String bid_size_1545;
	public float bid_1545;
	public String ask_size_1545;
	public float ask_1545;
	public float underlying_bid_1545;
	public float underlying_ask_1545;
	public String implied_underlying_price_1545;
	public String active_underlying_price_1545;
	public double implied_volatility_1545;
	public float delta_1545;
	public String gamma_1545;
	public String theta_1545;
	public String vega_1545;
	public String rho_1545;
	public String bid_size_eod;
	public String bid_eod;
	public String ask_size_eod;
	public String ask_eod;
	public String underlying_bid_eod;
	public String underlying_ask_eod;
	public String vwap;
	public String open_interest;
	
	public static LiveVolRawOption CreateFrom(String row){
		LiveVolRawOption o = new LiveVolRawOption();
		String[] values = row.split(",");
		o.underlying_symbol=values[0];
		o.quote_date= values[1];//
		o.root=values[2];
		o.expiration=values[3];//
		o.strike= Double.valueOf(values[4]).intValue();
		o.option_type=values[5];
		o.open= Double.valueOf(values[6]);
		o.high= Double.valueOf(values[7]);
		o.low= Double.valueOf(values[8]);
		o.close= Double.valueOf(values[9]);
		o.trade_volume=Integer.valueOf(values[10]);
		//bid_size_1545=values[11];
		o.bid_1545= Float.valueOf(values[12]);//
		//ask_size_1545=values[13];
		o.ask_1545= Float.valueOf(values[14]);//
		o.underlying_bid_1545=Float.valueOf(values[15]);
		o.underlying_ask_1545=Float.valueOf(values[16]);
		//implied_underlying_price_1545=values[17];
        //active_underlying_price_1545=values[18];//
		o.	implied_volatility_1545= Double.valueOf(values[19]);
		o.	delta_1545=Float.valueOf(values[20]);
		//gamma_1545=values[21];
		//theta_1545=values[22];
		//vega_1545=values[23];
		//rho_1545=values[24];
		//bid_size_eod=values[25];
		//bid_eod=values[26];
		//ask_size_eod=values[27];
		//ask_eod=values[28];
		//underlying_bid_eod=values[29];
		//underlying_ask_eod=values[30];
		//vwap=values[31];
		//open_interest=values[32];
		
		return o;
	}
	
	public String toJSON(){
		Gson g = new Gson();
		return g.toJson(this);
		
	}
	
	public String toString(){
		Gson g = new Gson();
		return g.toJsonTree(this).toString();
	}
	
	public static void main(String[] args) throws IOException{
		
	}
}
