
package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPermsComboAnyLengths {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "abc";
		List<String> l = new ArrayList<String>();
		allPerms("",s,3,3,l);
		//Result Count = 6 [abc, acb, bac, bca, cab, cba]
		System.out.println(l.size()+"\n"+l);
		l.clear();
		allPerms("",s,2,3,l);
		//Result Count = 12 [ab, abc, ac, acb, ba, bac, bc, bca, ca, cab, cb, cba]
		System.out.println(l.size()+"\n"+l);
		l.clear();
		
		s = "aab";
		Set<String> set = new HashSet<String>();
		allCombos("",s,3,3,set);
		//Result Count = 6 [abc, acb, bac, bca, cab, cba]
		System.out.println(set.size()+"\n"+set);
		set.clear();
		allCombos("",s,2,3,set);
		//Result Count = 12 [ab, abc, ac, acb, ba, bac, bc, bca, ca, cab, cb, cba]
		System.out.println(set.size()+"\n"+set);
		l.clear();
		
	}

	static void allPerms(String prefix, String word, int min, int max, List<String> result) {
		//ensures that only those that fit length criteria get added to result
		//List will accept duplicates
		if ( prefix.length()>=min && prefix.length()<=max) 
			result.add(prefix);
		for(int i = 0; i < word.length(); i++)
		{
			String p1 = prefix + word.charAt(i);
            		String p2 = word.substring(0, i) + word.substring(i+1, word.length());
			allPerms(p1,p2,min, max,result);
		}	
	}
	static void allCombos(String prefix, String word, int min, int max, Set<String> result) {
		//ensures that only those that fit length criteria get added to result
		//Set enforces uniqueness
		if ( prefix.length()>=min && prefix.length()<=max) 
			result.add(prefix);
		for(int i = 0; i < word.length(); i++)
		{
			String p1 = prefix + word.charAt(i);
            		String p2 = word.substring(0, i) + word.substring(i+1, word.length());
            		allCombos(p1,p2,min, max,result);
		}	
	}
}
