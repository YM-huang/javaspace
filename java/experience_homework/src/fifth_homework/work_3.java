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
			set.remove(i);//����ֵi�Ӽ������Ƴ�
			list.remove(i);//����i���Ƴ����Ƴ�һ��֮��2�ͱ����1��û���ܿ�
		}
		System.out.println(set+" "+list);
	}

}
