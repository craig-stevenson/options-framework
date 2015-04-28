package com.framework.options.containers.putseries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import com.framework.options.Put;

public class DefaultPutSeries implements PutSeries {
	private final Integer strike;
	private final String expirationDate;
	private final TreeMap<String,Put> puts;
	
	private DefaultPutSeries(DefaultPutSeries.Builder builder) {
		this.strike = builder.strike;
		this.expirationDate = builder.expirationDate;
		this.puts = new TreeMap<String,Put>(builder.puts);
	}

	public static class Builder{
		private Integer strike;
		private String expirationDate;
		private TreeMap<String,Put> puts;
		private Builder(){}
		
		public Builder(Put p){
			strike = p.getStrikePrice();
			expirationDate = p.getExpirationDate();
		}
				
		public void addPut(Put p){
			if(p.getStrikePrice() != strike.intValue()) return;
			if(!p.getExpirationDate().equals(expirationDate)) return;
			
			puts.put(p.getQuoteDate(), p);
		}
		
		public DefaultPutSeries build(){
			return new DefaultPutSeries(this);
		}
		
		
	}
	
	public Integer getStrikePrice() {
		return strike;
	}
	
	public ArrayList<Put> getPuts(){
		ArrayList<Put> p = new ArrayList<Put>(puts.values());
		return p;
	}

	public void add(Put p) {
		if(!(p.getStrikePrice() == strike.intValue())) return;
		puts.put(p.getQuoteDate(), p);
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void writeToFile(String dir) throws IOException {
		StringBuilder fileName = new StringBuilder();
		fileName.append(dir).append("PutSeries/").append(expirationDate).append("/");
		File f = new File(fileName.toString());
		if(!f.exists()){
			if(!f.mkdirs()){
				System.out.println("Couldnt make dirs");
			}
		}
		
		fileName.append("puts_").append(String.format("%05d", strike)).append(".txt");
		f = new File(fileName.toString());
		FileWriter pfw = new FileWriter(f);
		BufferedWriter pbw = new BufferedWriter(pfw);
		for(Put p: puts.values()){
			pbw.write(p.toJSON());
			pbw.newLine();
		}
		pbw.close();	
	}

}
