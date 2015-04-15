package com.framework.options;

import static org.junit.Assert.*;

import org.junit.Test;

import com.framework.options.Put;
import com.framework.options.Put.Builder;
import com.thetaminer.options.livevol.LiveVolRawOption;

public class PutTest {
	public void testBuilder(){
		/**
		 * Model the follwoing option,
		 * {"quote_date":"2011-08-23",
		 * "expiration":"2012-09-22",
		 * "strike":113,
		 * "option_type":"p",
		 * "open":13.7,
		 * "high":14.7,
		 * "low":12.7,
		 * "close":13.00,
		 * "trade_volume":64.0,
		 * "bid_1545":12.31,
		 * "ask_1545":12.57,
		 * "implied_volatility_1545":0.2739,
		 * "delta_1545":"-0.3950"}
		 */
		Put.Builder b = new Put.Builder();
		b.setUnderlyingSymbol("SPY");
		b.setUnderlyingPrice(200);
		b.setQuoteDate("2011-08-23");
		b.setDateOfExpiration("2015-01-01");
		b.setStrikePrice(113);
		b.setBid(1231);
		b.setAsk(1257);
		b.setDelta(90);
		
		Put p = b.build();
		
		assertEquals(p.getUnderlyingSymbol(), "SPY");
		assertEquals(p.getUnderlyingPrice(), 200);
		assertEquals(p.getQuoteDate(), "2011-08-23");
		assertEquals(p.getExpirationDate(), "2015-01-01");
		assertEquals(p.getDelta(), 90);
		assertEquals(p.getPrice(), (1231+1257)/2);
	}
	@Test
	public void test() {
		testBuilder();
		
		testCreateFrom();
	}
	private void testCreateFrom() {
		StringBuilder input = new StringBuilder();
		input.append("SPY,") //underlying_symbol
		     .append("2005-01-10,") //quote_date
		     .append("SZC,") //root
		     .append("2005-01-22,") //expiration
		     .append("88.000,") //strike
		     .append("c,") //option_type
		     .append("0.00,") //open
		     .append("0.00,") //high
		     .append("0.00,") //low 
		     .append("0.00,") //close 
		     .append("0,") //trade_volume
		     .append("4804,") //bid_size_1545
		     .append("30.80,") //bid_1545
		     .append("4529,") //ask_size_1545
		     .append("31.00,") //ask_1545
		     .append("118.84,") //underlying_bid_1545
		     .append("118.85,") //underlying_ask_1545
		     .append("118.86,") //implied_underlying_price_1545
		     .append("118.85,") //active_underlying_price_1545
		     .append("0.6222,") //implied_volatility_1545
		     .append("0.9978,") //delta_1545
		     .append("0.000538,") //gamma_1545
		     .append("-0.010105,") //theta_1545
		     .append("0.001421,") //vega_1545
		     .append("2.634754,") //rho_1545
		     .append("3050,") //bid_size_eod
		     .append("30.90,") //bid_eod
		     .append("2750,") //ask_size_eod
		     .append("31.10,") //ask_eod
		     .append("118.95,") //underlying_bid_eod
		     .append("118.97,") //underlying_ask_eod
		     .append("0.00,") //vwap
		     .append("0,") //open_interest
		     .append("0"); //delivery_code
		LiveVolRawOption raw = LiveVolRawOption.CreateFrom(input.toString());
		
		Put p = Put.CreateFrom(raw);
		
		assertEquals(p.getUnderlyingSymbol(), "SPY");
		assertEquals(p.getUnderlyingPrice(), 0);
		assertEquals(p.getQuoteDate(), "2005-01-10");
		assertEquals(p.getExpirationDate(), "2005-01-22");
		assertEquals(p.getDelta(), 99);
		assertEquals(p.getPrice(), 0);
		
		
		
	}
	
	
}
