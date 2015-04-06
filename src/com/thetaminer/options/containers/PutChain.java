package com.thetaminer.options.containers;

import java.util.ArrayList;

import com.thetaminer.options.Put;

public interface PutChain {
	/**
	 * 
	 * @param p
	 */
	public void addPut(Put p);
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Put> getInTheMoneyPuts();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Put> getOutOfTheMoneyPuts();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Put> getPuts();

}
