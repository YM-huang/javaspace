package fifth_experience;
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
			System.out.println("捕获一个空指针异常");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("捕获一个未知类型异常");
		}
		finally
		{
			System.out.println("异常处理结束");
		}
		
		
		try 
		{
			int b = 12, c;
	         c = b / 1;
	         System.out.println("c=" + c);
	         c = b / 0; //发生异常则跳过该行后语句，执行catch或finally块
	         System.out.println("c=" + c);
		}
		catch(ArithmeticException ae)
		{
			System.out.println(ae);
			System.out.println("捕获一个零除异常");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("捕获一个未知类型异常");
		}
		finally
		{
			System.out.println("异常处理结束");
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
			System.out.println("捕获一个数组越界异常");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("捕获一个未知类型异常");
		}
		finally
		{
			System.out.println("异常处理结束");
		}
		
		try 
		{
			String string ="我是猪";
			string.charAt(9);
		}
		catch(StringIndexOutOfBoundsException sbe)
		{
			System.out.println(sbe);
			System.out.println("捕获一个字符串访问下标越界异常");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("捕获一个未知类型异常");
		}
		finally
		{
			System.out.println("异常处理结束");
		}
		
		try 
		{
			String string =null;
			int t = Integer.valueOf(string);
		}
		catch(NumberFormatException nfe)
		{
			System.out.println(nfe);
			System.out.println("捕获一个数字格式异常");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("捕获一个未知类型异常");
		}
		finally
		{
			System.out.println("异常处理结束");
		}
		
		try 
		{
			System.out.println("请输入两个整数：");
			int a = cin.nextInt();
			int b = cin.nextInt();
			System.out.println(a+b);
		}
		catch(InputMismatchException ime)
		{
			System.out.println(ime);
			System.out.println("捕获一个输入异常");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("捕获一个未知类型异常");
		}
		finally
		{
			System.out.println("异常处理结束");
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
			System.out.println("捕获一个将对象强制转换为不是实例的子类异常");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("捕获一个未知类型异常");
		}
		finally
		{
			System.out.println("异常处理结束");
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
