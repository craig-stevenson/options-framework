package com.framework.options.containers.putchain;

import com.amazonaws.services.s3.AmazonS3;
import com.framework.options.Put;
import com.framework.options.containers.putquoteseries.PutQuoteSeries;

public class PutChainS3Interface {
	public static String getS3Key(PutChain p){
		StringBuilder key = new StringBuilder();
		key.append("PutChain/");
		//key.append(p.get);
		return null;
	}
	
	public static String getS3PutChainKey(Put p){
		return null;
	}
	
	public static PutQuoteSeries getFromS3(AmazonS3 client, String bucket, String key){
		return null;
	}
	
	public static void writeToS3(AmazonS3 client, String bucket, PutChain p){
		
	}
	
}
