package com.framework.options.strategies.sellingputs;

import java.util.ArrayList;

import com.framework.options.Put;
import com.framework.options.containers.PutChain;

public interface SellingPutsDB {

	ArrayList<String> getExpirationDates();

	Put getPut(String date, int daysBeforeExpiration, int delta);

	ArrayList<Put> getPutQuotes(Put p, int duration);

}
