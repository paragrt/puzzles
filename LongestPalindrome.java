package algorithms;

public class LongestPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] s = {"zasaabbaacxyq"    //even length
						,"abcdMalayalam" //odd length
						,"xyzabcdefghi"  //no palindrome
		};
		for(int i = 0; i < s.length; i++) {
			String pal = palindromeUtil(s[i]);
			System.out.println(s[i] + " palindrome is " + pal);
		}
	}

	/**
	 * @param s
	 * @param arr
	 * @return
	 */
	private static String palindromeUtil(String s) {
		char[] arr = s.toCharArray();
		int pal_length = Integer.MIN_VALUE;
		int idx = -1;
		
		//check for even palindrome abccba kind
		for(int mp = 1; mp < arr.length-1;mp++)
		{
			int mypal = 0;
			for( int i = mp, j = mp+1; i >= 0 && j <arr.length;i--,j++)
			{
				if ( arr[i] == arr[j] ) {
					mypal++;
				} else {//end of palindrome
					//and is longer than previously seen
					if ( mypal > pal_length )
					{
						pal_length = mypal;
						idx = mp;
					}
					break;
				}
			}
		}
		
		String retStr1 = pal_length>1? s.substring(idx-pal_length+1, idx+pal_length+1):null;
		//check for ODD palindrome abccba kind
		pal_length = Integer.MIN_VALUE;
		idx = -1;
		for(int mp = 1; mp < arr.length-1;mp++)
		{
			int mypal = 0;
			for( int i = mp, j = mp; i >= 0 && j <arr.length;i--,j++)
			{
				if ( arr[i] == arr[j] ) {
					mypal++;
				} else {//end of palindrome
					//and is longer than previously seen
					if ( mypal > pal_length )
					{
						pal_length = mypal;
						idx = mp;
					}
					break;
				}
			}
		}
		
		String retStr2 = pal_length>1? s.substring(idx-pal_length, idx+pal_length+1):null;
		if ( retStr1 == null && retStr2 == null ) {
			return "Not Found Palindrome";
		} else if ( retStr1 != null && retStr2 != null ) {
			if ( retStr1.length()>retStr2.length() )  return retStr1;
			else return retStr2;
		} else {
			if ( retStr1 == null ) return retStr2;
			else return retStr1;
		}
	}
}
