package com.framework.options.containers.optionchainquote;

import java.util.ArrayList;
import java.util.TreeMap;

import com.framework.options.Call;
import com.framework.options.Put;
import com.framework.options.containers.callchain.CallChain;
import com.framework.options.containers.putchain.MutablePutChain;
import com.framework.options.containers.putchain.PutChain;

public class MutableEODOptionChainQuote {
	private String quoteDate;
	private TreeMap<String, MutablePutChain> putChains;
	private TreeMap<String, CallChain> callChains;
	
	public void addPut(Put p){
		
		if(!putChains.containsKey(p.getExpirationDate())){
			MutablePutChain tmp = new MutablePutChain(p);
			putChains.put(p.getExpirationDate(), tmp);
			return;
		}
		
		MutablePutChain pc = putChains.get(p.getExpirationDate());
		pc.addPut(p);
	}
	
	public void addCall(Call c){
		
	}
	
	public String getQuoteDate(){
		return quoteDate;
	}
	
}
