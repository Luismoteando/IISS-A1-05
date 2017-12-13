import java.util.*;

/**
 * 
 * @author Lydia Prado Ibáñez, Luis Miguel Ortiz Rozalén and Rubén Pérez Rubio
 * Description: In this class, the data structure for the frontier is implemented. In this case, a priority queue is used.
 */
public class Frontier {
	
//	private LinkedList<Node> frontierList;
	private PriorityQueue<Node> frontierQueue;
		
	//Priority Queue
	
	public void createFrontierQueue(){
		frontierQueue = new PriorityQueue<Node>();
	}
	
	public void insertInQueue(Node node){	
		frontierQueue.add(node);
	}
	
	public boolean isEmptyQueue(){
		return frontierQueue.isEmpty();
	}
	
	public PriorityQueue<Node> getFrontierQueue(){
		return frontierQueue;
	}
	
	//Linked List
	
//	public void createFrontierList(){
//		frontierList = new LinkedList<Node>();
//	}
//	
//	public void insertInList(Node node){	
//		int i = 0;
//		if(frontierList.isEmpty())
//			frontierList.add(node);
//		else{
//			Node previous = frontierList.get(i);
//			for(int j = 0; j < frontierList.size(); j++){
//				if(previous.getValue() < node.getValue() && j + 1 < frontierList.size()){
//					previous = frontierList.get(j + 1);
//				}else{
//					frontierList.add(j, node);
//					return;
//
//				}
//			}
//		}
//	}
//	
//	public void removeFirst(){
//		frontierList.removeFirst();
//	}
//	
//	public boolean isEmptyList(){
//		return frontierList.isEmpty();
//	}
//	
//	public LinkedList<Node> getFrontierList(){
//		return frontierList;
//	}
//
}
