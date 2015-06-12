/**
 * 
 */
package com.framework.options.containers.putquoteseries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import com.framework.options.Put;

/**
 * @author craigstevenson
 *
 */
public class MutablePutQuoteSeries implements PutQuoteSeries {

	private final Integer strike;
	private final String expirationDate;
	private final TreeMap<String,Put> puts;
	public void addPut(Put p) {
		if(!getStrikePrice().equals(p.getStrikePrice())) return;
		if(!getExpirationDate().equals(p.getExpirationDate())) return;
		
		puts.put(p.getQuoteDate(), p);
	}
	
	public MutablePutQuoteSeries(String expirationDate, Integer strike){
		this.strike=strike;
		this.expirationDate=expirationDate;
		puts = new TreeMap<String,Put>();
	}
	
	public MutablePutQuoteSeries(Put p) {
		strike = p.getStrikePrice();
		expirationDate = p.getExpirationDate();
		puts = new TreeMap<String,Put>();
		puts.put(expirationDate, p);
	}
	
	/* (non-Javadoc)
	 * @see com.framework.options.containers.OptionQuoteSeries#getStrikePrice()
	 */
	@Override
	public Integer getStrikePrice() {
		// TODO Auto-generated method stub
		return strike;
	}

	/* (non-Javadoc)
	 * @see com.framework.options.containers.OptionQuoteSeries#getExpirationDate()
	 */
	@Override
	public String getExpirationDate() {
		// TODO Auto-generated method stub
		return expirationDate;
	}

	/* (non-Javadoc)
	 * @see com.framework.options.containers.OptionQuoteSeries#getQuoteDates()
	 */
	@Override
	public Collection<String> getQuoteDates() {
		// TODO Auto-generated method stub
		return new ArrayList<String>(puts.keySet());
	}

	/* (non-Javadoc)
	 * @see com.framework.options.containers.putquoteseries.PutQuoteSeries#getPuts()
	 */
	@Override
	public ArrayList<Put> getPuts() {	
		return new ArrayList<Put>(puts.values());
	}

}
