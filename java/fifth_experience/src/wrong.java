
import java.util.*;
public class wrong {

	private int a=1;
	private int b=2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		try 
		{
			wrong t2 = null;
			System.out.println(t2.a);
		}
		catch(NullPointerException ne)
		{
			System.out.println(ne);
			System.out.println("����һ����ָ���쳣");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("����һ��δ֪�����쳣");
		}
		finally
		{
			System.out.println("�쳣�������");
		}
		
		
		try 
		{
			int b = 12, c;
	         c = b / 1;
	         System.out.println("c=" + c);
	         c = b / 0; //�����쳣���������к���䣬ִ��catch��finally��
	         System.out.println("c=" + c);
		}
		catch(ArithmeticException ae)
		{
			System.out.println(ae);
			System.out.println("����һ������쳣");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("����һ��δ֪�����쳣");
		}
		finally
		{
			System.out.println("�쳣�������");
		}
		
		
		try 
		{
			int[] arr = {1, 2, 3};
	        for (int i = 0; i <= arr.length; i++) {
	            System.out.println(arr[i]);
	        }
		}
		catch(ArrayIndexOutOfBoundsException abe)
		{
			System.out.println(abe);
			System.out.println("����һ������Խ���쳣");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("����һ��δ֪�����쳣");
		}
		finally
		{
			System.out.println("�쳣�������");
		}
		
		try 
		{
			String string ="������";
			string.charAt(9);
		}
		catch(StringIndexOutOfBoundsException sbe)
		{
			System.out.println(sbe);
			System.out.println("����һ���ַ��������±�Խ���쳣");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("����һ��δ֪�����쳣");
		}
		finally
		{
			System.out.println("�쳣�������");
		}
		
		try 
		{
			String string =null;
			int t = Integer.valueOf(string);
		}
		catch(NumberFormatException nfe)
		{
			System.out.println(nfe);
			System.out.println("����һ�����ָ�ʽ�쳣");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("����һ��δ֪�����쳣");
		}
		finally
		{
			System.out.println("�쳣�������");
		}
		
		try 
		{
			System.out.println("����������������");
			int a = cin.nextInt();
			int b = cin.nextInt();
			System.out.println(a+b);
		}
		catch(InputMismatchException ime)
		{
			System.out.println(ime);
			System.out.println("����һ�������쳣");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("����һ��δ֪�����쳣");
		}
		finally
		{
			System.out.println("�쳣�������");
		}
		
		try 
		{
			Animal a = new Cat();
			a.eat();
			Dog b = (Dog)a;
			b.eat();
		}
		catch(ClassCastException cae)
		{
			System.out.println(cae);
			System.out.println("����һ��������ǿ��ת��Ϊ����ʵ���������쳣");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("����һ��δ֪�����쳣");
		}
		finally
		{
			System.out.println("�쳣�������");
		}
	}
}

abstract class Animal {
	abstract void eat();
}

class Cat extends Animal {
	public void eat()
	{
		System.out.println("chibaba");
	}
}

class Dog extends Animal {
	public void eat()
	{
		System.out.println("chibaba");
	}
}
