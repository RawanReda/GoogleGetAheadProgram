
import java.util.ArrayList;



public class NumberInWords {
	static ArrayList<String> words_used;

	public static String spellNumber(long num) {

		words_used = new ArrayList<String>();
		String numString = num + "";
		int rem_digits = numString.length();
		String temp = "";
		for (int i = 0; i < numString.length(); i++) {
			int times = 0;
			if (rem_digits >= 19 && rem_digits < 22) {
				times = rem_digits - 18;
				
				if (mapBase(numString, times, i)) {
					words_used.add("quintillion");
					
				}
			} else if (rem_digits >= 16 && rem_digits < 19) {
				times = rem_digits - 15;
			

				if (mapBase(numString, times, i)) {
					words_used.add("quadrillion");
				
				}
			} else if (rem_digits >= 13 && rem_digits < 16) {
				times = rem_digits - 12;

				if (mapBase(numString, times, i)) {
					words_used.add("trillion");
					
				}
			} else if (rem_digits >= 10 && rem_digits < 13) {
				times = rem_digits - 9;

				if (mapBase(numString, times, i)) {
					words_used.add("billion");
					
				}
			} else if (rem_digits >= 7 && rem_digits < 10) {
				times = rem_digits - 6;

				if (mapBase(numString, times, i)) {
					words_used.add("million");
					
				}
			} else if (rem_digits >= 4 && rem_digits < 7) {
				times = rem_digits - 3;

				if (mapBase(numString, times, i)) {
					
					words_used.add("thousand");
					
				}
			} else if (rem_digits <= 3) {
				times = rem_digits;

				if (mapBase(numString, times, i)) {
					words_used.add(temp);
					
				}

			}
			i += (times - 1);
			rem_digits -= times;

		}
		boolean first = true;
		String sb =""; 
		
		for (String x : words_used) {
			if(!x.equals("")) {
			if (first) {
				first=false;
				sb+=x;
			} else {
				sb+=" " + x;
			}}
		}
		
		return sb;
	}

	public static boolean mapBase(String numString, int times, int i) {
		boolean found = false;
		for (int j = times; j > 0 && i < numString.length(); j--, i++) {
			String temp = "";
			if (j == 1) {
				temp = getWord(Integer.parseInt(numString.charAt(i) + ""));
				if(!temp.equals("")) found=true;
				words_used.add(temp);
				
			} else if (j == 2) {
				if (Integer.parseInt(numString.charAt(i) + "") == 1) {
					temp = tens(Integer.parseInt(numString.charAt(i+1) + ""));
					if(!temp.equals("")) found=true;
					words_used.add(temp);
					
					break;
				} else {
					temp = getWordTens(Integer.parseInt(numString.charAt(i) + ""));
					if(!temp.equals("")) found=true;
					words_used.add(temp);
				}
			} else {
				String x = getWord(Integer.parseInt(numString.charAt(i) + ""));
				if (!x.equals("")) {
					found=true;
					words_used.add(x);
					words_used.add("hundred");
				}
			}
		}
		return found;
	}

	public static String tens(int i) {
		switch (i) {
		case 0:
			return "ten";
		case 1:
			return "eleven";
		case 2:
			return "twelve";
		case 3:
			return "thirteen";
		case 4:
			return "fourteen";
		case 5:
			return "fifteen";
		case 6:
			return "sixteen";
		case 7:
			return "seventeen";
		case 8:
			return "eighteen";
		case 9:
			return "nineteen";
		default:
			return "";
		}

	}

	public static String getWord(int i) {
		switch (i) {
		case 1:
			return "one";
		case 2:
			return "two";
		case 3:
			return "three";
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9:
			return "nine";
		default:
			return "";
		}

	}

	public static String getWordTens(int i) {
		switch (i) {

		case 2:
			return "twenty";
		case 3:
			return "thirty";
		case 4:
			return "forty";
		case 5:
			return "fifty";
		case 6:
			return "sixty";
		case 7:
			return "seventy";
		case 8:
			return "eighty";
		case 9:
			return "ninety";
		default:
			return "";
		}

	}

	public static void main(String[] args) {
		System.out.println(spellNumber(100100));
	}

//	 @Test
//	 public void testEquals() {
//		 assertEquals(0, spellNumber(3835217595)); 
//		 if (spellNumber(3835217595) != "three billion eight hundred thirty five million two hundred seventeen thousand five hundred ninety five") {
//		        cout << "wrong answer in test 1" << endl;
//		    }
//		    if (get_amount_in_words(0) != "zero") {
//		        cout << "wrong answer in test 2" << endl;
//		    }
//		    if (get_amount_in_words(1654) != "one thousand six hundred fifty four") {
//		        cout << "wrong answer in test 3" << endl;
//		    }
//		    if (get_amount_in_words(18446744073709551615) != "eighteen quintillion four hundred forty six quadrillion seven hundred forty four trillion seventy three billion seven hundred nine million five hundred fifty one thousand six hundred fifteen") {
//		        cout << "wrong answer in test 4" << endl;
//		    }
//		    if (get_amount_in_words(3017) != "three thousand seventeen") {
//		        cout << "wrong answer in test 5" << endl;
//		    }
//		    if (get_amount_in_words(50) != "fifty") {
//		        cout << "wrong answer in test 6" << endl;
//		    }
//		    if (get_amount_in_words(11111112) != "eleven million one hundred eleven thousand one hundred twelve") {
//		        cout << "wrong answer in test 7" << endl;
//		    }
//		    if (get_amount_in_words(100090030) != "one hundred million ninety thousand thirty") {
//		        cout << "wrong answer in test 8" << endl;
//		    }
//		    if (get_amount_in_words(100100) != "one hundred thousand one hundred") {
//		        cout << "wrong answer in test 9" << endl;
//		    }
//		
//	 }

}
