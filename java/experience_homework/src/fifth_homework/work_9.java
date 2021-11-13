package fifth_homework;

class ValHold{
    public int i = 10;
}

public class work_9{
    public static void main(String argv[]){
    	work_9 o = new work_9();
            o.amethod();
    }
    public void amethod(){
            int i = 99;
            ValHold v = new ValHold();
            v.i=30;
            another(v,i);
            System.out.print( v.i );
    }

    public void another(ValHold v, int i){
            i=0;
            v.i = 20;
            ValHold vh = new ValHold();
            v =  vh;//v的原始地址没变
            System.out.print(v.i);
    System.out.print(i);
    }
}
