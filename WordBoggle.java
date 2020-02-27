package algorithms;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
 * Sample Input
 * 
2 <-- Nbr of testcases
5 <-- Nbr of Dic words
GEEKS FOR QUIZ GO SEEK <-- the actual words in dic
3 3 <-- dimension of word boggle
G I Z U E K Q S E <-- rearrange into 3 by 3 char array
GEEKS QUIZ SEEK <-- answer printed by below code
6 <-- next test case's dic word count
EBD C BFD E EEC F <-- dic words
2 5 <-- boggle word array dim
E E E E C B F E E E <---rearrange into 2X5
C E EEC F  <-- answer
 */

public class WordBoggle {
	static Set<String> dictionary = new HashSet<String>();
    static Set<String> foundWords = new HashSet<String>();
    static int maxLen = -1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String[] words = { "GEEKS", "FOR", "QUIZ", "GO" };
		Scanner sc = new Scanner(System.in);
		int numTests = sc.nextInt();
		for (int i = 0; i < numTests; i++) {
			int numWords = sc.nextInt();
			dictionary.clear();
			foundWords.clear();
			maxLen = -1;
			readNWords(numWords, sc);
			int n = sc.nextInt();
			int m = sc.nextInt();
			char[][] boggle = readCharArray(n,m, sc);
			findWords(boggle);
			if ( foundWords.isEmpty() ) {
				System.out.println(-1);return;
			}
			TreeSet<String> treeSet = new TreeSet<String>(foundWords);
			treeSet.iterator().forEachRemaining(x -> System.out.print(x+" "));
			System.out.println();
		}
	}

	static char[][] readCharArray(int n, int m, Scanner sc) {
		
		char[][] boggle = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				boggle[i][j] = sc.next().charAt(0);
			}
		}
		return boggle;

	}

	static void readNWords(int numWords, Scanner sc) {
		for (int i = 0; i < numWords; i++) {
			String wd = sc.next();
			dictionary.add(wd);
			if (wd.length()>maxLen) {
				maxLen = wd.length();
			}
		}
	}

	static void findWords(char[][] boggle) {
		for (int i = 0; i < boggle.length; i++) {
			for (int j = 0; j < boggle[i].length; j++) {
				boolean[][] alreadySeen = new boolean[boggle.length][boggle[0].length];
				alreadySeen[i][j] = true;
				if (dictionary.contains(String.valueOf(boggle[i][j]))) {
					foundWords.add(String.valueOf(boggle[i][j]));
				}
				findWordStartingAt(String.valueOf(boggle[i][j]), boggle, i, j, alreadySeen);
			}
		}

	}

	static void findWordStartingAt(String startWith, char[][] boggle, int i_idx, int j_idx, boolean[][] alreadySeen) {
		// we have 8 directions to go
		// i+1, i -1, j+1, j-1 and (i+1,j+1), (i-1,j-1), (i+1,j-1), ( i-1,j+1)
		// loop from i_idx -1 to i_idx+1 and j_idx -1 to j_idx + 1
		// skip over when i matches i_idx and j matches j_idx and alreadySeen
		for (int i = i_idx - 1; i <= i_idx + 1; i++) {
			if (i < 0 || i >= boggle.length)
				continue;

			for (int j = j_idx - 1; j <= j_idx + 1; j++) {
				if (j < 0 || j >= boggle[i].length)
					continue;
				if (i == i_idx && j == j_idx)
					continue;
				if (alreadySeen[i][j])
					continue;
				String newWord = startWith + boggle[i][j];
				if ( newWord.length() > maxLen ) return;
				alreadySeen[i][j] = true;
				if (dictionary.contains(newWord)) {
					//System.out.println("Formed word:" + newWord);
					foundWords.add(newWord);
				}
				// continue searching for more words
				findWordStartingAt(newWord, boggle, i, j, alreadySeen);
				// everytime you back out of a call...reset the alreadySeen
				alreadySeen[i][j] = false;
			}
		}
	}
}
