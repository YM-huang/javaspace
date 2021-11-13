package fifth_homework;

public class work_1 {

	private int i = 0;
	work_1 increment() {
        i++;//自身的++加到最后是10；
        return this.clone();
    }
    public work_1 clone() {
    	work_1 t = new work_1();//i==0
        t.i = i;//0==0还是0
        return t;
    }
    public work_1() { System.out.print(i); }
    void print() {
        System.out.printf("i = %d", i);
    }
    public static void main(String[] args) {
    	work_1 x = new work_1();
        for ( int i=0; i<10; i++ )
            x = x.increment();
        x.print();
    }

}
