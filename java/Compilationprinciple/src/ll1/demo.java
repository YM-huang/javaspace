package ll1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class demo {
    public static void main(String[] args) {
        Test test = new Test();
        test.getNvNt();
        test.Init();
        test.createTable();
        test.analyzeLL();
        test.ouput();
    }
}

class Test {
    public HashMap<Character, HashSet<Character>> firstSet = new HashMap<Character, HashSet<Character>>();
    public HashMap<String, HashSet<Character>> firstSetX = new HashMap<String, HashSet<Character>>();
    public Character S = 'E';
    public HashMap<Character, HashSet<Character>> followSet = new HashMap<Character, HashSet<Character>>();
  
    public HashSet<Character> VnSet = new HashSet<Character>();
    public HashSet<Character> VtSet = new HashSet<Character>();
    public HashMap<Character, ArrayList<String>> experssionSet = new HashMap<Character, ArrayList<String>>();
     public String [] inputExperssion = { "E->TK", "K->+TK", "K->~", "T->FM", "M->*FM", "M->~", "F->i", "F->(E)"};
    public String [][] table;  
    public Stack<Character> analyzeStatck = new Stack<Character>();
    public String strInput = "i+i*i$";
    public String action = "";
    int index = 0;
    public void Init() {
        for (String e : inputExperssion) {
            String[] str = e.split("->");
            char c = str[0].charAt(0);
            ArrayList<String> list = experssionSet.containsKey(c) ? experssionSet.get(c) : new ArrayList<String>();
            list.add(str[1]);
            experssionSet.put(c, list);
        }
        for (Character c : VnSet)
            getFirst(c);

        for (Character c : VnSet) {
            ArrayList<String> l = experssionSet.get(c);
            for (String s : l)
                    getFirst(s);
        }
        getFollow(S);
        for (Character c : VnSet) {
            getFollow(c);
        }
    }

    public void getNvNt() {
        for (String e : inputExperssion) {
            String[] str = e.split("->");
            VnSet.add(str[0].charAt(0));
        }
        for (String e : inputExperssion) {
            String[] str = e.split("->");
            String right = str[1];
            for (int i = 0; i < right.length(); i++) 
                if (!VnSet.contains(right.charAt(i)))
                    VtSet.add(right.charAt(i));      
        }
    }

    public void getFirst(Character c) {
        ArrayList<String> list = experssionSet.get(c);
        HashSet<Character> set = firstSet.containsKey(c) ? firstSet.get(c) : new HashSet<Character>();
        // cΪ�ս�� ֱ�����
        if (VtSet.contains(c)) {
            set.add(c);
            firstSet.put(c, set);
            return;
        }
        // cΪ���ս�� ������ÿ������ʽ
        for (String s : list) {
            // c �Ƴ��մ� ֱ�����
            if (s == Character.toString('~')) {
                set.add('~');
            }
            // X -> Y1Y2Y3�� ���
            else {
                // ��������ɨ������ʽ�Ҳ�
                int i = 0;
                while (i < s.length()) {
                    char tn = s.charAt(i);
                    //�ȴ����ֹδ��ʼ��
                    getFirst(tn);
                    HashSet<Character> tvSet = firstSet.get(tn);
                    // ����first��������
                    for (Character tmp : tvSet)
                        set.add(tmp);
                    // �������մ� ������һ������
                    if (tvSet.contains('~'))
                        i++;
                    // �����˳� ������һ������ʽ
                    else
                        break;
                }
            }
        }
        firstSet.put(c, set);
    }

    public void getFirst(String s) {
        HashSet<Character> set = (firstSetX.containsKey(s))? firstSetX.get(s) : new HashSet<Character>();
        // ��������ɨ���ʽ
        int i = 0;
        while (i < s.length()) {
            char tn = s.charAt(i);  
            HashSet<Character> tvSet = firstSet.get(tn);
            // ����ǿ� first��������
            for (Character tmp : tvSet)
                if(tmp != '~')
                    set.add(tmp);
            // �������մ� ������һ������
            if (tvSet.contains('~'))
                i++;
            // �������
            else
                break;
            // ����β�� �����з��ŵ�first���������մ� �ѿմ�����
            if (i == s.length()) {
                set.add('~');
            }
        }
        firstSetX.put(s, set);
    }


    public void getFollow(char c) {
        ArrayList<String> list = experssionSet.get(c);
        HashSet<Character> setA = followSet.containsKey(c) ? followSet.get(c) : new HashSet<Character>();
        //����ǿ�ʼ�� ��� $ 
        if (c == S) {
            setA.add('$');
        }
        //������������в���ʽ��ȷ��c�ĺ�� �ս��
        for (Character ch : VnSet) {
            ArrayList<String> l = experssionSet.get(ch);
            for (String s : l) 
                for (int i = 0; i < s.length(); i++)
                    if (s.charAt(i) == c && i + 1 < s.length() && VtSet.contains(s.charAt(i + 1)))
                        setA.add(s.charAt(i + 1));
        }
        followSet.put(c, setA);
        //����c��ÿһ������ʽ
        for (String s : list) {
            int i = s.length() - 1;
            while (i >= 0 ) {
                char tn = s.charAt(i);
                //ֻ������ս��
                if(VnSet.contains(tn)){
                    // ���� A->��B��  ��ʽ����
                    //���²�����   followA ���� followB
                    //���´��ڣ��Ѧµķǿ�first��  ����followB
                    //���´���  �� first(��)�����մ�   followA ���� followB

                    //���´��� 
                    if (s.length() - i - 1 > 0) {
                        String right = s.substring(i + 1);
                        //�ǿ�first�� ���� followB
                        HashSet<Character> setF = null;
                        if( right.length() == 1){
                        	if(!firstSet.containsKey(right.charAt(0)))
                        		getFirst(right.charAt(0));
                            setF = firstSet.get(right.charAt(0));
						}
                        else{
                            //���ҳ��Ҳ���first��
                            if(!firstSetX.containsKey(right))
                                getFirst(right);
                            setF = firstSetX.get(right);
                        }
                        HashSet<Character> setX = followSet.containsKey(tn) ? followSet.get(tn) : new HashSet<Character>();
                        for (Character var : setF)
                            if (var != '~')
                                setX.add(var);
                        followSet.put(tn, setX);
 
                        // ��first(��)�����մ�   followA ���� followB
                        if(setF.contains('~')){
                            if(tn != c){
                                HashSet<Character> setB = followSet.containsKey(tn) ? followSet.get(tn) : new HashSet<Character>();
                                for (Character var : setA)
                                    setB.add(var);
                                followSet.put(tn, setB);
                             }  
                        }
                     }
                    //���²�����   followA ���� followB
                    else{
                        // A��B��ͬ����� 
                        if(tn != c){
                            HashSet<Character> setB = followSet.containsKey(tn) ? followSet.get(tn) : new HashSet<Character>();
                            for (Character var : setA)
                                setB.add(var);
                            followSet.put(tn, setB);
                         }   
                    }
                    i--;
                }  
                //������ս����ǰ��  �� A->aaaBCDaaaa  ��ʱ��Ϊ CDaaaa 
                else i--;         
             }
        }    
    }


    public void createTable() {
        Object[] VtArray = VtSet.toArray();
        Object[] VnArray = VnSet.toArray();
        // Ԥ��������ʼ��
        table = new String[VnArray.length + 1][VtArray.length + 1];
        table[0][0] = "Vn/Vt";
        //��ʼ����������
        for (int i = 0; i < VtArray.length; i++) 
            table[0][i + 1] = (VtArray[i].toString().charAt(0) == '~') ? "$" : VtArray[i].toString();  
        for (int i = 0; i < VnArray.length; i++) 
            table[i + 1][0] = VnArray[i] + "";
        //ȫ����error
        for (int i = 0; i < VnArray.length; i++)
            for (int j = 0; j < VtArray.length; j++)
                table[i + 1][j + 1] = "error";
            
        //��������ʽ
        for (char A : VnSet) {
            ArrayList<String> l = experssionSet.get(A);
            for(String s : l){
                HashSet<Character> set = firstSetX.get(s);
                 for (char a : set)
                    insert(A, a, s);
                 if(set.contains('~'))  {
                    HashSet<Character> setFollow = followSet.get(A);
                    if(setFollow.contains('$'))
                        insert(A, '$', s);  
                    for (char b : setFollow)
                        insert(A, b, s);                    
                 }
            }
        }
    }

    public void analyzeLL() {
        System.out.println("****************LL��������**********");
        System.out.println("               Stack           Input     Action");
        analyzeStatck.push('$');
        analyzeStatck.push('E');
        displayLL();
        char X = analyzeStatck.peek();
        while (X != '$') {
            char a = strInput.charAt(index);
            if (X == a) {
                action = "match " + analyzeStatck.peek();
                analyzeStatck.pop();
                index++;
            } else if (VtSet.contains(X))
                return;
            else if (find(X, a).equals("error"))
                return;
            else if (find(X, a).equals("~")) {
                analyzeStatck.pop();
                action = X + "->~";
            } else {
                String str = find(X, a);
                if (str != "") {
                    action = X + "->" + str;
                    analyzeStatck.pop();
                    int len = str.length();
                    for (int i = len - 1; i >= 0; i--)
                        analyzeStatck.push(str.charAt(i));
                } else {
                    System.out.println("error at '" + strInput.charAt(index) + " in " + index);
                    return;
                }
            }
            X = analyzeStatck.peek();
            displayLL();
        }
        System.out.println("analyze LL1 successfully");
        System.out.println("****************LL��������**********");
    }

    public String find(char X, char a) {
        for (int i = 0; i < VnSet.size() + 1; i++) {
            if (table[i][0].charAt(0) == X)
                for (int j = 0; j < VtSet.size() + 1; j++) {
                    if (table[0][j].charAt(0) == a)
                        return table[i][j];
                }
        }
        return "";
    }
    public void insert(char X, char a,String s) {
        if(a == '~') a = '$';
        for (int i = 0; i < VnSet.size() + 1; i++) {
            if (table[i][0].charAt(0) == X)
                for (int j = 0; j < VtSet.size() + 1; j++) {
                    if (table[0][j].charAt(0) == a){
                        table[i][j] = s;
                        return;
                    }
                }
        }
    }

    public void displayLL() {
        // ��� LL1
        Stack<Character> s = analyzeStatck;
        System.out.printf("%23s", s);
        System.out.printf("%13s", strInput.substring(index));
        System.out.printf("%10s", action);
        System.out.println();
    }

    public void ouput() {
        System.out.println("*********first��********");
        for (Character c : VnSet) {
            HashSet<Character> set = firstSet.get(c);
            System.out.printf("%10s",c + "  ->   ");
            for (Character var : set)
                System.out.print(var);
            System.out.println();
        }
        System.out.println("**********first��**********");
        System.out.println("*********firstX��********");
        Set<String> setStr =  firstSetX.keySet();
        for (String s : setStr) {
                HashSet<Character> set = firstSetX.get(s);
                System.out.printf("%10s",s + "  ->   ");
                for (Character var : set)
                    System.out.print(var);
                System.out.println();
            }
        System.out.println("**********firstX��**********");
        System.out.println("**********follow��*********");

        for (Character c : VnSet) {
            HashSet<Character> set = followSet.get(c);
            System.out.print("Follow " + c + ":");
            for (Character var : set)
                System.out.print(var);
            System.out.println();
        }
        System.out.println("**********follow��**********");

        System.out.println("**********LL1Ԥ�������********");

        for (int i = 0; i < VnSet.size() + 1; i++) {
            for (int j = 0; j < VtSet.size() + 1; j++) {
                System.out.printf("%6s", table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("**********LL1Ԥ�������********");
    }

}