package fifth_homework;

import java.util.Scanner;
import java.util.LinkedList;
public class teststack {

	LinkedList linkList = new LinkedList<Object>();
	LinkedList linkListMin = new LinkedList<Object>();
	LinkedList linkListMax = new LinkedList<Object>();
	public void push(int obj)
	{
		linkList.addFirst(obj);
	}
	public boolean isEmpty()
	{
		return linkList.isEmpty();
	}
	public Object pop()
	{
		if(!linkList.isEmpty())
		{
			return linkList.removeFirst();
		}
		return "Õ»ÄÚÎÞÔªËØ";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		teststack mystack = new teststack();
		while(cin.hasNext())
		{
			int num = cin.nextInt();
			mystack.push(num);
			System.out.println(num);
		}
		while(!mystack.isEmpty())
		{
			System.out.println(mystack.pop());
		}
		cin.close();
	}
	

}
