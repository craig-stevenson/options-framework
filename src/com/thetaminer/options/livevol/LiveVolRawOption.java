package com.thetaminer.options.livevol;

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
	public float implied_underlying_price_1545;
	public float active_underlying_price_1545;
	public double implied_volatility_1545;
	public float delta_1545;
	public float gamma_1545;
	public float theta_1545;
	public float vega_1545;
	public float rho_1545;
	public int bid_size_eod;
	public float bid_eod;
	public int ask_size_eod;
	public float ask_eod;
	public int underlying_bid_eod;
	public int underlying_ask_eod;
	public float vwap;
	public int open_interest;
	
	public static LiveVolRawOption CreateFrom(String row){
		try{
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
			o.bid_size_1545=values[11];
			o.bid_1545= Float.valueOf(values[12]);//
			o.ask_size_1545=values[13];
			o.ask_1545= Float.valueOf(values[14]);//
			o.underlying_bid_1545=Float.valueOf(values[15]);
			o.underlying_ask_1545=Float.valueOf(values[16]);
			o.implied_underlying_price_1545=Float.valueOf(values[17]);
	        o.active_underlying_price_1545=Float.valueOf(values[18]);//
			o.implied_volatility_1545= Float.valueOf(values[19]);
			o.delta_1545=Float.valueOf(values[20]);
			o.gamma_1545=Float.valueOf(values[21]);
			o.theta_1545=Float.valueOf(values[22]);
			o.vega_1545=Float.valueOf(values[23]);
			o.rho_1545=Float.valueOf(values[24]);
			//bid_size_eod=values[25];
			//bid_eod=values[26];
			//ask_size_eod=values[27];
			//ask_eod=values[28];
			//underlying_bid_eod=values[29];
			//underlying_ask_eod=values[30];
			//vwap=values[31];
			//open_interest=values[32];
			return o;
		}catch(Exception e){
			return null;
		}
		
		
		
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
