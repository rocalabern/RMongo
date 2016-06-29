package test;

import java.util.ArrayList;
import java.util.List;

import rmongo.RMongo;

public class RMongoTest {
	static String user = "******";
	static String pass = "*****";
	static String ip = "******";
	static String port = "******";
	
	public static void main(String[] args) {
		RMongo rmongo = new RMongo("mongodb://"+user+":"+pass+"@"+ip+":"+port+"/?authSource=audit_prod");
		try {
			rmongo.connectDatabase("audit_prod");
			
			String[] listDB = rmongo.showCollections();
			
	         for (String el : listDB) {
	        	 System.out.println(el);
	         }
	         
	         rmongo.setMaxRows(10);
	         
//	         String strFile = "C:/workspace/prova2.csv";
//	         rmongo.find("flex_eval", "{'contexts.ONLINE_BANKING':{'$exists':true}}", strFile);
	         
	         String strFile = "C:/workspace/prova3.csv";
	         List<String> listAVars = new ArrayList<String>();
	         listAVars.add("_id");
	         listAVars.add("contexts.ONLINE_BANKING.OnlineBankingRules.account.sumAmountRule.amount");
	         String[] listVars = listAVars.toArray(new String[0]);
	         rmongo.findVars(
	        		 "flex_eval", 
	        		 "{\"createDate\" : {\"$gte\" :  { \"$date\" : \"2016-06-26T00:00:00.000Z\"} }, \"contexts.ONLINE_BANKING\":{\"$exists\":true}}", 
	        		 listVars, 
	        		 strFile);
	         
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rmongo != null) rmongo.close();
		}
	}
}
