package algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
/* Uses BFS */
public class WordLadder {
	
	static class MyNode {
		MyNode parentWord;
		String value;
		public MyNode(String s, MyNode p) { this.value = s;this.parentWord=p;}
		public MyNode(String s)           { this.value = s;}
		public String toString() { return this.value;}
	}
	
	static HashSet<String> d = new HashSet<String>();
	//goes 22 levels and fails....cause QUE is a rather unique prefix
	//static String start = "CLASS";
	//static String target = "QUEUE";
	//goes only 2 levels and fails....cause QUEEN-->QUEER is a rather unique prefix
	//static String start = "QUEEN";
	//static String target = "QUEUE";
	static String start =  "MOTHER";
	static String target = "FATTEN";
	//static String start = "PAPA";
	//static String target= "MAMA";

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("/usr/share/dict/words"));//on any unix system
			String line;
			while ((line = br.readLine()) != null)
				d.add(line.toUpperCase());
		} finally {
			br.close();
		}
		try { 
			start = args[0].toUpperCase();
			target = args[1].toUpperCase();
		}
		catch(Exception ignore) {} 
		System.out.println("Find word ladder from "+ start + " to " + target);
		System.out.println("Tree Depth=" + findWords());
	}

	static int findWords() {

		ArrayList<ArrayList<MyNode>> q = new ArrayList<ArrayList<MyNode>>();
		ArrayList<MyNode> nextLevel = new ArrayList<MyNode>();
		nextLevel.add(new MyNode(start));
		q.add(nextLevel);
		HashSet<String> alreadySeen = new HashSet<String>();
		alreadySeen.add(start);
		MyNode found = null;
		do {
			found = inspectNextLevel(q, nextLevel, alreadySeen);
			if (found != null) {
				//print detailed state space search
				//for (ArrayList<MyNode> lvl : q) {
				//	System.out.println(lvl);
				//}
				//print breadcrumbs from target to start
				printPath(found);
				break;
			}
			if (nextLevel != q.get(q.size() - 1)) {
				nextLevel = q.get(q.size() - 1);
			} else
				break;
		} while (true);
		if (found==null) {
			System.out.println("No such word");
			for (ArrayList<MyNode> lvl : q) {
				System.out.println(lvl);
			}
		}
		return q.size();
	}

	private static void printPath(MyNode found) {
		System.out.println("From Target to Start");
		// TODO Auto-generated method stub
		for(int i = 1; found != null ; found = found.parentWord, i++) {
			System.out.println(i+")"+found.value);
		}
	}

	static MyNode inspectNextLevel(ArrayList<ArrayList<MyNode>> q, ArrayList<MyNode> currLevel, HashSet<String> as) {
		ArrayList<MyNode> nextLevel = new ArrayList<MyNode>();
		// for each word in the current Level
		for (MyNode curr : currLevel) {
			// for each position in the array
			char[] cArr = curr.value.toCharArray();
			for (int i = 0; i < cArr.length; i++) {
				char orig = cArr[i];
				// replace A to Z and if word is valid, add to next level
				for (char c = 'A'; c <= 'Z'; c++) {
					if (orig != c) // no point replacing same char
					{
						cArr[i] = c;
						String str = new String(cArr);
						// if new word n valid, add it

						if (!as.contains(str) && d.contains(str)) {
							nextLevel.add(new MyNode(str, curr));
							as.add(str);
							if (str.equals(target)) {

								System.out.println("FOUND it");
								q.add(nextLevel);
								return nextLevel.get(nextLevel.size()-1);// found;
							}
						}
					}

				}
				// restore the char
				cArr[i] = orig;
			}

		}
		// if we added any new words add this level else we are done
		if (!nextLevel.isEmpty())
			q.add(nextLevel);
		return null;// not found and maybe end of search
	}

}
