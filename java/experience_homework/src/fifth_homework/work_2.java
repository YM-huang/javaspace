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
//˳��̬���ԡ����ԡ���̬����顢����顢���췽�����ȸ��������
//�������в����Ĺ��췽���Ͳ�������޲εĹ��췽����
//Ĭ�ϵ����޲εĹ��췽����
//�ܽ᣺ִ��˳���Ⱦ�̬������ٹ��췽�����ȸ��������࣬��̬�����ֻ����һ�Ρ�