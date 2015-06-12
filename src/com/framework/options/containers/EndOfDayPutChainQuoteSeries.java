package com.framework.options.containers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.framework.options.containers.putchain.PutChain;

public interface EndOfDayPutChainQuoteSeries {
	
	public static interface builder{
		public void setExpirationDate(String expirationDate);
		public void setQuoteDate(String quoteDate);
		public void setPutChain(PutChain chain);
		public List<String> getQuoteDates();
		public PutChain getPutChain(String quoteDate);
	}
	
}
