package sow;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortedNumericSortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.SortField;


public class SearchPageRankIndex {

	public static void main(String[] args) throws Exception {
		String index = "G:/sigmod data/pagerank-sigmode-index/2";
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser("title", analyzer);
		Query query = parser.parse("data");
		//SortedNumericSortField sf = new SortedNumericSortField("pagerank",SortField.Type.DOUBLE,true);
		//Sort sort = new Sort(sf);
		TopDocs results = searcher.search(query, 100000);
		System.out.println(results.totalHits + " total matching documents");
		for (int i = 0; i < results.totalHits; i++) {
			Document doc = searcher.doc(results.scoreDocs[i].doc);
			
			//String path = doc.get("path");
			//System.out.println((i + 1) + ". " + path);
			
			String title = doc.get("title");
			if (title != null) {
				System.out.println("   Title: " + doc.get("title")+"pagerank"+doc.get("pagerank"));
			}
		}
		reader.close();
	}

}
