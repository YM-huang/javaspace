package unsigned_number;

import java.util.Scanner;

public class unsigned_number {
	static int w=0;//整数累加器（初值为0）
	static int p=0;//指数累加器（初值为0）
	static int n=0;//十进小数累加器（初值为0）
	static int e=1;//十进指数的符号（初值为1， 遇负号为-1）
	
	//判断是否为无符号数
	static boolean isnum(String num){
	    
	    int state = 0;
	    int i=0;
	    for(;i<num.length();i++){
	        char temp = num.charAt(i);
	        state = next_state(state, temp);
	        if(state==-1)
	            return false;
	    }
	    //没有到达终态1、2、6
	    if(state==3||state==4||state==5)
	        return false;
	    return true;
	}
	
	//获取下一个状态及中间量计算结果
	static int next_state(int currentstate,char temp){
	    int next=-1;
	    
	    switch (currentstate) {
	        case 0:{
	            if(Character.isDigit(temp)){
	                n=0;
	                p=0;
	                e=1;
	                w=(temp-'0');
	                next=1;
	            }
	            else if(temp=='.'){
	                n=0;
	                p=0;
	                e=1;
	                w=0;
	                next=3;
	            }
	            else
	                next=-1;
	            break;
	        }
	        
	        case 1:{
	            if(Character.isDigit(temp)){
	                w=w*10+(temp-'0');
	                next=1;
	            }
	            else if(temp=='.'){
	                next=2;
	            }
	            else if(temp=='E'||temp=='e'){
	                next=4;
	            }
	            else
	                next=-1;
	            break;
	        }
	            
	        case 2:{
	            if(Character.isDigit(temp)){
	                w=w*10+(temp-'0');
	                n=n+1;
	                next=2;
	            }
	            else if(temp=='E'||temp=='e'){
	                next=4;
	            }
	            else
	                next=-1;
	            break;
	        }
	            
	        case 3:{
	            if(Character.isDigit(temp)){
	                n=n+1;
	                w=w*10+(temp-'0');
	                next=2;
	            }
	            else
	                next=-1;
	            break;
	        }
	            
	        case 4:{
	            if(Character.isDigit(temp)){
	                p=p*10+(temp-'0');
	                next=6;
	            }
	            else if(temp=='+'){
	                next=5;
	            }
	            else if(temp=='-'){
	                e=-1;
	                next=5;
	            }
	            else
	                next=-1;
	            break;
	        }
	            
	        case 5:{
	            if(Character.isDigit(temp)){
	                p=p*10+(temp-'0');
	                next=6;
	            }
	            else
	                next=-1;
	            break;
	        }
	        
	        case 6:{
	            if(Character.isDigit(temp)){
	                p=p*10+(temp-'0');
	                next=2;
	            }
	            else
	                next=-1;
	            break;
	        }
	    }
	    return next;
	}
	
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()){
	        String num;
	        num = cin.next();
	        System.out.println("input:"+num);
	    
	        if(isnum(num)){
	        	System.out.print("it is an unsigned number～\n");
	            if(e*p-n!=0&&w!=0)
	            	System.out.println("the number is:"+w+"E"+(e*p-n));
	            else
	            	System.out.println("the number is:"+w);
	            
	        }
	        else{
	        	System.out.println("it isn't an unsigned number!");
	        }
	    }
		cin.close();
	}
}
