package com.framework.options.containers.putchain;

import java.util.ArrayList;
import java.util.TreeMap;

import com.framework.options.Put;
import com.google.gson.Gson;
/**
 * This is the default implementation of a PutChain
 * @author craigstevenson
 *
 */
public class MutablePutChain implements PutChain {
	private String quoteDate;
	private String expirationDate;
	TreeMap<Integer,Put> theChain;
	
	public MutablePutChain(Put p){
		this(p.getQuoteDate(), p.getExpirationDate());
		addPut(p);
	}
	
	public MutablePutChain(String quoteDate, String expirationDate){
		this.quoteDate = quoteDate;
		this.expirationDate=expirationDate;
		theChain = new TreeMap<Integer,Put>();
	}
	
	public void addPut(Put p) {
		if(!p.getQuoteDate().equals(quoteDate)) return;
		if(!p.getExpirationDate().equals(expirationDate)) return;
		
		theChain.put(p.getStrikePrice(), p);
	}

	@Override
	public ArrayList<Put> getInTheMoneyPuts() {
		ArrayList<Put> puts = new ArrayList<Put>();
		for(Put p: theChain.values()){
			if(p.isInTheMoney()) puts.add(p);
		}
		return puts;
	}

	@Override
	public ArrayList<Put> getOutOfTheMoneyPuts() {
		ArrayList<Put> puts = new ArrayList<Put>();
		for(Put p: theChain.values()){
			if(p.isOutOfTheMoney()) puts.add(p);
		}
		return puts;
	}

	@Override
	public ArrayList<Put> getPuts() {
		return new ArrayList<Put>(theChain.values());
	}

	public String toJSON(){
		Gson g = new Gson();
		StringBuilder sb = new StringBuilder();
		for(Put p : theChain.values()){
			sb.append(g.toJson(p));
		}
		return sb.toString();
	}

	public String getQuoteDate() {
		return quoteDate;
	}

}
