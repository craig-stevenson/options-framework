package com.framework.options.containers.putseries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.framework.options.Put;

public interface PutSeries {
	public Integer getStrikePrice();
	public Collection<Put> getPuts();
	public String getExpirationDate();
	public void writeToFile(String dir) throws IOException;
	public interface Builder{
		
		PutSeries build();
	}
}
