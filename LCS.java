import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LCS {

	/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
	int lcs(char[] X, char[] Y, int m, int n, Map<String, Integer> map) {
		String tempX = new String(X, 0, m);
		String tempY = new String(Y, 0, n);
		String key = tempX + ":" + tempY;
		// return cached result
		if (map.get(key) != null) {
			return map.get(key);
		}
		// System.out.println(key);

		if (m == 0 || n == 0) {
			return 0;
		}
		if (X[m - 1] == Y[n - 1]) {
			
			int len = 1 + lcs(X, Y, m - 1, n - 1, map);
			map.put(key, len);
		} else {
			int len = max(lcs(X, Y, m, n - 1, map), lcs(X, Y, m - 1, n, map));
			map.put(key, len);
		}
		return map.get(key);
	}

	/* Utility function to get max of 2 integers */
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public static void main(String[] args) {
		LCS lcs = new LCS();
		// ans Longest seq = [A, G, GTAB] leng = 4
		//String s1 = "AGGTAB";
		//String s2 = "GXTXAYB";

		String s1 = "abcdefgpqxr";
		String s2 = "adepqrfg";
		char[] X = s1.toCharArray();
		char[] Y = s2.toCharArray();
		int m = X.length;
		int n = Y.length;

		Map<String, Integer> mp = new HashMap<String, Integer>();
		
		System.out.println("Length of LCS is" + " " + lcs.lcs(X, Y, m, n, mp));
		
	}

}
