package bigint;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class bigint
{

    //规模只要在这个范围内就可以直接计算了
    private final static int SIZE = 4;

    // 此方法要保证入参len为X、Y的长度最大值
    private static String bigIntMultiply(String X, String Y, int len)
    {
        // 最终返回结果
        String str = "";
        // 补齐X、Y，使之长度相同
        X = formatNumber(X, len);
        Y = formatNumber(Y, len);

        // 少于4位数，可直接计算
        if (len <= SIZE)
        {
            return ("" + (Integer.parseInt(X) * Integer.parseInt(Y)));
        }

        // 将X、Y分成三部分
        int len1 = len / 3;
        int len2 = (len - len1)/2;
        int len3 = len-len1-len2;
        String A = X.substring(0, len1);
        String B = X.substring(len1,len2);
        String C = X.substring(len2, len3);
        String D = Y.substring(0,len1);
        String E = Y.substring(len1,len2);
        String F = Y.substring(len2,len3);

        // 乘法法则，分块处理
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

        // 处理CF，得到原位及进位
        String[] sCF = dealString(CF, len3);

        // 处理(B-C)(F-E)+BE+CF的和
        String BCFEBECF = bigDigitalSub(bigDigitalSub(BCFE, BE),CF);
        // 加上CF的进位
        if (!"0".equals(sCF[1]))
        {
        	BCFEBECF = addition(BCFEBECF, sCF[1]);
        }

        // 得到BCFEBECF的进位
        String[] sBCFEBECF = dealString(BCFEBECF, lenM2);
        
        // 处理(A-B)(E-D)+BE+AD的和
        String ABEDADBE = bigDigitalSub(bigDigitalSub(ABED, BE),AD);
        // 加上CF的进位
        if (!"0".equals(sBCFEBECF[1]))
        {
        	ABEDADBE = addition(BCFEBECF, sBCFEBECF[1]);
        }

        // 得到ABEDADBE的进位
        String[] sABEDADBE = dealString(ABEDADBE, lenM1);

        // AD加上ABEDADBE的进位
        AD = addition(AD, sABEDADBE[1]);

        // 最终结果
        str = AD + sABEDADBE[0] + sBCFEBECF[0] + sCF[0];

        return str;
    }

    // 两个数字串按位加
    private static String addition(String ad, String bc)
    {
        // 返回的结果
        String str = "";

        // 两字符串长度要相同
        int lenM = Math.max(ad.length(), bc.length());
        ad = formatNumber(ad, lenM);
        bc = formatNumber(bc, lenM);

        // 按位加，进位存储在temp中
        int flag = 0;

        // 从后往前按位求和
        for (int i = lenM - 1; i >= 0; i--)
        {
            int t =
                flag + Integer.parseInt(ad.substring(i, i + 1))
                    + Integer.parseInt(bc.substring(i, i + 1));

            // 如果结果超过9，则进位当前位只保留个位数
            if (t > 9)
            {
                flag = 1;
                t = t - 10;
            }
            else
            {
                flag = 0;
            }

            // 拼接结果字符串
            str = "" + t + str;
        }
        if (flag != 0)
        {
            str = "" + flag + str;
        }
        return str;
    }
    
    
    /*
      	两个大数相减，默认没有符号位，且都为正数
     */
    public static String bigDigitalSub(String a, String b) {
        //翻转字符串并转化成数组
        char[] aArray = new StringBuilder(a).reverse().toString().toCharArray();
        char[] bArray = new StringBuilder(b).reverse().toString().toCharArray();
        int aLength = aArray.length;
        int bLength = bArray.length;
        //找到最大的位数，两个整数的差的位数小于等于两个整数中的最大位数
        int maxLength = Math.max(aLength, bLength);
        int[] result = new int[maxLength];
        //判断结果符号
        char sign = '+';
        if (aLength < bLength) sign = '-';
        else if (aLength == bLength) {
            int i = maxLength - 1;
            while (i > 0 && aArray[i] == bArray[i])
                i--;
            if (aArray[i] < bArray[i])
                sign = '-';
        }
        //开始计算结果集
        for (int i = 0; i < maxLength; i++) {
            int aInt = i < aLength ? aArray[i] - '0' : 0;
            int bInt = i < bLength ? bArray[i] - '0' : 0;
            if (sign == '-') result[i] = bInt - aInt;
            else 
            	result[i] = aInt - bInt;
        }
        //处理结果集，如果结果集中的某一位小于0，则向高位借位，然后将本位加10
        for (int i = 0; i < maxLength - 1; i++) {
            if (result[i] < 0) {
                result[i + 1] -= 1;
                result[i] += 10;
            }
        }

        //处理结果集，转化成真正结果
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
    
    

    // 处理数字串，分离出进位；
    // String数组第一个为原位数字，第二个为进位
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
            // 要保证结果的length与入参的len一致，少于则高位补0
            String result = str[0];
            for (int i = result.length(); i < len1; i++)
            {
                result = "0" + result;
            }
            str[0] = result;
        }
        return str;
    }

    // 乘数、被乘数位数对齐
    private static String formatNumber(String x, int len)
    {
        while (len > x.length())
        {
            x = "0" + x;
        }
        return x;
    }

    //测试桩
    public static void main(String[] args)
    {
        // 正则表达式：不以0开头的数字串
        String pat = "^[1-9]\\d*$";
        Pattern p = Pattern.compile(pat);

        // 获得乘数A
        System.out.println("请输入乘数A（不以0开头的正整数）：");
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        Matcher m = p.matcher(A);
        if (!m.matches())
        {
            System.out.println("数字不合法！");
            return;
        }

        // 获得乘数B
        System.out.println("请输入乘数B（不以0开头的正整数）：");
        sc = new Scanner(System.in);
        String B = sc.nextLine();
        m = p.matcher(B);
        if (!m.matches())
        {
            System.out.println("数字不合法！");
            return;
        }
        System.out.println(A + " * " + B + " = "
            + bigIntMultiply(A, B, Math.max(A.length(), B.length())));
    }
}
