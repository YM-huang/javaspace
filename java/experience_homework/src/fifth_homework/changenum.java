package fifth_homework;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
public class changenum <E extends Comparable<E>>{

	Stack<E> stack1=new Stack<E>();  
    Stack<E> stackMin=new Stack<E>();//�������Сֵ��ջ  
    Stack<E> stackMax=new Stack<E>();//��������ֵ��ջ  
    public void push(E e)  
    {  
        stack1.push(e);   

        if(stackMin.isEmpty()||e.compareTo(stackMin.peek())<0)  
            stackMin.push(e);//����СջΪ��push��stackʱ��ͬʱ����push��stackMin;  
        else if(e.compareTo(stackMin.peek())>0)  
            stackMin.push(stackMin.peek());  

        if(stackMax.isEmpty()||e.compareTo(stackMin.peek())>0)  
            stackMax.push(e);  
        else if(e.compareTo(stackMax.peek())<0)  
            stackMin.push(stackMax.peek());  
    }  
    public E pop()//һ��Ҫ���ţ��ǿղ���pop()  
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
