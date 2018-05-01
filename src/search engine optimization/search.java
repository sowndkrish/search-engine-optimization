package sow;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.ScoreDoc; 
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortedNumericSortField;
import org.apache.lucene.search.SortField;
public class search {
	//Jianguo Lu, Dec 2016. 
	//Simplified Lucene Search 
	


		public static void main(String[] args) throws Exception {
			String index = "F:/IRS/search/pr/sig_index";
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
			IndexSearcher searcher = new IndexSearcher(reader);
			Analyzer analyzer = new StandardAnalyzer();
			QueryParser parser = new QueryParser("title", analyzer);
			Query query = parser.parse("calculus");
			//SortField sf=new SortField("pagerank", SortField.Type.DOUBLE, false);
			//SortedNumericSortField sf = new SortedNumericSortField("pagerank",SortField.Type.DOUBLE,true);
			//Sort sort = new Sort(sf);
			TopDocs results = searcher.search(query, 100000);
			System.out.println(results.totalHits + " total matching documents");
			HashMap<String,String> map = new HashMap<String,String>();
			ArrayList out = new ArrayList();
			for (int i = 0; i < results.totalHits; i++) {
				ArrayList al = new ArrayList();
				Document doc = searcher.doc(results.scoreDocs[i].doc);
				String docid = doc.get("docid");
				String title = doc.get("title");
				String year=doc.get("year");
				String date=doc.get("date");
				String conf=doc.get("conf");
				String pr=doc.get("pagerank");
				map.put(title, pr);
				al.add(title);		
				al.add(year);
				al.add(date);
				al.add(conf);
				al.add(docid);
				out.add(al);				
			}
			Map<String,String> result = sortByValues(map);
			Set mapSet = (Set) result.entrySet();
            //Create iterator on Set 
            Iterator mapIterator = mapSet.iterator();
            System.out.println("Display the key/value of HashMap.");
            while (mapIterator.hasNext()) {
                    Map.Entry mapEntry = (Map.Entry) mapIterator.next();
                    // getKey Method of HashMap access a key of map
                    String keyValue = (String) mapEntry.getKey();
                    ArrayList in=new ArrayList();
                    String extra=null;
                    String extra1=null;
                    int count = 1;
                    for(Iterator c = out.iterator(); c.hasNext();)
                    {
                    for(Iterator d = ((ArrayList)c.next()).iterator();
                    d.hasNext();)
                    {                    	
                    	//in.add((String)d.next());
                  // System.out.println((String)d.next());
                    	extra=(String)d.next();
                    	//System.out.print(extra);
                    }
                    
                 //  System.out.println("Loop count #" + count);
                    count++;
                    }
                  
                    String value = (String) mapEntry.getValue();
                    System.out.println("Key : " + keyValue + "= Value : " + value);
            }

			reader.close();
		}
		public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
	        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
	      
	        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

	            @Override
	            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });

	        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
	      
	        for(Map.Entry<K,V> entry: entries){
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }
	      
	        return sortedMap;
	    }
}
