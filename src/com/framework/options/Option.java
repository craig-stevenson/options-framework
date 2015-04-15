package com.framework.options;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public interface Option {
	
	public String getUnderlyingSymbol();
	public int getUnderlyingPrice();
	public String getQuoteDate();
	public String getExpirationDate();
	public int getStrikePrice();
	public int getDelta();
	public int getBid();
	public int getAsk();
	public boolean isInTheMoney();
	public boolean isOutOfTheMoney();
	public int GetIntrinsicValue();
	public int GetExtrinsicValue();
	public int getPrice();
	public String getKey();
	public interface Builder{
		Builder setUnderlyingSymbol(String underlyingSymbol);
		Builder setUnderlyingPrice(int underlyingPrice);
		Builder setQuoteDate(String quoteDate);
		Builder setDateOfExpiration(String dateOfExpiration);
		Builder setStrikePrice(int strikePrice);
		Builder setDelta(int delta);
		Builder setBid(int bid);
		Builder setAsk(int ask);
	}
}
