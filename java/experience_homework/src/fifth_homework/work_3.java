package fifth_homework;
import java.util.*;
public class work_3 {

	public static void main(String args[])
	{
		Set<Integer> set=new TreeSet<Integer>();
		List<Integer> list=new ArrayList<Integer>();
		for (int i=-3;i<3;i++)
		{
			set.add(i);
			list.add(i);
		}
		for (int i=0;i<3;i++)
		{
			set.remove(i);//将数值i从集合中移除
			list.remove(i);//将第i个移除，移除一个之后2就变成了1，没错，很坑
		}
		System.out.println(set+" "+list);
	}

}
