import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Stack;

public class BalancedParanthesesSol {
	
	public static int longestBalanced(String str) {
		Stack<Integer> unmatchedBrackets= new Stack<Integer>();
		int max=0; 
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='(') {
				unmatchedBrackets.push(i); 
			}else if(!unmatchedBrackets.isEmpty()){
				int open= unmatchedBrackets.pop();
				int length = i-open+1; 
				max= Math.max(max, length); 
				}
		}
		return max; 
	}
	
 @Test
 public void testEquals() {
	 assertEquals(0, longestBalanced("")); 
	 assertEquals(0, longestBalanced("("));
	 assertEquals(0, longestBalanced(")"));
	 assertEquals(2, longestBalanced("()")); 
	 assertEquals(4, longestBalanced("(())"));
	
 }
}
