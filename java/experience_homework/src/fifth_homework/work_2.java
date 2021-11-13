package fifth_homework;

class Window {
    Window(int marker) { System.out.println("Window(" + marker + ")"); }
}
class House {
    Window w1 = new Window(1); 
    House() {
        System.out.println("House()");
        w3 = new Window(33); 
    }
    Window w2 = new Window(2); 
    void f() {
        System.out.println("f()");
    }
    static Window w3 = new Window(3); 
}

public class work_2 {
    public static void main(String[] args) {
        House h = new House();
        h.f(); 
    }
}
//顺序静态属性、属性、静态代码块、代码块、构造方法，先父类后子类
//调用了有参数的构造方法就不会调用无参的构造方法。
//默认调用无参的构造方法。
//总结：执行顺序，先静态代码块再构造方法，先父类再子类，静态代码块只运行一次。