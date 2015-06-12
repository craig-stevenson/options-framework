package com.framework.options.containers.putquoteseries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import com.framework.options.Put;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class ImmutablePutQuoteSeries implements PutQuoteSeries {
	private static final int VERSION = 1;
	private final Integer strike;
	private final String expirationDate;
	private final TreeMap<String,Put> puts;
	
	private ImmutablePutQuoteSeries(ImmutablePutQuoteSeries.Builder builder) {
		this.strike = builder.strike;
		this.expirationDate = builder.expirationDate;
		this.puts = new TreeMap<String,Put>(builder.puts);
	}

	public static class Builder{
		private Integer strike;
		private String expirationDate;
		private TreeMap<String,Put> puts;
		private Builder(){}
		
		public Builder (Integer strike, String expirationDate){
			this.strike = strike;
			this.expirationDate = expirationDate;
			puts = new TreeMap<String,Put>();
		}
		
		public Builder(Put p){
			strike = p.getStrikePrice();
			expirationDate = p.getExpirationDate();
			puts = new TreeMap<String,Put>();
			addPut(p);
		}
				
		public void addPut(Put p){
			if(p.getStrikePrice() != strike.intValue()) return;
			if(!p.getExpirationDate().equals(expirationDate)) return;
			
			puts.put(p.getQuoteDate(), p);
		}
		
		public ImmutablePutQuoteSeries build(){
			return new ImmutablePutQuoteSeries(this);
		}
		
		public static ImmutablePutQuoteSeries fromJSON(String json){
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(json);
			if(!element.isJsonObject()) return null;
			
			JsonObject object = (JsonObject) element;
			
			return null;
		}
		
	}
	
	public Integer getStrikePrice() {
		return strike;
	}
	
	public ArrayList<Put> getPuts(){
		ArrayList<Put> p = new ArrayList<Put>(puts.values());
		return p;
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

	@Override
	public Collection<String> getQuoteDates() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toJSON(){
		Gson g = new Gson();
		return g.toJson(this);
		
	}
	
	public static void main(String[] args){
		Put.Builder putBuilder = new Put.Builder();
		
		ImmutablePutQuoteSeries.Builder b = new ImmutablePutQuoteSeries.Builder();
	}

}
