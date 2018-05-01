package sow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class readm {
	static String vocabindex;
	static Map<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
	static Map<ArrayList<String>, ArrayList<String>> hashMap1 = new HashMap<ArrayList<String>, ArrayList<String>>();
	 public static void main(String[] args) throws Exception {
	        Map<String, String> map = new HashMap<String, String>();
	        BufferedReader in = new BufferedReader(new FileReader("F:/IRS/search/vocabulary.txt"));
	        String line = null;
	        while ((line = in.readLine()) != null) {
	            String parts[] = line.split(" ");
	            map.put(parts[0], parts[1]);         
	        }
	        for (Entry<String, String> entry : map.entrySet()) {
	            if (entry.getValue().equals("software")) {
	            	vocabindex=entry.getKey();
	                System.out.println(entry.getKey());
	            }
	        }
	        in.close();
	        finddocument();
	    }
	 public static void finddocument() throws IOException
	 {
		 new ArrayList<String>();
	        BufferedReader in1 = new BufferedReader(new FileReader("F:/IRS/search/resulttfidf.txt"));
	        String line = null;
	        while ((line = in1.readLine()) != null) {
	            String parts[] = line.split(" ");
	            addValues(parts[0], parts[1]);
	        }
	        Iterator it = hashMap.keySet().iterator();
	        ArrayList tempList = null;
	           String key = vocabindex;            
	           tempList = hashMap.get(key);
	           Set<String> hs = new HashSet<>();
	           hs.addAll(tempList);
	           tempList.clear();
	           tempList.addAll(hs);
	          // System.out.println("Key : "+key+ " , Value : "+tempList);
	           Iterator<String> iterator = tempList.iterator();
	   		while (iterator.hasNext()) {
	   			findclusters(iterator.next());
	   		}
	        in1.close();
	 }
	 public static void addValues(String key, String value) { 
		   ArrayList tempList = null;
		   if (hashMap.containsKey(key)) {
		      tempList = hashMap.get(key);
		      if(tempList == null)
		         tempList = new ArrayList();
		      tempList.add(value);  
		   } else {
		      tempList = new ArrayList();
		      tempList.add(value);               
		   }
		   hashMap.put(key,tempList);
		}
	 public static void findclusters(String doc) throws IOException
	 {
		 Map<String, String> map1 = new HashMap<String, String>();
	        BufferedReader bufin = new BufferedReader(new FileReader("F:/IRS/search/clust.txt"));
	        String line = null;
	        while ((line = bufin.readLine()) != null) {
	            String parts[] = line.split(" ");
	            map1.put(parts[0], parts[1]);         
	        }
	        for (Entry<String, String> entry : map1.entrySet()) {
	            if (entry.getKey().equals(doc)) {
	                System.out.println("Key"+entry.getKey()+"Val"+entry.getValue());
	            }
	        }
	        bufin.close();
	 }
}

