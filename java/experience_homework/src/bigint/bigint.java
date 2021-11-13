package bigint;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class bigint
{

    //��ģֻҪ�������Χ�ھͿ���ֱ�Ӽ�����
    private final static int SIZE = 4;

    // �˷���Ҫ��֤���lenΪX��Y�ĳ������ֵ
    private static String bigIntMultiply(String X, String Y, int len)
    {
        // ���շ��ؽ��
        String str = "";
        // ����X��Y��ʹ֮������ͬ
        X = formatNumber(X, len);
        Y = formatNumber(Y, len);

        // ����4λ������ֱ�Ӽ���
        if (len <= SIZE)
        {
            return ("" + (Integer.parseInt(X) * Integer.parseInt(Y)));
        }

        // ��X��Y�ֳ�������
        int len1 = len / 3;
        int len2 = (len - len1)/2;
        int len3 = len-len1-len2;
        String A = X.substring(0, len1);
        String B = X.substring(len1,len2);
        String C = X.substring(len2, len3);
        String D = Y.substring(0,len1);
        String E = Y.substring(len1,len2);
        String F = Y.substring(len2,len3);

        // �˷����򣬷ֿ鴦��
        int lenM1 = Math.max(len1, len2);
        int lenM2 = Math.max(len2, len3);
        String AD = bigIntMultiply(A, D, len1);
        String BE = bigIntMultiply(B, E, len2);
        String CF = bigIntMultiply(C, F, len3);
        //(A-B)(E-D)
        int lensub1 = Math.max(addition(A, B).length(), addition(E, D).length());
        String ABED = bigIntMultiply(addition(A, B),addition(E, D), lensub1);
        //(B-C)(F-E)
        int lensub2 = Math.max(addition(B, C).length(), addition(F, E).length());
        String BCFE = bigIntMultiply(addition(B, C),addition(F, E), lensub2);

        // ����CF���õ�ԭλ����λ
        String[] sCF = dealString(CF, len3);

        // ����(B-C)(F-E)+BE+CF�ĺ�
        String BCFEBECF = bigDigitalSub(bigDigitalSub(BCFE, BE),CF);
        // ����CF�Ľ�λ
        if (!"0".equals(sCF[1]))
        {
        	BCFEBECF = addition(BCFEBECF, sCF[1]);
        }

        // �õ�BCFEBECF�Ľ�λ
        String[] sBCFEBECF = dealString(BCFEBECF, lenM2);
        
        // ����(A-B)(E-D)+BE+AD�ĺ�
        String ABEDADBE = bigDigitalSub(bigDigitalSub(ABED, BE),AD);
        // ����CF�Ľ�λ
        if (!"0".equals(sBCFEBECF[1]))
        {
        	ABEDADBE = addition(BCFEBECF, sBCFEBECF[1]);
        }

        // �õ�ABEDADBE�Ľ�λ
        String[] sABEDADBE = dealString(ABEDADBE, lenM1);

        // AD����ABEDADBE�Ľ�λ
        AD = addition(AD, sABEDADBE[1]);

        // ���ս��
        str = AD + sABEDADBE[0] + sBCFEBECF[0] + sCF[0];

        return str;
    }

    // �������ִ���λ��
    private static String addition(String ad, String bc)
    {
        // ���صĽ��
        String str = "";

        // ���ַ�������Ҫ��ͬ
        int lenM = Math.max(ad.length(), bc.length());
        ad = formatNumber(ad, lenM);
        bc = formatNumber(bc, lenM);

        // ��λ�ӣ���λ�洢��temp��
        int flag = 0;

        // �Ӻ���ǰ��λ���
        for (int i = lenM - 1; i >= 0; i--)
        {
            int t =
                flag + Integer.parseInt(ad.substring(i, i + 1))
                    + Integer.parseInt(bc.substring(i, i + 1));

            // ����������9�����λ��ǰλֻ������λ��
            if (t > 9)
            {
                flag = 1;
                t = t - 10;
            }
            else
            {
                flag = 0;
            }

            // ƴ�ӽ���ַ���
            str = "" + t + str;
        }
        if (flag != 0)
        {
            str = "" + flag + str;
        }
        return str;
    }
    
    
    /*
      	�������������Ĭ��û�з���λ���Ҷ�Ϊ����
     */
    public static String bigDigitalSub(String a, String b) {
        //��ת�ַ�����ת��������
        char[] aArray = new StringBuilder(a).reverse().toString().toCharArray();
        char[] bArray = new StringBuilder(b).reverse().toString().toCharArray();
        int aLength = aArray.length;
        int bLength = bArray.length;
        //�ҵ�����λ�������������Ĳ��λ��С�ڵ������������е����λ��
        int maxLength = Math.max(aLength, bLength);
        int[] result = new int[maxLength];
        //�жϽ������
        char sign = '+';
        if (aLength < bLength) sign = '-';
        else if (aLength == bLength) {
            int i = maxLength - 1;
            while (i > 0 && aArray[i] == bArray[i])
                i--;
            if (aArray[i] < bArray[i])
                sign = '-';
        }
        //��ʼ��������
        for (int i = 0; i < maxLength; i++) {
            int aInt = i < aLength ? aArray[i] - '0' : 0;
            int bInt = i < bLength ? bArray[i] - '0' : 0;
            if (sign == '-') result[i] = bInt - aInt;
            else 
            	result[i] = aInt - bInt;
        }
        //�������������������е�ĳһλС��0�������λ��λ��Ȼ�󽫱�λ��10
        for (int i = 0; i < maxLength - 1; i++) {
            if (result[i] < 0) {
                result[i + 1] -= 1;
                result[i] += 10;
            }
        }

        //����������ת�����������
        StringBuffer realResult = new StringBuffer();
        if (sign == '-') realResult.append('-');
        boolean isBeginning = true;
        for (int i = maxLength - 1; i >= 0; i--) {
            if (result[i] == 0 && isBeginning) continue;
            else isBeginning = false;
            realResult.append(result[i]);
        }
        if (realResult.toString().equals("")) realResult.append('0');
        return realResult.toString();
    }
    
    

    // �������ִ����������λ��
    // String�����һ��Ϊԭλ���֣��ڶ���Ϊ��λ
    private static String[] dealString(String ac, int len1)
    {
        String[] str = {ac, "0"};
        if (len1 < ac.length())
        {
            int t = ac.length() - len1;
            str[0] = ac.substring(t);
            str[1] = ac.substring(0, t);
        }
        else
        {
            // Ҫ��֤�����length����ε�lenһ�£��������λ��0
            String result = str[0];
            for (int i = result.length(); i < len1; i++)
            {
                result = "0" + result;
            }
            str[0] = result;
        }
        return str;
    }

    // ������������λ������
    private static String formatNumber(String x, int len)
    {
        while (len > x.length())
        {
            x = "0" + x;
        }
        return x;
    }

    //����׮
    public static void main(String[] args)
    {
        // ������ʽ������0��ͷ�����ִ�
        String pat = "^[1-9]\\d*$";
        Pattern p = Pattern.compile(pat);

        // ��ó���A
        System.out.println("���������A������0��ͷ������������");
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        Matcher m = p.matcher(A);
        if (!m.matches())
        {
            System.out.println("���ֲ��Ϸ���");
            return;
        }

        // ��ó���B
        System.out.println("���������B������0��ͷ������������");
        sc = new Scanner(System.in);
        String B = sc.nextLine();
        m = p.matcher(B);
        if (!m.matches())
        {
            System.out.println("���ֲ��Ϸ���");
            return;
        }
        System.out.println(A + " * " + B + " = "
            + bigIntMultiply(A, B, Math.max(A.length(), B.length())));
    }
}
