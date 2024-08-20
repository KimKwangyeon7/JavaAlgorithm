import java.util.*;
class Solution {
    public String solution(String p) {
        //String answer = "";
        return split(p);
    }
    
    private static String change(String str) {
    	int len = str.length();
    	String s = str.substring(1, len-1);
    	String a = "";
    	for (int i = 0; i < len-2; i++) {
    		if (s.charAt(i) == '(') {
    			a += ")";
    		}
    		if (s.charAt(i) == ')') {
    			a += "(";
    		}
    	}
    	return a;
    }
    
    private static boolean isRight(String str) {
    	Stack<Character> left = new Stack<>();
    	int len = str.length();
    	
    	for (int i = 0; i < len; i++) {
    		if (str.charAt(i) == '(') {
    			left.push(str.charAt(i));
    		} else if (str.charAt(i) == ')') {
    			if (left.isEmpty()) {
    				return false;
    			} else {
    				left.pop();
    			}
    		}
    	}
    	if (left.isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private static String split(String str) {
    	if (isRight(str)) {
    		return str;
    	}
    	int left = 0;
    	int right = 0;
    	int len = str.length();
    	int idx = -1;
    	for (int i = 0; i < len; i++) {
    		if (str.charAt(i) == '(') {
    			left++;
    		} else if (str.charAt(i) == ')') {
    			right++;
    		}
    		if (left != 0 && left == right) {
    			idx = i;
    			break;
    		}
    	}
    	
    	String u = "";
    	String v = "";
    	if (idx == str.length()-1) { 
    		u = str;
    	} else {
    		u = str.substring(0, idx+1);
        	v = str.substring(idx+1);
    	}
    	
    	if (isRight(u)) {
    		return u + split(v);
    	} else {
    		return "(" + split(v) + ")" + change(u);
    	}
    	
    }
}