package ConstructNFA;

import java.util.Scanner;

public class ConstructNFA {
	static stack buffstack1 = new stack();
	static stack buffstack2 = new stack();
	static stack buffstack3 = new stack();
	static stack outputstack = new stack();
	static int statusId = 0;
	static int leftsum = 0;
	static int starsum = 0;
	static int rightsum = 0;
	static int ts = 0;//特殊值
	public static void main(String[] args) {
		String inputD = "";//正规式左部分
		String inputR = "";//正规式右部分
		Scanner input = new Scanner(System.in);
		System.out.println("=============请输入正规式=============");
		System.out.println("=============先输入左部，再输入右部，右部按()区分=============");
		inputD = input.next();
		inputR = input.next();
		System.out.println("=============输入结束=============");
	    constructToNFA(inputR);
	}

	private static void printNFA(stack outputstack) {
		System.out.println("=============结果输出=============");
		Object[] array = outputstack.toArray();
		for(int i = 0;i < array.length;i++)
	    {
	    	struct node = (struct)array[i];
	    	System.out.println(node.id+"--["+node.aChar+"]-->"+node.nextId);
	    }
	}
	
	private static void constructToNFA(String inputR) {
	    buffstack3.push('$');
	    buffstack2.push('$');
	    for(int i = 0;i < inputR.length();i++) {
	        char ch = inputR.charAt(i);
	        if(ch == '(') {
	        	leftsum++;
	        }
	        else if(ch == '*') {
	        	starsum++;
	        }
	        else if(ch == ')') {
	        	rightsum++;
	        }
	    }
	    if(leftsum == rightsum) {
		    judgeBracket(inputR);
		    buffstack1.clean();
		    buffstack3.clean();
		    judgeStar();
		    buffstack2.clean();
		    putNFA();
		    printNFA(outputstack);
	    }
	    else {
	    	System.out.println("输入有误！");
	    }
	}

	private static void putNFA() {
		Object[] result = buffstack3.toArray();
	    for(int i = 0;i < result.length-1; i++) {
	    	String str = (String)result[i];
	    	if(str.length() == 1) {
	    		char ch = str.charAt(0);
		    	if(ch != '$') {
	                outputstack.push(new struct(statusId, statusId+1, ch));
	                statusId = statusId + 1;
	            }
	    	}
	    	else {
	    		//a|b
	    		if(str.indexOf("|")!=-1 && str.indexOf("*")==-1) {
	    			int j = 0;
	    			outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    			outputstack.push(new struct(statusId, statusId+3, 'ε'));
	    			statusId = statusId + 1;
	    	        outputstack.push(new struct(statusId, statusId+1, str.charAt(j)));
	    	        statusId = statusId + 1;
	    	        outputstack.push(new struct(statusId, statusId+3, 'ε'));
	    	        statusId = statusId + 1;
	    	        outputstack.push(new struct(statusId, statusId+1, str.charAt(j+2)));
	    	        statusId = statusId + 1;
	    	        outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    	        statusId = statusId + 1;
	    		}
	    		else if(str.indexOf("|")!=-1 && str.indexOf("*")!=-1) {
	    			//()*
	    			if(str.indexOf(")")!=-1) {
	    				int huo = 0;
	    				for(int j = 0;j < str.length();j++) {
	    					char ch = str.charAt(j);
		    				if(ch == '|') {
		    					huo = j;
		    				}
	    				}
	    				//(a|b)*
	    				outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    			    outputstack.push(new struct(statusId, statusId+7, 'ε'));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    			    outputstack.push(new struct(statusId, statusId+3, 'ε'));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+1, str.charAt(huo-1)));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+3, 'ε'));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+1, str.charAt(huo+1)));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId-5, 'ε'));
	    			    outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    			    statusId = statusId + 1;
	    			}
	    			//a|b*
	    			else {
	    				int j = 0;
	    				outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    			    outputstack.push(new struct(statusId, statusId+3, 'ε'));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+1, str.charAt(j)));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+5, 'ε'));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    			    outputstack.push(new struct(statusId, statusId+3, 'ε'));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+1, str.charAt(j+2)));
	    			    statusId = statusId + 1;
	    			    outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    			    outputstack.push(new struct(statusId, statusId-1, 'ε'));
	    			    statusId = statusId + 1;
	    			}	
	    		}
	    		else if(str.indexOf("|")==-1 && str.indexOf("*")!=-1) {
	    			//(ab)*
	    			if(str.indexOf(")")!=-1) {
	    				outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    				outputstack.push(new struct(statusId, statusId+str.length()-1, 'ε'));//str.length()-2-1+1+1
			            statusId = statusId + 1;
	    				for(int j = 1;j < str.length()-2;j++) {
	    					char ch = str.charAt(j);
	    					outputstack.push(new struct(statusId, statusId+1, ch));
				            statusId = statusId + 1;
	    				}
	    				outputstack.push(new struct(statusId, statusId-(str.length()-3), 'ε'));//str.length()-2-1
			            outputstack.push(new struct(statusId, statusId+1, 'ε'));
			            statusId = statusId + 1;
	    			}
	    			//a*
	    			else {
	    				outputstack.push(new struct(statusId, statusId+1, 'ε'));
	    				outputstack.push(new struct(statusId, statusId+3, 'ε'));
			            statusId = statusId + 1;
	    				outputstack.push(new struct(statusId, statusId+1, str.charAt(0)));
				        statusId = statusId + 1;
	    				outputstack.push(new struct(statusId, statusId-1, 'ε'));
			            statusId = statusId + 1;
			            outputstack.push(new struct(statusId, statusId+1, 'ε'));
			            statusId = statusId + 1;
	    			}
	    		}
	    	}
	    }
	}

	private static void judgeBracket(String inputR) {
		String t = inputR;	
	    for(int i = 0;i < inputR.length();i++) {
	    	if(leftsum > 0) {
		        char ch = inputR.charAt(i);
		        switch (ch)
		        {
		            case '(':
		            {
		            	buffstack1.push(i);//放入(的序号
		                break;
		            }
		            case ')':
		            {
		            	ts = 0;
		            	if(i != inputR.length()-1) {
		            		char ch2 = inputR.charAt(i+1);
		        	        if(ch2 == '*') {
		        	        	char ch3 = (char) buffstack3.peek();
		        	        	String str3 = ch3 + "*";
		        	        	buffstack3.pop();
		        	        	buffstack3.push(str3);
		        	        	ts = 1;
		        	        }
		            	}
		            	int startId = (int) buffstack1.pop();
		            	int endId = i;
		            	String str = "";
		            	for(int j = startId+1;j < endId;j++) {
		        	        char ch1 = t.charAt(j);
		        	        buffstack3.pop();
		        	        str += ch1;
		        	        
		            	}
		            	if(ts == 1) {
		            		str = "(" + str +")*";
		            		buffstack2.push(str);
		            	}
		            	else if(str.indexOf("|")!=-1) {
		            		buffstack2.push(str);
		            	}
		            	else {
		            		for(int j = endId-1;j > 0;j--) {
			        	        char ch1 = t.charAt(j);
			        	        buffstack2.push(ch1);
		            		}
		            	}
		            	leftsum--;
		            	String t1 = "";
		            	//(前
		            	for(int j = 0;j < startId;j++) {
		        	        char ch1 = t.charAt(j);
		        	        t1 += ch1;
		            	}
		            	//)后
		            	int t2 = 1;
		            	for(int j = endId+1;j < t.length();j++) {
		        	        char ch1 = t.charAt(j);
		        	        if(ch1 == '*' && t2 ==1) {
		        	        	t2 = 0;
		        	        }
		        	        t1 += ch1;
		            	}
		            	t = t1;
		            	if(t != "") {
		            		judgeBracket(t);
		            	}
		            	else {
		            		break;
		            	}
		            	break;
		            }
		            default:
		            {
		            	buffstack3.push(ch);
		                break;
		            }
		        }
	    	}
		}
	}

	private static void judgeStar() {
		Object[] result = buffstack2.toArray();
	    for(int i = 0;i < result.length-1; i++) {
	    	
	    	String str = "";
	    	str += result[i];
	    	starsum = 0;
	    	for(int k = 0;k < str.length();k++) {
		        char ch = str.charAt(k);
		        if(ch == '*') {
		        	starsum++;
		        }
	    	}
	    	if(str.indexOf("*")!=-1) { 		
            	if(str.indexOf(")")!=-1) {
        	        buffstack3.push(str);
            	}
            	else {
	    		String str1 = "";
	    		for(int j = 0;j < str.length();j++) {
	    			char ch = str.charAt(j);
	    			if(ch != '*') {
	    				buffstack3.push(ch);
	    				str1 = ch + "";
	    			}
	    			else {
	    				starsum--;
	    				buffstack3.pop();
	    				str1 += ch;
	    				buffstack3.push(str1);
	    				str1 = "";
	    			}
	    		}
	    		buffstack2.pop();
	    		}
	    	}
	    	else {
	    		buffstack2.pop();
	    		buffstack3.push(str);
	    	}
	    }
	}
}
