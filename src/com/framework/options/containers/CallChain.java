package com.framework.options.containers;

import java.util.ArrayList;

import com.framework.options.Call;

public interface CallChain {
	/**
	 * 
	 * @param c
	 */
	public void addCall(Call c);
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Call> getInTheMoneyCalls();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Call> getOutOfTheMoneyCalls();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Call> getCalls();
}
