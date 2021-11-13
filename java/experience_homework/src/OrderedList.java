import java.util.Random;

public class OrderedList {
	
	int n;                //��ǰ������Ԫ�صĸ���
    int MaxLength;            //���������Ԫ�صĸ���

    int[] value;            //�洢������Ԫ�ص�����
    int[] link;              //ָ������
    Random rnd = new Random();
    
    int Small;              //������Ԫ�ص��½�
    int TailKey;            //������Ԫ�ص��Ͻ�  

    OrderedList(int small, int Large, int MaxL)
    {
	      MaxLength = MaxL;
	      value = new int[MaxLength+1];
	      link = new int[MaxLength+1];
	      TailKey = Large;
	      n = 0;
	      link[0] = 0;
	      value[0] = TailKey;
	      Small = small;
    }

    //�����Ƿ������λ��
    boolean ifSearch(int x , int index)
    {
	    	index = 0;
	    	int max = Small;
	    	int m = (int) Math.floor(Math.sqrt(Double.valueOf(n)));
	    	for(int i = 1;i <= m;i++)
	    	{
	    		int j = rnd.nextInt(n)+1;
	    		int y = value[j];
	    		if((max<y)&&(y<x))
	    		{
	    			max = y;
	    			index = j;
	    		}
	    	}
	    	//˳������
			while(value[link[index]]<x)
			{
				index = link[index];
			}
			
			return(value[link[index]] == x);		
    }
    
    //��������λ��
    int Searchidx(int x , int index)
    {
	    	index = 0;
	    	int max = Small;
	    	int m = (int) Math.floor(Math.sqrt(Double.valueOf(n)));
	    	for(int i = 1;i <= m;i++)
	    	{
	    		int j = rnd.nextInt(n)+1;
	    		int y = value[j];
	    		if((max<y)&&(y<x))
	    		{
	    			max = y;
	    			index = j;
	    		}
	    	}
	    	//˳������
			while(value[link[index]]<x)
			{
				index = link[index];
			}
			
			return(index);		
    }
    
    void Insert(int k){
    	  if((n == MaxLength)||(k>=TailKey)){
    	    return;
    	  }
    	  int index = 1;
    	  if(!ifSearch(k, index)){
    		    value[++n] = k;
    		    link[n] = link[Searchidx(k,index)];
    		    link[Searchidx(k,index)] = n;
    	  }

    	  
    }
    
    //�������Ԫ��
    int SearchLast()
    {
	      int index = 0;
	      int x = value[n];
	      int max = Small;
	      //�����ȡ����Ԫ�ش���
	      int m = (int) Math.floor(Math.sqrt(Double.valueOf(n)));
	
	      for(int i=1; i<=m; i++)
	      {
	        //�����������Ԫ��λ��
	    	int j = rnd.nextInt(n)+1;
	  		int y = value[j];
	
	        if((max<y)&&(y<x))
	        {
	          max = y;
	          index = j;
	        }
	      }
	      
	      //˳������
	      while(link[index]!=0)
	      {
	        index = link[index];
	      }
	      return index;
    }
    
    //��Сֵ
    int SearchFirst()
    {
	      int index = 0;
	      return index;
    }

    void Delete(int k){
    	  if((n==0)&&(k>=TailKey)) { return;  }
    	  int index = 1;
    	  if(ifSearch(k, index)){
    	    int p = link[Searchidx(k,index)];
    	    if(p == n){
    	      link[Searchidx(k,index)] = link[p];
    	    }else{
    	      if(link[p]!=n){
    	        int q = SearchLast();
    	        link[q] = p;
    	        link[Searchidx(k,index)] = link[p];
    	      }
    	      value[p] = value[n];//ɾ��Ԫ�������Ԫ�����
    	      link[p] = link[n];
    	    }
    	    n--;
    	  }
    }
    
    //��������λ��ǰ���ڵ�
    int predeceessor(int x , int index)
    {
	    	index = 0;
	    	int max = Small;
	    	int m = (int) Math.floor(Math.sqrt(Double.valueOf(n)));
	    	for(int i = 1;i <= m;i++)
	    	{
	    		int j = rnd.nextInt(n)+1;
	    		int y = value[j];
	    		if((max<y)&&(y<x))
	    		{
	    			max = y;
	    			index = j;
	    		}
	    	}
	    	//˳������
			while(value[link[index]]<x-1)
			{
				index = link[index];
			}
			
			return(index);		
    }
    
    //��������λ�ú�̽ڵ�
    int successor(int x , int index)
    {
	    	index = 0;
	    	int max = Small;
	    	int m = (int) Math.floor(Math.sqrt(Double.valueOf(n)));
	    	for(int i = 1;i <= m;i++)
	    	{
	    		int j = rnd.nextInt(n)+1;
	    		int y = value[j];
	    		if((max<y)&&(y<x))
	    		{
	    			max = y;
	    			index = j;
	    		}
	    	}
	    	//˳������
			while(value[link[index]]<x+1)
			{
				index = link[index];
			}
			
			return(index);		
    }

    public static void main(String[] args)
    {
    	
    }
    
}

