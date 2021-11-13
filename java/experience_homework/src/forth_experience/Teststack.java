package forth_experience;

import java.util.LinkedList;
import java.util.Scanner;
public class Teststack {	
	LinkedList linkList = new LinkedList<Object>();
	public void push(Object obj) {
		linkList.addFirst(obj);
	}
	public boolean isEmpty() {
		return linkList.isEmpty();
	}
	public void clear() {
		linkList.clear();
	}
	//�Ƴ������ش��б�ĵ�һ��Ԫ��
	public Object pop() {
		if (!linkList.isEmpty())
			return linkList.removeFirst();
		return "ջ����Ԫ��";
	}
	public int getSize() {
		return linkList.size();
	}
	public Object pp() {
		if (!linkList.isEmpty())
			return linkList.getFirst();
		return "E";
	}
	public static void main(String[] args) {
		Teststack myStack = new Teststack ();
//		myStack.push(2);
//		myStack.push(3);
//		myStack.push(4);
//		System.out.println(myStack.pop());//���4
//		System.out.println(myStack.pop());//���3
		Scanner cin = new Scanner(System.in);
		int n = 0;
		while(cin.hasNext())
		{
			n = cin.nextInt();
			for(int i =0;i<n;i++)
			{
				String t = cin.next();
				if(t.compareTo("A")==0)
				{
					System.out.println(myStack.pp());
					
				}
				if(t.compareTo("O")==0)
				{
					myStack.pop();
				}
				if(t.compareTo("P")==0)
				{
					int m = cin.nextInt();
					myStack.push(m);
				}
			}
			System.out.println();
			myStack.clear();
		}
		cin.close();
	}
}

