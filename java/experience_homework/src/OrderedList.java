import java.util.Random;

public class OrderedList {
	
	int n;                //当前集合中元素的个数
    int MaxLength;            //集合中最大元素的个数

    int[] value;            //存储集合中元素的数组
    int[] link;              //指针数组
    Random rnd = new Random();
    
    int Small;              //集合中元素的下界
    int TailKey;            //集合中元素的上界  

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

    //返回是否有这个位置
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
	    	//顺序搜索
			while(value[link[index]]<x)
			{
				index = link[index];
			}
			
			return(value[link[index]] == x);		
    }
    
    //返回所在位置
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
	    	//顺序搜索
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
    
    //查找最大元素
    int SearchLast()
    {
	      int index = 0;
	      int x = value[n];
	      int max = Small;
	      //随机抽取数组元素次数
	      int m = (int) Math.floor(Math.sqrt(Double.valueOf(n)));
	
	      for(int i=1; i<=m; i++)
	      {
	        //随机产生数组元素位置
	    	int j = rnd.nextInt(n)+1;
	  		int y = value[j];
	
	        if((max<y)&&(y<x))
	        {
	          max = y;
	          index = j;
	        }
	      }
	      
	      //顺序搜索
	      while(link[index]!=0)
	      {
	        index = link[index];
	      }
	      return index;
    }
    
    //最小值
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
    	      value[p] = value[n];//删除元素由最大元素来填补
    	      link[p] = link[n];
    	    }
    	    n--;
    	  }
    }
    
    //返回所在位置前驱节点
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
	    	//顺序搜索
			while(value[link[index]]<x-1)
			{
				index = link[index];
			}
			
			return(index);		
    }
    
    //返回所在位置后继节点
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
	    	//顺序搜索
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

