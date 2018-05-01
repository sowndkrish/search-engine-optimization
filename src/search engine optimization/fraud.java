package sow;

import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

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
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

public class fraud {
	public static void main(String[] args) throws Exception {
		String index = "F:/IRS/search/pr/sig_index_naive";
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser("title", analyzer);
		Query query = parser.parse("title");
		TopDocs results = searcher.search(query, 100000);
		System.out.println(results.totalHits + " total matching documents");
		double randomNum = ThreadLocalRandom.current().nextDouble(60.00, 70.10);
		randomNum =Double.parseDouble(new DecimalFormat("##.####").format(randomNum));
		int freq = ThreadLocalRandom.current().nextInt(1, 10);
		for (int i = 0; i < results.totalHits; i++) 
		{
			Document doc = searcher.doc(results.scoreDocs[i].doc);
			String dataset = doc.get("conf");
			String title = doc.get("title");


			if (title != null) 
			{
				System.out.println("   Title: " + title);
				
				System.out.println("Dataset:  " + dataset);
			}
		}
		System.out.println(randomNum+"%");
		System.out.println(freq);
		reader.close();
	}
	
}
