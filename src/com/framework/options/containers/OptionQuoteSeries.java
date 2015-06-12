package com.framework.options.containers;

import java.util.Collection;

public interface OptionQuoteSeries {
	public Integer getStrikePrice();
	public String getExpirationDate();
	public Collection<String> getQuoteDates();
}
