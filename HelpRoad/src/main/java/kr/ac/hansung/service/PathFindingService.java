package kr.ac.hansung.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import kr.ac.hansung.model.Point;

@Service
public class PathFindingService {


	   private static Point start = new Point();
	   private static Point end = new Point();
	   private static Point move = new Point();
	   private static final int PATHWAY = 0;
	   private static final int WALL = 1;
	   private static final int BLOCKED = 2;
	   // private static final int PATH = 3;
	   private static final int N = 65;
	   private static final int M = 87;
	   private static int[][] map = new int[N][M]; // 평면도 행렬 저장할 배열
	   private static LinkedListService queue = new LinkedListService(); // 찾은 길
	   private static LinkedListService stack = new LinkedListService();

	   public static void createMap() { // 평면도 2진수 행렬 https://fors.tistory.com/77
		   ClassPathResource resource = new ClassPathResource("floors/1f.csv");

		   try {
	         // csv 데이터 파일
			   Path path = Paths.get(resource.getURI());
	         File csv = new File(path.toString()); // 경로 q
	         BufferedReader br = new BufferedReader(new FileReader(csv));
	         String line = "";
	         int row = 0, i;

	         while ((line = br.readLine()) != null) {
	            // -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
	            String[] token = line.split(",", -1);
	            for (i = 0; i < M; i++) {
	               map[row][i] = Integer.parseInt(token[i]);
	            }

	            // CSV에서 읽어 배열에 옮긴 자료 확인하기 위한 출력
	            /*
	             * for (i = 0; i < M; i++) { System.out.print(map[row][i] + ","); }
	             * System.out.println("");
	             */

	            row++;
	         }
	         br.close();

	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }

	   public static int MoveToEnd(Point move) {
	      int x = move.getX();
	      int y = move.getY();

	      int MoveToEnd = Math.abs(x - end.getX()) + Math.abs(y - end.getY());

	      return MoveToEnd;
	   }

	   public static void findingLogic() {
	      queue.add(move);
	      while (true) {
	         Point firstQ = queue.removeFirst();
	         move.setPoint(firstQ.getX(), firstQ.getY());
	         move.setMTE(firstQ.getMTE());
	         move.setSTM(firstQ.getSTM());
	         
	         int x = move.getX();
	         int y = move.getY();

	         Point temp = new Point();
	         temp.setPoint(x, y);
	         temp.setMTE(firstQ.getMTE());
	         temp.setSTM(move.getSTM());
	         map[x][y] = 2;
	         stack.add(temp);

	         if (x == end.getX() && y == end.getY()) {
	            break; // 도착
	         }

	         Point left = null;
	         Point right = null;
	         Point up = null;
	         Point down = null;
	         
	         int size = queue.size();

	         LinkedListService arr = new LinkedListService();
	         if (map[x - 1][y] == 0) {
	            left = new Point(x - 1, y);
	            int STM = move.getSTM()+1;
	            left.setSTM(STM);
	            int MTE = MoveToEnd(left);
	            left.setMTE(MTE);
	            int index = queue.indexOf(left);
	            if (index == -1)
	               arr.addFirst(left);
	            /*            else if ( left.getFin() < queue.get(index).getFin() ){
	               int i = stack.indexOf(left);
	               stack.remove(i);
	               queue.remove(index);
	               queue.addFirst(left); 
	            }*/
	         }
	         if (map[x][y - 1] == 0) {
	            up = new Point(x, y - 1);
	            int STM = move.getSTM()+1;
	            up.setSTM(STM);
	            int MTE = MoveToEnd(up);
	            up.setMTE(MTE);
	            int index = queue.indexOf(up);
	            if (index == -1)
	               arr.addFirst(up);
	            /*         else if ( up.getFin() < queue.get(index).getFin() ){
	               int i = stack.indexOf(up);
	               stack.remove(i);
	               queue.remove(index);
	               queue.addFirst(up);
	            } */
	         }
	         if (map[x][y + 1] == 0) {
	            down = new Point(x, y + 1);
	            int STM = move.getSTM()+1;
	            down.setSTM(STM);
	            int MTE = MoveToEnd(down);
	            down.setMTE(MTE);
	            int index = queue.indexOf(down);
	            if (index == -1)
	               arr.addFirst(down);
	            /*               else if ( down.getFin() < queue.get(index).getFin() ){
	               int i = stack.indexOf(down);
	               stack.remove(i);
	               queue.remove(index);
	               queue.addFirst(down);
	            } */
	         }
	         if (map[x + 1][y] == 0) {
	            right = new Point(x + 1, y);
	            int STM = move.getSTM()+1;
	            right.setSTM(STM);
	            int MTE = MoveToEnd(right);
	            right.setMTE(MTE);
	            int index = queue.indexOf(right);
	            if (index == -1)
	               arr.addFirst(right);
	            /*         else if ( right.getFin() < queue.get(index).getFin() ){
	               int i = stack.indexOf(right);
	               stack.remove(i);
	               queue.remove(index);
	               queue.addFirst(right);
	            } */
	         }

	         if (arr.size() == 0) {
	            stack.removeLast();
	            Point rePoint = stack.removeLast();
	            queue.addFirst(rePoint);
	            move.setPoint(rePoint.getX(), rePoint.getY());
	            continue;
	         }

	         
	         for(int i=0; i<arr.size()-1; i++) {
	            int min = arr.get(i).getFin();
	            for(int j=(i+1); j<arr.size(); j++) {
	               int a = arr.get(j).getFin();
	               if(min > a) {
	                  Point temp1 = arr.get(i);
	                  Point temp2 = arr.get(j);
	                  arr.set(i, temp2);
	                  arr.set(j, temp1);
	               }
	            }
	         }
	         for(int i=arr.size()-1; i>=0; i--) {
	            int index = queue.indexOf(arr.get(i));
	            if(index == -1) {
	               queue.addFirst(arr.removeLast());
	            }
	            else if(arr.get(i).getFin() <= queue.get(index).getFin()) {
	               int sIndex = stack.indexOf(arr.get(i));
	               if (sIndex != -1)
	                  stack.remove(sIndex);
	               queue.remove(index);
	               queue.addFirst(arr.removeLast());
	            }
	         }
	      }
	   }

	   public void getRoute(Point startp, Point endp) {
	      createMap();
	      start.setPoint(startp.getX(), startp.getY());
	      move.setPoint(start.getX(), start.getY());
	      end.setPoint(endp.getX(), endp.getY());
	      findingLogic();

	      LinkedListService.ListIterator listIterator = stack.listIterator();

	      while (listIterator.hasNext()) {
	         Point read = new Point();
	         read = listIterator.next();
	         map[read.getX()][read.getY()] = 5;
	         System.out.println(read.getX() + ", " + read.getY());
	      }
	      
	      String line ="";
	      int row = 0, i;
	      for(int j = 0; j < N; j++) {
	         
	          for (i = 0; i < M; i++) { 
	             System.out.print(map[row][i] + ","); 
	            }
	           System.out.println("");
	          

	         row++;
	      }
	   }
}
