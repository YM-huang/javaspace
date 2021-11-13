import java.sql.Date;
import java.text.SimpleDateFormat;

public class date1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date d1 = new Date(0);
        SimpleDateFormat dfd = new SimpleDateFormat("dd");
        String date = dfd.format(d1);
        int date2 = Integer.parseInt(date);//获取日期
        System.out.println(date2);
	}

}
