package kr.ac.hansung.service;

import java.util.LinkedList;

import kr.ac.hansung.model.Point;

public class LinkedListService extends LinkedList {
	
	private Node head = null;
	   private Node tail = null;
	   private int size = 0;

	   private class Node {
	      private Point data;
	      private Node next = null;

	      public Node(Point data) {
	         this.data = data;
	         this.next = null;
	      }

	      public String toString() {
	         return String.valueOf(this.data);
	      }

	      public Point getData() {
	         return data;
	      }
	   }

	   public void addFirst(Point input) {
	      Node newNode = new Node(input);
	      newNode.next = head;
	      head = newNode;
	      size++;
	      if (head.next == null) {
	         tail = head;
	      }
	   }

	   public void addLast(Point input) {
	      Node newNode = new Node(input);
	      if (size == 0) {
	         addFirst(input);
	      } else {
	         tail.next = newNode;
	         tail = newNode;
	         size++;
	      }
	   }

	   Node node(int index) {
	      Node x = head;
	      for (int i = 0; i < index; i++)
	         x = x.next;
	      return x;
	   }

	   public void add(int k, Point input) {
	      if (k == 0) {
	         addFirst(input);
	      } else {
	         Node temp1 = node(k - 1);
	         Node temp2 = temp1.next;
	         Node newNode = new Node(input);
	         temp1.next = newNode;
	         newNode.next = temp2;
	         size++;

	         if (newNode.next == null)
	            tail = newNode;
	      }
	   }
	   
	   public void add(Point input) {
	      if (size == 0) {
	         addFirst(input);
	      } else {
	         Node temp1 = node(size - 1);
	         Node temp2 = temp1.next;
	         Node newNode = new Node(input);
	         temp1.next = newNode;
	         newNode.next = temp2;
	         size++;

	         if (newNode.next == null)
	            tail = newNode;
	      }
	   }

	   public void set(int k, Point input) {
	      if (k == 0) {
	         Node newNode = new Node(input);
	         newNode.next = head.next;
	         head = newNode;
	      } else {
	         Node temp1 = node(k - 1);
	         Node temp2 = temp1.next;
	         Node newNode = new Node(input);
	         temp1.next = newNode;
	         if(temp2.next != null) 
	            newNode.next = temp2.next;

	         if (newNode.next == null)
	            tail = newNode;
	      }
	   }
	   
	   public String toString() {
	      if (head == null)
	         return "[]";

	      Node temp = head;
	      String str = "[";

	      while (temp.next != null) {
	         str += temp.data + ",";
	         temp = temp.next;
	      }

	      str += temp.data;
	      return str + "]";
	   }

	   public Point removeFirst() {
	      Node temp = head;
	      head = temp.next;

	      Point returnData = temp.data;
	      temp = null;
	      size--;
	      if(size == 0)
	         tail = null;
	      return returnData;
	   }

	   public Point remove(int k) {
	      if (k == 0)
	         return removeFirst();
	      Node temp = node(k - 1);

	      Node todoDeleted = temp.next;

	      temp.next = temp.next.next;

	      Point returnData = todoDeleted.data;
	      if (todoDeleted == tail)
	         tail = temp;

	      todoDeleted = null;
	      size--;
	      return returnData;
	   }

	   public void removeAll() {
	      while (size == 0) {
	         removeLast();
	      }
	   }

	   public Point removeLast() {
	      return remove(size - 1);
	   }

	   public int size() {
	      return size;
	   }

	   public Point get(int k) {
	      Node temp = node(k);
	      return temp.data;
	   }

	   public int indexOf(Point data) {
	      if (head == null)
	         return -1;
	      
	      Node temp = head;
	      int index = 0;

	      int y = temp.data.getX();
	      int x = temp.data.getY();
	      while (x != data.getX() || y != data.getY()) {
	         index++;

	         temp = temp.next;
	         if (temp == null)
	            return -1;
	         y = temp.data.getX();
	         x = temp.data.getY();
	      }

	      return index;
	   }

	   /*public ListIterator listIterator() {
	      return new ListIterator();
	   }

	   class ListIterator {
	      private Node lastReturned;
	      private Node next;
	      private int nextIndex;

	      ListIterator() {
	         next = head;
	         nextIndex = 0;
	      }

	      public Point next() {
	         lastReturned = next;
	         next = next.next;
	         nextIndex++;
	         return lastReturned.data;
	      }

	      public boolean hasNext() {
	         return nextIndex < size();
	      }

	      public void add(Point input) {
	         Node newNode = new Node(input);
	         if (lastReturned == null) {
	            head = newNode;
	            newNode.next = next;
	         } else {
	            lastReturned.next = newNode;
	            newNode.next = next;
	         }
	         lastReturned = newNode;
	         nextIndex++;
	         size++;
	      }

	      public void remove() {
	         if (nextIndex == 0) {
	            throw new IllegalStateException();
	         }
	         LinkedListService.this.remove(nextIndex - 1);
	         nextIndex--;
	      }
	   }*/

}
