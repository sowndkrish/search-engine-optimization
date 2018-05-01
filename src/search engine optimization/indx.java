package sow;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.SortField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.document.DoubleDocValuesField;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.NumericUtils;
import org.apache.lucene.util.BytesRef;
public class indx {

	//adapted/simplified from luecene.demo.IndexFiles
	// Jianguo Lu, Dec 2016
	

	/**
	 * Index all text files under a directory.
	 */


		public static void main(String[] args) throws Exception {
			String indexPath = "F:/IRS/search/pr/sig_index_naive";
			String docsPath = "F:/IRS/search/vldb_icse_id.txt";
			Directory dir = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			IndexWriter writer = new IndexWriter(dir, iwc);
			
			FileReader f1 = new FileReader(docsPath);
			BufferedReader br = new BufferedReader(f1);
			
			double d;
			//String d=null;
			String lineTxt = null;
			
			while ((lineTxt = br.readLine()) != null)
		        {
					 String[] splits = lineTxt.split("\t");
			Document doc = new Document();

			doc.add(new StringField("docid", splits[0] , Field.Store.YES));

			doc.add(new TextField("title", splits[1], Field.Store.YES));
			doc.add(new StringField("title", splits[1], Field.Store.YES));	
			doc.add(new SortedField);

			doc.add(new TextField("titlexn", splits[2], Field.Store.YES));
			doc.add(new StringField("titlexn", splits[2], Field.Store.YES));
			doc.add(new StringField("year", splits[3], Field.Store.YES));
			doc.add(new StringField("date", splits[4], Field.Store.YES));
			doc.add(new TextField("conf", splits[7], Field.Store.YES));
			doc.add(new StringField("conf", splits[7], Field.Store.YES));
			
			writer.addDocument(doc);
			
		}

			writer.close();
		}
}