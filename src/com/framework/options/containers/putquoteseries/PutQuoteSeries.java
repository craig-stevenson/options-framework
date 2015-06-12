package com.framework.options.containers.putquoteseries;

import java.util.Collection;
import java.util.List;

import com.framework.options.Put;
import com.framework.options.containers.OptionQuoteSeries;

public interface PutQuoteSeries extends OptionQuoteSeries{
	public List<Put> getPuts();
}
