package com.framework.options.strategies.sellingputs;

import java.util.ArrayList;

import com.framework.options.Put;
import com.framework.options.containers.PutChain;

public class SellingPuts {
	public static SellingPutsResults run(SellingPutsDB x, int daysBeforeExpiration, int duration, int delta, int percentageOfProfit){
		ArrayList<String> expirationDates = x.getExpirationDates();
		SellingPutsResults results = new SellingPutsResults();
		for(String date: expirationDates){
			Put sellToOpen = x.getPut(date, daysBeforeExpiration, delta);
			ArrayList<Put> putQuotes = x.getPutQuotes(sellToOpen, duration);
			Put buyToClose = null;
			for(Put quote : putQuotes){
				buyToClose=quote;
				int currentPercentageProfit=(100*(sellToOpen.getPrice() - quote.getPrice()))/sellToOpen.getPrice();
				if(currentPercentageProfit > percentageOfProfit ) break;
				
			}
			results.add(date, sellToOpen, buyToClose);
		}
		return null;
	}
}
