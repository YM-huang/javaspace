package fifth_homework;

class Foo {
    protected class Bar {
        protected Bar() { System.out.println("Foo.Bar"); }
    }
    private Bar b;
    Foo() {
        System.out.println("Foo");
        b = this.new Bar();
    }
}

public class work_12 extends Foo  {
    protected class Bar {
        protected Bar() { System.out.println("FootToo.Bar"); }
    }
    public static void main(String[] args) {
        new work_12();
    }
}
