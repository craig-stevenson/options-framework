package com.framework.options.containers.putquoteseries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.framework.options.Put;
import com.framework.options.containers.putquoteseries.ImmutablePutQuoteSeries.Builder;
import com.thetaminer.options.livevol.LiveVolRawOption;

public final class PutQuoteSeriesS3Interface {
	
	/**
	 * returns the S3 Key for the PutSeries p
	 * @param p
	 * @return
	 */
	public static String getS3Key(PutQuoteSeries p){
		StringBuilder key = new StringBuilder();
		key.append("PutSeries/");
		key.append(p.getExpirationDate()).append("/");
		key.append(p.getStrikePrice());
		
		return key.toString();
	}
	
	/**
	 * returns the S3 Key for the PutSeries which would contain p
	 * @param p
	 * @return
	 */
	public static String getS3PutSeriesKey(Put p){
		StringBuilder key = new StringBuilder();
		key.append("PutSeries/");
		key.append(p.getExpirationDate()).append("/");
		key.append(p.getStrikePrice());
		
		return key.toString();
	}
	
	public static PutQuoteSeries getFromS3(AmazonS3 client, String bucket, String key){
		PutQuoteSeries ps=null;
		S3Object o = client.getObject(bucket, key);
		
		return ps;
	}
	
	public static void writeToS3(AmazonS3 client, String bucket, PutQuoteSeries p) throws IOException{
		String key = getS3Key(p);
		File f = File.createTempFile("S3-temp", ".txt");
		f.deleteOnExit();
		Writer w = new OutputStreamWriter(new FileOutputStream(f));
		w.write(p.toString());
		client.putObject(bucket, key, f);
	}
	
}
