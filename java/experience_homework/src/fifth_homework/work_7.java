package fifth_homework;

public enum work_7 {
	  PLUS   { int eval(int x, int y) { return x + y; } },
	  MINUS  { int eval(int x, int y) { return x - y; } },
	  TIMES  { int eval(int x, int y) { return x * y; } },
	  DIVIDE { int eval(int x, int y) { return x / y; } };
	  abstract int eval(int x, int y);
	  public static void main(String args[]) {
	          int x = 4;
	        int y = 2;
	        for (work_7 op : work_7.values())
	            System.out.printf("%d %s %d = %d%n", x, op, y, op.eval(x, y));
	  }
}
