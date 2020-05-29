package kr.ac.hansung.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import kr.ac.hansung.model.Point;

@Service
public class PathFindingService {

	private static Point start = new Point();
	private static Point end = new Point();
	//private static Point move = new Point();
	private static final int N = 65;
	private static final int M = 87;
	private static int[][] map = new int[N][M]; // 평면도 행렬 저장할 배열
	// private static LinkedListService queue = new LinkedListService(); // 찾은 길
	// private static LinkedListService stack = new LinkedListService(); // 최종 경로
	
	private static List<Node> paths;

	public void createMap(int floor) { // 평면도 2진수 행렬 https://fors.tistory.com/77
		String fileName = "floors/" + floor + "f.csv";
		ClassPathResource resource = new ClassPathResource(fileName);
		// ClassPathResource resource = new
		// ClassPathResource("/resouces/floors/1f.csv");

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

	public class Node {

		int x; // 칸의 x 좌표
		int y; // 칸의 y 좌표

		// 이동한 경로를 연결리스트로 나타내기 위해 이전 Node를 참조하기위한 변수
		Node prev;

		Node(int x, int y, Node prev) {
			this.x = x;
			this.y = y;
			this.prev = prev;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}
	}

	public void bfs() {

		Queue<Node> queue = new LinkedList<>();
		paths = new LinkedList<>();
		
		// 방문한 노드는 true 로 표시하기위한 변수
		boolean[][] visited = new boolean[N][M];

		visited[start.getX()][start.getY()] = true;
		queue.offer(new Node(start.getX(), start.getY(), null));

		int dist_x[] = { 1, 0, -1, 0 };
		int dist_y[] = { 0, 1, 0, -1 };

		while (!queue.isEmpty()) {

			// 현재 큐에 있는 것을 꺼낸다.
			Node node = queue.poll();
			//System.out.println(node.x+","+node.y);
			
			// 만약 node가 -> 출구까지 왔는지 체크한다..
			if (node.x == end.getX() && node.y == end.getY()) {
				// 출구까지 왔으면 출구까지 오기전에 이전칸은 무엇이고 그 이전칸은 무엇인지
				// 거꾸로 prev 노드를 올라가면서 list 에 추가하고 탐색을 마무리 한다.
				//System.out.println("아!!!!!!!!!!");
				Node s = node;
				while (s != null) {
					//System.out.println("되니????????????");
					paths.add(0, s);
					s = s.prev;
				}
				break;
			}

			// 출구까지 온게 아니면 현재 칸(노드)에서 인접한 노드를
			// 서 -> 남 -> 동 -> 북 순으로 검사해본다.
			for (int i = 0; i < dist_x.length; i++) {
				//System.out.println("node: "+node.x+","+node.y);
				int vx = node.x + dist_x[i];
				int vy = node.y + dist_y[i];
				// 인접한 (vx, vy) 노드기 이전에 방문하였거나 wall 이면
				if (is_path(vx, vy, visited)) {
					//System.out.println(vx+","+vy);
					visited[vx][vy] = true;
					queue.offer(new Node(vx, vy, node));
				}
			}
		}

	}

	/**
	 * @return maze 영역을 벗어나거나 이미 방문한 노드가 아니면 true
	 */
	public static boolean is_path(int x, int y, boolean[][] visited) {

		if (x < 0 || y < 0 || x > N - 1 || y > M - 1) {
			return false;
		}

		// 이미 방문한 노드도 안됨. wall 은 갈수 없음.
		if (visited[x][y] || map[x][y] == 1) {
			return false;
		}

		return true;
	}

	public int direction(int x1, int y1, int x2, int y2) { // 경로 진행 방향

		System.out.println(x1 + ", " + x2);
		System.out.println(y1 + ", " + y2);

		/*if (x1 - x2 == 1) { // 상
			if (y1 == y2)
				return 0;
		} else if (x1 == x2) { // 우
			if (y1 - y2 == -1)
				return 90;
			else // 좌
				return 270;
		} else if (x1 - x2 == -1) { // 하
			if (y1 == y2)
				return 180;
		}
		return -1;*/
		
		if(y1 == y2) {
			if(x1 - x2 == 1)	// 상
				return 0;
			else	// 하
				return 180;
		}
		else {
			if(y1 - y2 == 1)	// 좌
				return 270;
			else	// 우
				return 90;
		}
	}

	private Iterator<Node> iterator;
	// private LinkedListService.ListIterator listIterator2;
	private Node read;
	private Node readNext;

	public String correctDirection(int heading, int direction) {
	
		/*if(0 <= heading && heading <= 30) {
			if(heading - 30 <= 0)
				if(heading - 30 + 360 <= direction || direction <= heading + 30)
					return "straight";
			else if()

		}
		if(30 <= heading && heading < 150) {
			if(heading - 30 <= direction && heading + 30 <= direction)
				return "straight";
			else if(heading - 30 < direction && heading + 30 + 180 < direction)
				return "right";
			else
				return "left";
		}
		else if(210 <= heading && heading < 330) {
			if(heading - 30 <= direction && heading + 30 <= direction)
				return "straight";
			else if(heading - 30 < direction || heading + 30 + 180 < direction + 360)
				return "right";
			else
				return "left";
		}
		else
			
		return "error";*/
		/*if(heading + 15 > 360) {
			if (heading + 15 - 360 >= direction || heading - 15 <= direction) // 방향 일치(오차범위 30이내)
					return "straight";
		}
		else if(heading - 15 < 0) {
			if (heading + 15 >= direction || heading - 15 + 360 <= direction) // 방향 일치(오차범위 30이내)
				return "straight";
		}
		else {
			if (heading + 15 >= direction && heading - 15 <= direction) // 방향 일치(오차범위 30이내)
				return "straight";
		}*/
		
		if(heading >= 0 && heading + 180 < 360) { // heading : 0 ~ 180 미만
			if(heading - 20 < 0) {
				if (heading + 20 >= direction || heading - 20 + 360 <= direction) // 방향 일치(오차범위 30이내)
					return "straight";
				else if (heading < direction && direction < heading + 180)
					return "right";
				else
					return "left";
			}
			else {
				if (heading + 20 >= direction && heading - 20 <= direction) // 방향 일치(오차범위 30이내)
					return "straight";
				else if (heading < direction && direction < heading + 180)
					return "right";
				/*else if (heading + 180 == direction)
					return "back";*/
				else
					return "left";
			}
			
		}
		else { // heading : 180 ~ 360
			if(heading + 20 > 360) {
				if (heading + 20 - 360 >= direction || heading - 20 <= direction) // 방향 일치(오차범위 30이내)
						return "straight";
				else if(heading < direction || heading + 180 - 360 > direction)
					return "right";
				else
					return "left";
			}
			else {
				if (heading + 20 >= direction && heading - 20 <= direction) // 방향 일치(오차범위 30이내)
					return "straight";
				else if(heading < direction || heading + 180 - 360 > direction)
					return "right";
				else
					return "left";
			}

			/*if (heading < direction && heading + 180 < direction + 360)
				return "right";
			else if (heading + 180 - 360 == direction)
				return "back"
			else
				return "left";*/
		}

	}

	public String navigation(double starth) { // starth : 현재 방향
		
		int dir = direction(read.x, read.y, readNext.x, readNext.y); // 경로 진행 방향 계산

		//String heading = correctDirection((int)starth - 3, dir); // 건물이 3도 기울어져 있음
		String heading = correctDirection((int)starth, dir);
		
		if(heading.equals("straight")) {	//	현재 향하는 방향과 경로 방향이 일치할 때
			read = readNext;
			if (iterator.hasNext()) {
				readNext = iterator.next();
			}
		}

		if (read == null || readNext == null) // 길찾기 에러
			return "error";

		if(read == readNext) // 도착
			return "end";
			
		return heading;
	}

	public void getRoute(Point startp, int startFloor, Point endp) { // startp : 현재 위치, startFloor : 현재 층, endp : 도착 위치
		createMap(startFloor);

		if(startp.getX() >= -5 && startp.getX() < 0)	// gps의 정확도가 부족으로 5칸 이내는 가장 가까운 칸으로 대체
			startp.setPoint(0, startp.getY());
		else if(startp.getX() >= N && startp.getX() < N + 5)
			startp.setPoint(N-1, startp.getY());
		
		if(startp.getY() >= -5 && startp.getY() < 0)
			startp.setPoint(0, startp.getY());
		else if(startp.getY() >= M && startp.getY() < M + 5)
			startp.setPoint(startp.getX(), M-1);
		
		start.setPoint(startp.getX(), startp.getY());
		//move.setPoint(start.getX(), start.getY());
		end.setPoint(endp.getX(), endp.getY());
		
		bfs();

		//listIterator = paths.listIterator();
		//System.out.println(paths);
		
		iterator = paths.iterator();
		if (iterator.hasNext()) {
			read = iterator.next();
		}
		if (iterator.hasNext()) {
			readNext = iterator.next();
		}

		/*
		 * while (listIterator.hasNext()) { Point read = new Point(); read =
		 * listIterator.next(); map[read.getX()][read.getY()] = 5;
		 * System.out.println(read.getX() + ", " + read.getY()); }
		 * 
		 * String line =""; int row = 0, i; for(int j = 0; j < N; j++) {
		 * 
		 * for (i = 0; i < M; i++) { System.out.print(map[row][i] + ","); }
		 * System.out.println("");
		 * 
		 * 
		 * row++; }
		 */
	}

	public void getRoute(Point startp, int startFloor, Point elve1, Point elve2) { // startp : 현재 위치 , startFloor : 현재
																					// 층, elve1 : 엘리베이터1, elve2 : 엘리베이터2
		createMap(startFloor);
		
		if(startp.getX() >= -5 && startp.getX() < 0)	// gps의 정확도가 부족으로 5칸 이내는 가장 가까운 칸으로 대체
			startp.setPoint(0, startp.getY());
		else if(startp.getX() >= N && startp.getX() < N + 5)
			startp.setPoint(N-1, startp.getY());
		
		if(startp.getY() >= -5 && startp.getY() < 0)
			startp.setPoint(0, startp.getY());
		else if(startp.getY() >= M && startp.getY() < M + 5)
			startp.setPoint(startp.getX(), M-1);

		/*
		 * start.setPoint(startp.getX(), startp.getY()); move.setPoint(start.getX(),
		 * start.getY()); end.setPoint(elva1.getX(), elva1.getY());
		 * 
		 * findingLogic();
		 * 
		 * 
		 * listIterator = stack.listIterator(); int e1 = stack.size(); //엘베1까지의 거리
		 * 
		 * queue.removeAll(); stack.removeAll(); //초기화
		 * 
		 * move.setPoint(start.getX(), start.getY()); end.setPoint(elva2.getX(),
		 * elva2.getY());
		 * 
		 * findingLogic(); int e2 = stack.size(); //엘베2까지의 거리 listIterator2 =
		 * stack.listIterator();
		 * 
		 * if(e1 > e2) { //엘베2가 더 가깝다면 2번 째 걸로 바꿔라. listIterator = listIterator2; }
		 * 
		 * if(listIterator.hasNext()) { read = listIterator.next(); }
		 * if(listIterator.hasNext()) { readNext = listIterator.next(); }
		 */

		int elve1dist = (startp.getX() - elve1.getX()) * (startp.getX() - elve1.getX()) + (startp.getY() - elve1.getY()) * (startp.getY() - elve1.getY()); // 엘리베이터1과 거리
		int elve2dist = (startp.getX() - elve2.getX()) * (startp.getX() - elve2.getX()) + (startp.getY() - elve2.getY()) * (startp.getY() - elve2.getY()); // 엘리베이터2와 거리

		start.setPoint(startp.getX(), startp.getY());
		//move.setPoint(start.getX(), start.getY());

		if (elve1dist < elve2dist) { // 엘리베이터1과의 거리가 더 짧을 때
			end.setPoint(elve1.getX(), elve1.getY());
		} else
			end.setPoint(elve2.getX(), elve2.getY());

		bfs();

		//listIterator = paths.listIterator();

		iterator = paths.iterator();
		if (iterator.hasNext()) {
			read = iterator.next();
		}
		if (iterator.hasNext()) {
			readNext = iterator.next();
		}

	}

	public void draw() {

		//ListIterator iter = paths.listIterator();

		Iterator<Node> iter = paths.iterator();
		
		while (iter.hasNext()) {
			Node r = iter.next();
			map[r.x][r.y] = 5;
		}

		int row = 0, i;
		for (int j = 0; j < N; j++) {

			for (i = 0; i < M; i++) {
				System.out.print(map[row][i] + ",");
			}
			System.out.println("");

			row++;
		}
	}

}
