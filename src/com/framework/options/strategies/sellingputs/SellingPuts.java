package com.framework.options.strategies.sellingputs;

import java.util.List;

import com.framework.options.Put;

public class SellingPuts {
	public static SellingPutsResults run(SellingPutsDB db, int daysBeforeExpiration, int duration, int delta, int percentageOfProfit){
		List<String> expirationDates = db.getExpirationDates();
		SellingPutsResults results = new SellingPutsResults();
		for(String date: expirationDates){
			Put sellToOpen = db.getPut(date, daysBeforeExpiration, delta);
			List<Put> putQuotes = db.getPutQuotes(sellToOpen, duration);
			Put buyToClose = null;
			for(Put quote : putQuotes){
				buyToClose=quote;
				int currentPercentageProfit=(100*(sellToOpen.getPrice() - quote.getPrice()))/sellToOpen.getPrice();
				if(currentPercentageProfit > percentageOfProfit ) break;	
			}
			results.add(date, sellToOpen, buyToClose);
		}
		return results;
	}
}
