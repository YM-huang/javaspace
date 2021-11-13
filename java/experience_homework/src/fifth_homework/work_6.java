package fifth_homework;

public class work_6 {
    int i=2;
    class A {
        int k = i;
        void f() { k=k+i; }
    }
    void f() {
        A a = new A();
        for ( i=0; i<10; i++ )//这里的i和上面是一个i
            a.f();
        System.out.println(a.k);
    }
    public static void main(String[] args) {
    	work_6 m = new work_6();
        m.f();
    }
}
