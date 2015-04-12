package com.framework.options.containers;

import java.util.ArrayList;
import java.util.TreeMap;

import com.framework.options.Put;
/**
 * This is the default implementation of a PutChain
 * @author craigstevenson
 *
 */
public class DefaultPutChain implements PutChain {
	private String quoteDate;
	TreeMap<Integer,Put> theChain;
	
	public DefaultPutChain(String quoteDate){
		this.quoteDate = quoteDate;
		theChain = new TreeMap<Integer,Put>();
	}
	
	@Override
	public void addPut(Put p) {
		if(!p.getQuoteDate().equals(quoteDate)) return;
		
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

}
