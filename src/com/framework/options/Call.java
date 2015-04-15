package com.framework.options;

import java.time.LocalDate;

import com.framework.options.Put.Builder;
import com.google.gson.Gson;
import com.thetaminer.options.livevol.LiveVolRawOption;


public class Call implements Option {
	private String underlyingSymbol;
	private int underlyingPrice;
	private int strikePrice;
	private LocalDate quoteDate;
	private LocalDate dateOfExpiration;
	private int impliedVolatility;
	private int delta;
	private int bid;
	private int ask;
	private Call(){};
	private Call(Builder b){
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
		private int strikePrice;
		private LocalDate quoteDate;
		private LocalDate dateOfExpiration;
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
		
		
		public Call build(){
			return new Call(this);
		}
		
	}
	@Override
	public int getPrice() {
		int d = (bid+ask)/2;
		int intrinsic = GetIntrinsicValue();
		if(intrinsic > d) return intrinsic;
		
		return d;
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
		return ask;
	}

	@Override
	public int GetIntrinsicValue() {
		return underlyingPrice-strikePrice*100;
	}

	@Override
	public int GetExtrinsicValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getStrikePrice() {
		return strikePrice;
	}
	public static Call CreateFrom(LiveVolRawOption o){
		Call c = new Call();
		if(!o.option_type.equals("c")) return null;
		
		c.underlyingSymbol = o.underlying_symbol;
		
		Float underlyingBid = new Float(o.underlying_bid_1545);
		Float underlyingAsk = new Float(o.underlying_ask_1545);
		Float underlyingPrice = new Float((underlyingBid+underlyingAsk)*50);
		c.underlyingPrice = underlyingPrice.intValue();
		
		c.strikePrice = o.strike;
		
		c.quoteDate = LocalDate.parse(o.quote_date);
		
		c.dateOfExpiration = LocalDate.parse(o.expiration);
		
		Float delta = o.delta_1545*100;
		c.delta = delta.intValue();
		
		Float bid = o.bid_1545*100;
		Float ask = o.ask_1545*100;
		c.bid = bid.intValue();
		c.ask = ask.intValue(); 
		
		return c;
		
	}
	public String toJSON() {
		Gson g = new Gson();
		return g.toJson(this);
	}
	@Override
	public boolean isInTheMoney() {
		if(strikePrice*100 < underlyingPrice) return true;
		
		return false;
	}
	@Override
	public boolean isOutOfTheMoney() {
		if(strikePrice*100 > underlyingPrice) return true;
		
		return  false;
	}
}
