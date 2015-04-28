package com.framework.options.containers.putseries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;

import com.amazonaws.services.s3.AmazonS3;
import com.framework.options.Put;
import com.framework.options.containers.putseries.DefaultPutSeries.Builder;
import com.thetaminer.options.livevol.LiveVolRawOption;

public class PutSeriesS3Interface {
	
	/**
	 * returns the S3 Key for the PutSeries p
	 * @param p
	 * @return
	 */
	public static String getS3Key(PutSeries p){
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
	
	public PutSeries getFromS3(AmazonS3 client, String bucket, String key){
		return null;
	}
	
	public void writeToS3(AmazonS3 client, String bucket, PutSeries p){
		
	}
	
}
