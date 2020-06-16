package kr.ac.hansung.model;

public class Point {

	   private double lat, lon; //latitude 위도, longitude 경도
	   private int x, y;   //   행렬 x, y
	   
	   public Point() {}
	   
	   public Point(double lat, double lon) {
		   this.lat = lat;
		   this.lon = lon;
	   }
	   
	   public Point(int x, int y) {
		   this.x = x;
		   this.y = y;
	   }
	   
	   public void setPoint(int x, int y) {
		   this.x = x;
		   this.y = y;
	   }
	   
	   public void setPoint(double lat, double lon) {
		   this.lat = lat;
		   this.lon = lon;
	   }
	   
	   public int getX() {
		   return x;
	   }
	   
	   public int getY() {
		   return y;
	   }
	   
	   public double getLat() {
		   return lat;
	   }
	   
	   public double getLon() {
		   return lon;
	   }
	   
}
