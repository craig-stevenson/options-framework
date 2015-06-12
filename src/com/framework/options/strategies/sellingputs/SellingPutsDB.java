package com.framework.options.strategies.sellingputs;

import java.util.ArrayList;
import java.util.List;

import com.framework.options.Put;
import com.framework.options.containers.putchain.PutChain;

public interface SellingPutsDB {

	public List<String> getExpirationDates();

	public Put getPut(String date, int daysBeforeExpiration, int delta);

	public List<Put> getPutQuotes(Put p, int duration);

}
