package fifth_homework;

public class work_4 {

	int i=0;
	work_4(int ii) { i = ii; }
	work_4(String s) { this(s.length()); }
    public String toString() { return String.format("%02X",i); }
    public static void main(String[] args) {
    	work_4 m = new work_4("hello world");
        System.out.println(m);
    }

}
