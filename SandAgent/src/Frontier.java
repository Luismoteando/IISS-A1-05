import java.util.*;

public class Frontier {
	
	private LinkedList<Node> frontierList;
	private PriorityQueue<Node> frontierQueue;
	
	//Linked List
	public void createFrontierList(){
		frontierList = new LinkedList<Node>();
	}
	
	public void insertInList(Node node){	
		frontierList.add(node);
	}
	
	public void removeFirst(){
		frontierList.removeFirst();
	}
	
	public boolean isEmptyList(){
		return frontierList.isEmpty();
	}
	
	public LinkedList<Node> getFrontierList(){
		return frontierList;
	}
	
	//Priority Queue
	
	public void createFrontierQueue(){
		frontierQueue = new PriorityQueue<Node>();
	}
	
	public void insertInQueue(Node node){	
		frontierQueue.add(node);
	}
	
	public void removeFirstFromQueue(){
		frontierQueue.remove();
	}
	
	public boolean isEmptyQueue(){
		return frontierQueue.isEmpty();
	}
	
	public PriorityQueue<Node> getFrontierQueue(){
		return frontierQueue;
	}
}
