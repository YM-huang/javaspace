package sixth_experience;
import java.util.*;
import com.alibaba.fastjson.*;
import java.io.*;
public class jsontoobj {
	
	private static double earthRadiusMeters = 6371000.0; //源码中使用半径为6367460.0;

	private double metersPerDegree = 2.0 * Math.PI * earthRadiusMeters / 360.0;

	private double radiansPerDegree = Math.PI / 180.0;

	private double degreesPerRadian = 180.0 / Math.PI;

	private void calculateArea(List<double[]> points) {

		if (points.size() > 2) {

			double areaMeters2 = PlanarPolygonAreaMeters2(points);

			if (areaMeters2 > 1000000.0) 
				areaMeters2 = SphericalPolygonAreaMeters2(points); 
			System.out.println("面积为："+Math.abs(areaMeters2)+"（平方米）");

		}
		
	}


	private double SphericalPolygonAreaMeters2(List<double[]> points) {

		double totalAngle = 0.0;

		for (int i = 0; i < points.size(); ++i) {

			int j = (i + 1) % points.size();

			int k = (i + 2) % points.size();

			totalAngle += Angle(points.get(i), points.get(j), points.get(k));

		}

		double planarTotalAngle = (points.size() - 2) * 180.0;

		double sphericalExcess = totalAngle - planarTotalAngle;

		if (sphericalExcess > 420.0) {

			totalAngle = points.size() * 360.0 - totalAngle;
	
			sphericalExcess = totalAngle - planarTotalAngle;

		}

		else if (sphericalExcess > 300.0 && sphericalExcess < 420.0)

		{ sphericalExcess = Math.abs(360.0 - sphericalExcess); }

		return sphericalExcess * radiansPerDegree * earthRadiusMeters * earthRadiusMeters;

	}



	private double Angle(double[] p1, double []p2, double[] p3) {

		double bearing21 = Bearing(p2, p1);

		double bearing23 = Bearing(p2, p3);

		double angle = bearing21 - bearing23;

		if (angle < 0.0) angle += 360.0;

		return angle;

	}

	private double PlanarPolygonAreaMeters2(List<double[]> points) {

		double a = 0.0;

		for (int i = 0; i < points.size(); ++i) {

			int j = (i + 1) % points.size();

			double xi = points.get(i)[0] * metersPerDegree * Math.cos(points.get(i)[1] * radiansPerDegree);

			double yi = points.get(i)[1] * metersPerDegree;

			double xj = points.get(j)[0] * metersPerDegree * Math.cos(points.get(j)[1] * radiansPerDegree);

			double yj = points.get(j)[1] * metersPerDegree;

			a += xi * yj - xj * yi;

		}

		return Math.abs(a / 2.0);

	}

	private double Bearing(double []from, double[] to) {

	double lat1 = from[1] * radiansPerDegree;

	double lon1 = from[0] * radiansPerDegree;

	double lat2 = to[1] * radiansPerDegree;

	double lon2 = to[0] * radiansPerDegree;

	double angle = -Math.atan2(Math.sin(lon1 - lon2) * Math.cos(lat2), Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

	if (angle < 0.0) angle += Math.PI * 2.0;

	angle = angle * degreesPerRadian;

	return angle;

	}
	
	
	public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer strbuffer = new StringBuffer();
            while ((ch = reader.read()) != -1) {
            	strbuffer.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = strbuffer.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	public static void main(String[] args) {
		//String path = two.class.getClassLoader().getResource("world.json").getPath();
        
		String s = readJsonFile("world.json");
        
		JSONObject jsonObj = JSON.parseObject(s);
        
		JSONArray country = jsonObj.getJSONArray("features");
        
		for(int i=0;i<country.size();i++) {
        	
        	List<double[]> points = new ArrayList<double[]>();
        	
        	String temp1 = "";
        	
        	String temp2 = "";
        	
        	int t = 1;
        	
        	JSONObject key = (JSONObject)country.get(i);
        	
        	String id=key.get("id").toString();
        	
        	System.out.print("id:"+id+"  ");
            
        	JSONObject properties=key.getJSONObject("properties");
            
        	String name=properties.get("name").toString();
            
        	System.out.print("name:"+name+"  ");
            
        	JSONObject geometry = (JSONObject)key.get("geometry");
            
        	String coordinates = geometry.getJSONArray("coordinates").toString();
            
        	coordinates = coordinates.replaceAll("\\[","");
            
        	coordinates = coordinates.replaceAll("\\]","");
            
        	String[] coordinates1 = coordinates.split(",");
            
        	for(String ss : coordinates1 ) {

                if(t == 1)
                {
                	temp1 = ss;
                }
                else if(t == -1)
                {
                	temp2 = ss;
                	
                	double[] point = {Double.parseDouble(temp1),Double.parseDouble(temp2)};
                	
                	points.add(point);
                }

                t  = t * (-1);
        	}

            jsontoobj area = new jsontoobj();

            area.calculateArea(points);

        }
	}
}