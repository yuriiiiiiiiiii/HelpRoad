package kr.ac.hansung.model;

public class Point {

	   private double lat, lon; //latitude 위도, longitude 경도
	   private int x, y;   //   행렬 x, y
	   private int STM = 0;
	   private int MTE = 0;
	   private int fin = 0;
	   
	   public void setPoint(double lat, double lon) {
	      this.lat = lat;
	      this.lon = lon;
	   }
	   
	   public Point() {}
	   public Point(int x, int y) {
	      this.x = x;
	      this.y = y;
	   }
	   
	   public void setPoint(int x, int y) {
	      this.x = x;
	      this.y = y;
	   }
	   
	   public double getLat() {
	      return lat;
	   }
	   
	   public double getLon() {
	      return lon;
	   }
	   
	   public void setSTM(int distance) {
	      this.STM = distance;
	   }
	   
	   public void setMTE(int MTE) {
	      this.MTE = MTE;
	   }
	   
	   public void plusSTM() {
	      STM++;
	   }
	   
	   public int getX() {
	      return x;
	   }
	   
	   public int getY() {
	      return y;
	   }
	   
	   public int getSTM() {
	      return STM;
	   }
	   
	   public int getMTE() {
	      return MTE;
	   }
	   
	   public int getFin() {
	      if(fin == 0) {
	         return STM + MTE;
	      }
	      else
	         return fin;
	   }
	}
