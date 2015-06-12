package com.framework.options.containers.putchain;

import java.util.ArrayList;

import com.framework.options.Put;
/**
 * 
 * @author craigstevenson
 *
 */
public interface PutChain {
	
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
