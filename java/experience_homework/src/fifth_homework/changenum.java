package fifth_homework;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
public class changenum <E extends Comparable<E>>{

	Stack<E> stack1=new Stack<E>();  
    Stack<E> stackMin=new Stack<E>();//存放求最小值的栈  
    Stack<E> stackMax=new Stack<E>();//存放求最大值的栈  
    public void push(E e)  
    {  
        stack1.push(e);   

        if(stackMin.isEmpty()||e.compareTo(stackMin.peek())<0)  
            stackMin.push(e);//若最小栈为空push进stack时就同时把它push进stackMin;  
        else if(e.compareTo(stackMin.peek())>0)  
            stackMin.push(stackMin.peek());  

        if(stackMax.isEmpty()||e.compareTo(stackMin.peek())>0)  
            stackMax.push(e);  
        else if(e.compareTo(stackMax.peek())<0)  
            stackMin.push(stackMax.peek());  
    }  
    public E pop()//一定要记着，非空才能pop()  
    {  
    	if(!stack1.isEmpty())
		{
			return stack1.pop();
		}
    	return null;

    }  
    public E getMin()  
    {  
        return  stackMin.peek();  
    }  
    public E getMax()  
    {  
        return stackMax.peek();  
    }  
    public boolean isEmpty()
	{
		return stack1.isEmpty();
	}
    public E getMed()  
    {  
    	return stack1.peek(); 
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		changenum mystack = new changenum();
		while(cin.hasNext())
		{
			int num = cin.nextInt();
			mystack.push(num);
			System.out.println(num);
		}
		System.out.println(mystack.getMax());
		while(!mystack.isEmpty())
		{
			if(mystack.getMin().compareTo(mystack.getMed())!=0||mystack.getMax().compareTo(mystack.getMed())!=0)
			{
				System.out.println(mystack.pop());
			}
		}
		System.out.println(mystack.getMin());
		cin.close();
	}

}
