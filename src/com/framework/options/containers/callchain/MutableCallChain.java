package com.framework.options.containers.callchain;

import java.util.ArrayList;
import java.util.TreeMap;

import com.framework.options.Call;

public class MutableCallChain implements CallChain{
	private String quoteDate;
	TreeMap<Integer,Call> theChain;
	
	public MutableCallChain(String quoteDate){
		this.quoteDate = quoteDate;
		theChain = new TreeMap<Integer,Call>();
	}
	
	public void addCall(Call c){
		if(!c.getQuoteDate().equals(quoteDate)) return;
		
		theChain.put(c.getStrikePrice(), c);
	}
	
	public ArrayList<Call> getInTheMoneyCalls(){
		ArrayList<Call> calls = new ArrayList<Call>();
		for(Call c : theChain.values()){
			if(c.isInTheMoney()) calls.add(c);
		}
		return calls;
	}
	
	public ArrayList<Call> getOutOfTheMoneyCalls(){
		ArrayList<Call> calls = new ArrayList<Call>();
		for(Call c : theChain.values()){
			if(c.isOutOfTheMoney()) calls.add(c);
		}
		return calls;
	}
	
	public ArrayList<Call> getCalls(){
		return new ArrayList<Call>(theChain.values());
	}
	
	public static void main(String[] args) {
		

	}

}
