package com.thetaminer.options.livevol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LiveVolRawSplitter {
	public static String in = "/Users/craigstevenson/dev/ThetaMiner.com/RAW/SPY/SPY.options-eod-calcs.2013.csv";
	public static String out = "/Users/craigstevenson/dev/ThetaMiner.com/RAW/SPY/SPY.options-eod-calcs.2013-GOOD.csv";
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader( new FileReader(in) );
		r.readLine();//first line is header, drop it like it's hot.
		String line=r.readLine();
		
		BufferedWriter w = new BufferedWriter( new FileWriter(out));
		while(line!=null){
			//parse the line read in
			LiveVolRawOption tmp = LiveVolRawOption.CreateFrom(line);
			
			if(tmp.bid_size_1545.equals("0.0")){
				line=r.readLine();
				continue;
			}
			
			if(tmp.bid_size_1545.equals("0")){
				line=r.readLine();
				continue;
			}
			
			if(tmp.ask_size_1545.equals("0.0")){
				line=r.readLine();
				continue;
			}
			
			if(tmp.ask_size_1545.equals("0")){
				line=r.readLine();
				continue;
			}
			
			w.write(line);
			w.newLine();
			line=r.readLine();
		}
		w.close();
		
	}

}
