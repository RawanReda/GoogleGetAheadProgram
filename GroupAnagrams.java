import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GroupAnagrams {
	public static String sort_word(String s) {
		char[] content= s.toCharArray();
		Arrays.sort(content);
		
		return new String(content);
		
	}
	public static void anagrams_group(String[] s) {
		
		HashMap<String, ArrayList<String>> hm= new HashMap<>();
		for(String word: s) {
			String sorted= sort_word(word);
			ArrayList<String> list= hm.get(sorted); 
			if(list==null) 
				list= new ArrayList<String>(); 
			list.add(word); 
			hm.put(sorted,list);
		}
		for (String name: hm.keySet()) {
		    String key = name.toString();
		    String value = hm.get(name).toString();
		    System.out.println(key + " " + value);
		}
	}
public static void main(String[] args) {
	HashMap<String, ArrayList<String>> hm= new HashMap<>();
	anagrams_group(new String[] {"eat","tea","tan","ate","nat","bat"});
}
}
