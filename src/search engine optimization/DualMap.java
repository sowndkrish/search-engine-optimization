package sow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public final class DualMap<K1, K2, V> implements IDualMap<K1,K2,V> {

    private final Map<K1, V> map1 = new HashMap<K1, V>();

    private final Map<K2, V> map2 = new HashMap<K2, V>();

    @Override
    public Map<K1, V> getMap1() {
        return Collections.unmodifiableMap(map1);
    }

    @Override
    public Map<K2, V> getMap2() {
        return Collections.unmodifiableMap(map2);
    }

    @Override
    public void put(K1 key1, K2 key2, V value) {
        map1.put(key1, value);
        map2.put(key2, value);
    }
    public static void main(String[] args) throws Exception {
    	 DualMap<String, String, String> d=new DualMap();
    	Map<String, String> map = new HashMap<String, String>();
        BufferedReader in1 = new BufferedReader(new FileReader("F:/IRS/search/clust.txt"));
        String line = null;
        while ((line = in1.readLine()) != null) {
            String parts[] = line.split(" ");
           //System.out.println(parts[0]+" "+parts[1]);
          //  map.put(parts[0], parts[1]);
           
            d.put(parts[0], "1", parts[1]);
        }
        Map m1=d.getMap1();
        Map m2=d.getMap2();

        System.out.print("map1"+m1);
        System.out.print("map2"+m2);

        System.out.print(m1.get("6"));
        in1.close();
    }
}
