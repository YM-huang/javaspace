package ConstructNFA;

import java.util.LinkedList;

public class stack {
    private LinkedList<Object> a = new LinkedList<Object>();

    public void push(Object o) {
        a.addFirst(o);
    }

    public Object pop() {
        return a.removeFirst();
    }

    public Object peek() {
        return a.getFirst();
    }
    
    public Object[] toArray() {

        return a.toArray();
    }
    
    public boolean empty() {

        return a.isEmpty();
    }
    
    public int length() {

        return a.toArray().length;
    }
    
    public void clean() {
	    Object[] result = a.toArray();
	    for(int j = 0;j < result.length-1; j++) {
//	    	System.out.println(result[j]);
	    	a.pop();
	    }
//	    System.out.println("----------clean---------");
    }
}