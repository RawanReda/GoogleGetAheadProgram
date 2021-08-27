import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class UnknownAlphabet {
	public static Character[] findAlphabet(String[] words) {
		ArrayList<Character> all_chars = new ArrayList<Character>();
		HashMap<Character, Integer> dependencies = new HashMap<Character, Integer>();
		HashMap<Character, ArrayList<Character>> child = new HashMap<Character, ArrayList<Character>>();

		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			for (int j = 0; j < s.length(); j++) {
				if (!dependencies.containsKey(s.charAt(j))) {
					all_chars.add(s.charAt(j));
					dependencies.put(s.charAt(j), 0);
					child.put(s.charAt(j), new ArrayList<Character>());
				}
			}
		}

		for (int i = 0; i < words.length - 1; i++) {
			String s1 = words[i];
			String s2 = words[i + 1];

			int loopsize = Math.min(s1.length(), s2.length());
			for (int j = 0; j < loopsize; j++) {
				if (s1.charAt(j) != s2.charAt(j)) {
					ArrayList<Character> children = child.get(s1.charAt(j));
					children.add(s2.charAt(j));
					child.put(s1.charAt(j), children);
					dependencies.put(s2.charAt(j),dependencies.get(s2.charAt(j))+1); 
					break;
				}
			}
		}
	for (Entry<Character, ArrayList<Character>> entry : child.entrySet()) {
			
			ArrayList<Character> children = entry.getValue();
			System.out.println(entry.getKey()+" "+children.toString());
		}

HashSet<Character>res_contains= new HashSet<Character>();
		Character[] res = new Character[all_chars.size()];
		int index=0; 
		for(int i=0; i<all_chars.size(); i++) {
			if(dependencies.get(all_chars.get(i))==0) {
				res[index++]= all_chars.get(i);
				res_contains.add(all_chars.get(i));
			}
		}

		int toBeProcessed=0; 
		while(toBeProcessed< res.length) {
			Character c= res[toBeProcessed]; 
			ArrayList<Character> children= child.get(c);

			for(Character x: children) {
				dependencies.put(x,dependencies.get(x)-1); 
			}
			
			for(int i=0; i<children.size(); i++) {
		
				if(!res_contains.contains(children.get(i)) && dependencies.get(children.get(i))==0) {
					res[index++]= children.get(i);
					res_contains.add(children.get(i));
				}
			}
			toBeProcessed++; 

		}
	

		return res;

	}

	public static void main(String[] args) {

		Character[] r = findAlphabet(new String[] {"#T", "at", "At", "cat", "cA", "c@t", "cT", "ct", "cc"});
		for (int i = 0; i < r.length; i++) {
			System.out.println(r[i]);
		}

	}

}
