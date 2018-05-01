package sow;


	import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
	import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LegacyDoubleField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
	 
	public class sortmap {
		public static String hex2Decimal(String s) {
	        String digits = "0123456789ABCDEF";
	        s = s.toUpperCase();
	        int val = 0;
	        for (int i = 0; i < s.length(); i++) {
	            char c = s.charAt(i);
	            int d = digits.indexOf(c);
	            val = 16*val + d;
	        }
	        String value=Integer.toString(val);
	        return value;
		}
		public static String decimal2Hex(int d) {
	        String digits = "0123456789ABCDEF";
	        if (d == 0) return "0";
	        String hex = "";
	        while (d > 0) {
	            int digit = d % 16;                // rightmost digit
	            hex = digits.charAt(digit) + hex;  // string concatenation
	            d = d / 16;
	        }
	        return hex;
	    }

		public static void main(String[] args) throws IOException {
			String indexPath = "F:/IRS/search/pr/sig_index/test";
			String docsPath = "G:/sigmod data/sigmod_id.txt";
			System.out.println("Indexing to directory '" + indexPath + "'...");
			Directory dir = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			IndexWriter writer = new IndexWriter(dir, iwc);
			
			FileReader f1 = new FileReader(docsPath);
			BufferedReader br = new BufferedReader(f1);
			String pagerank = "F:/IRS/search/pr/pagerank_sigmod_id.txt";
			FileReader f2 = new FileReader(pagerank);
			
			BufferedReader buf = new BufferedReader(f2);
			double d;
			//String d=null;
		String lineTxt = null;
		  HashMap<String,String> map = new HashMap<String,String>();
		  
		  while ((lineTxt = buf.readLine()) != null)
        {
			  String[] splits = lineTxt.split("\t");
			  //System.out.println(splits[0]+"     "+splits[1]);
			  map.put(splits[0], splits[1]);
        }
		// Map result = sortByValue(map);
		// System.out.println(map);
		// Map<String,String> result = sortByValues(map);
	      //  System.out.println("Sorted Map in Java by values: " + result);


 
		  while ((lineTxt = br.readLine()) != null)
	        {
				 String[] splits = lineTxt.split("\t");
			  Document doc = new Document();
				String deci=hex2Decimal(splits[0]);
				doc.add(new StringField("docid", deci , Field.Store.YES));
				//String pr=decimal2Hex(Integer.parseInt(hex2Decimal(splits[0])));
				//System.out.println(pr);
				doc.add(new TextField("title", splits[1], Field.Store.YES));
				doc.add(new StringField("title", splits[1], Field.Store.YES));	
				doc.add(new TextField("titlexn",splits[2],Field.Store.YES));
				doc.add(new TextField("year",splits[3],Field.Store.YES));
				doc.add(new TextField("date",splits[4],Field.Store.YES));
				doc.add(new TextField("conf",splits[6],Field.Store.YES));
			//System.out.println(map.get(deci));
				
				System.out.println(map.get(deci));
				if(map.get(deci)==null)
					d=0.0;
				else
					d=Double.parseDouble(map.get(deci));
					//d=map.get(deci);
				//System.out.println("pagerank is"+d);
				doc.add(new LegacyDoubleField("pagerank",d,Field.Store.YES));
				
				writer.addDocument(doc);
				
		}

			writer.close();
        

	 
	 
			
	 
			//System.out.println(result.get("1983604014"));
	 
			// <Integer, Integer> Map
	 
		}
		public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
	        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
	      
	        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

	            @Override
	            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });
	      
	        //LinkedHashMap will keep the keys in the order they are inserted
	        //which is currently sorted on natural ordering
	        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
	      
	        for(Map.Entry<K,V> entry: entries){
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }
	      
	        return sortedMap;
	    }


			public static Map sortByValue(Map unsortedMap) {
			Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
			sortedMap.putAll(unsortedMap);
			return sortedMap;
		}
	 
	}
	 
	class ValueComparator implements Comparator {
		Map map;
	 
		public ValueComparator(Map map) {
			this.map = map;
		}
	 
		public int compare(Object keyA, Object keyB) {
			if((keyA!=null) && (keyB!=null)){
			Comparable valueA = (Comparable) map.get(keyA);
			Comparable valueB = (Comparable) map.get(keyB);
			System.out.println(valueA+" "+valueB);
			//if(valueA!=null)
			return valueB.compareTo(valueA);}
			else
			return 0;
			
				
		}
	}