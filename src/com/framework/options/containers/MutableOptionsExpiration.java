package com.framework.options.containers;

import java.util.ArrayList;
import java.util.TreeMap;

import com.framework.options.Put;
import com.framework.options.containers.putchain.MutablePutChain;
import com.framework.options.containers.putquoteseries.MutablePutQuoteSeries;

public class MutableOptionsExpiration {
	String expirationDate;
	//Map indexed by quote date
	TreeMap<String,MutablePutChain> chains = new TreeMap<String,MutablePutChain>();
	TreeMap<Integer,MutablePutQuoteSeries> strikes = new TreeMap<Integer,MutablePutQuoteSeries>();
	
	public MutableOptionsExpiration(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void addPut(Put p) {
		addToChains(p);
		addToStrikes(p);
	}

	private void addToStrikes(Put p) {
		if(!expirationDate.equals(p.getExpirationDate())) return;
		
		if(strikes.containsKey(p.getStrikePrice())){
			strikes.get(p.getStrikePrice()).addPut(p);
			return;
		}
		
		MutablePutQuoteSeries tmp = new MutablePutQuoteSeries(p.getExpirationDate(), p.getStrikePrice());
		tmp.addPut(p);
		strikes.put(tmp.getStrikePrice(), tmp);
	}

	private void addToChains(Put p) {
		if(!expirationDate.equals(p.getExpirationDate())) return;
		
		if(chains.containsKey(p.getQuoteDate())){
			chains.get(p.getQuoteDate()).addPut(p);
			return;
		}
		
		MutablePutChain tmp = new MutablePutChain(p.getQuoteDate(),p.getExpirationDate());
		tmp.addPut(p);
		chains.put(tmp.getQuoteDate(), tmp);
		
	}

	public ArrayList<String> getQuoteDates() {
		return new ArrayList<String>(chains.keySet());
	}

	public ArrayList<Put> getPuts(String quoteDate) {
		return chains.get(quoteDate).getPuts();
	}

	public ArrayList<Integer> getStrikePrices() {
		return new ArrayList<Integer>(strikes.keySet());
	}

	public ArrayList<Put> getPuts(Integer strike) {
		return strikes.get(strike).getPuts();
	}

}
