package com.framework.options;

import java.time.LocalDate;

import com.google.gson.Gson;
import com.thetaminer.options.livevol.LiveVolRawOption;

public class Put implements Option {
	private String underlyingSymbol;
	private int underlyingPrice;
	private int strikePrice;
	private LocalDate quoteDate;
	private LocalDate dateOfExpiration;
	private int delta;
	private int bid;
	private int ask;
	
	private Put(){};
	
	public static Put CreateFrom(LiveVolRawOption o){
		Put p = new Put();
		if(!o.option_type.equals("p")) return null;
		
		p.underlyingSymbol = o.underlying_symbol;
		
		Float underlyingBid = new Float(o.underlying_bid_1545);
		Float underlyingAsk = new Float(o.underlying_ask_1545);
		Float underlyingPrice = new Float((underlyingBid+underlyingAsk)*50);
		p.underlyingPrice = underlyingPrice.intValue();
		
		p.strikePrice = o.strike;
		
		p.quoteDate = LocalDate.parse(o.quote_date);
		
		p.dateOfExpiration = LocalDate.parse(o.expiration);
		
		Float delta = o.delta_1545*100;
		p.delta = delta.intValue();
		
		Float bid = o.bid_1545*100;
		Float ask = o.ask_1545*100;
		
		return p;
		
	}
	private Put(Builder b){
		this.underlyingSymbol = b.underlyingSymbol;
		this.underlyingPrice = b.underlyingPrice;
		this.strikePrice = b.strikePrice;
		this.quoteDate = b.quoteDate;
		this.dateOfExpiration = b.dateOfExpiration;
		this.delta = b.delta;
		this.bid = b.bid;
		this.ask = b.ask;
	}
	
	public static class Builder implements Option.Builder{
		private String underlyingSymbol;
		private int underlyingPrice;
		private LocalDate quoteDate;
		private LocalDate dateOfExpiration;
		private int strikePrice;
		private int delta;
		private int bid;
		private int ask;
		
		@Override
		public Builder setUnderlyingSymbol(String underlyingSymbol){
			this.underlyingSymbol = underlyingSymbol;
			return this;
		}
		
		@Override
		public Builder setUnderlyingPrice(int underlyingPrice){
			this.underlyingPrice = underlyingPrice;
			return this;
		}
		
		@Override
		public Builder setStrikePrice(int strikePrice){
			this.strikePrice = strikePrice;
			return this;
		}
		
		@Override
		public Builder setQuoteDate(String quoteDate){
			this.quoteDate = LocalDate.parse(quoteDate);
			return this;
		}
		
		@Override
		public Builder setDateOfExpiration(String dateOfExpiration){
			this.dateOfExpiration = LocalDate.parse(dateOfExpiration);
			return this;
		}
		
		@Override
		public Builder setDelta(int delta){
			this.delta = delta;
			return this;
		}
		
		@Override
		public Builder setBid(int bid){
			this.bid = bid;
			return this;
		}
		
		@Override
		public Builder setAsk(int ask){
			this.ask = ask;
			return this;
		}
		
		public Put build(){
			return new Put(this);
		}
		
	}

	

	public String toJSON(){
		Gson g = new Gson();
		return g.toJson(this);
		
	}
	@Override
	public int getPrice() {
		int d = (bid+ask)/2;
		int intrinsic = (strikePrice-underlyingPrice)*100;
		if(intrinsic > d) return intrinsic;
		
		return d;
	}
	
	public String getKey() {
		StringBuilder key = new StringBuilder();
		key.append("<"+"p"+">")
		   .append("<"+quoteDate.toString()+">")
		   .append("<"+dateOfExpiration.toString()+">")
		   .append("<"+strikePrice+">");
		   
		return key.toString();
	}

	@Override
	public String getUnderlyingSymbol() {
		return underlyingSymbol;
	}

	@Override
	public int getUnderlyingPrice() {
		return underlyingPrice;
	}

	@Override
	public String getQuoteDate() {
		return quoteDate.toString();
	}

	@Override
	public String getExpirationDate() {
		return dateOfExpiration.toString();
	}

	@Override
	public int getDelta() {
		return delta;
	}

	@Override
	public int getBid() {
		return bid;
	}

	@Override
	public int getAsk() {
		// TODO Auto-generated method stub
		return ask;
	}
	@Override
	public int getStrikePrice() {
		// TODO Auto-generated method stub
		return strikePrice;
	}

	@Override
	public int GetIntrinsicValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetExtrinsicValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args){
		String input = "SPY,2005-01-10,SZC,2005-01-22,88.000,p,0.00,0.00,0.00,0.00,0,0,0.00,14185,0.05,118.84,118.85,118.86,118.85,0.6818,-0.0054,0.001093,-0.009711,0.003214,-0.020430,0,0.00,11010,0.05,118.95,118.97,0.00,0,0";
		LiveVolRawOption raw = LiveVolRawOption.CreateFrom(input);
		
		Put p = Put.CreateFrom(raw);
		System.out.println(p.toJSON());
	}

	@Override
	public boolean isInTheMoney() {
		if(strikePrice*100 > underlyingPrice) return true;
		
		return false;
	}
	@Override
	public boolean isOutOfTheMoney() {
		if(strikePrice*100 < underlyingPrice) return true;
		
		return  false;
	}


}
