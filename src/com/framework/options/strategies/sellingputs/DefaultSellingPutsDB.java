package com.framework.options.strategies.sellingputs;

import java.util.ArrayList;

import com.framework.options.Put;

public class DefaultSellingPutsDB implements SellingPutsDB {
	ArrayList<String> expirationDates;
	
	public void add(Put p){
		
	}
	
	@Override
	public ArrayList<String> getExpirationDates() {
		return new ArrayList<String>(expirationDates);
	}

	@Override
	public Put getPut(String date, int daysBeforeExpiration, int delta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Put> getPutQuotes(Put p, int duration) {
		// TODO Auto-generated method stub
		return null;
	}

}
