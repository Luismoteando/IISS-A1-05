import java.util.*;

public class Frontier {
	
	LinkedList<Node> frontierList;
	PriorityQueue<Node> frontierQueue;
	
	///////////////////////////////List part///////////////////////////////
	public void createFrontierList(){
		frontierList = new LinkedList<Node>();
	}
	
	public LinkedList<Node> insertInList(Node node){
		if (frontierList.get(0) == null){
			frontierList.addFirst(node);
		}else{
			for(int i=0; i<frontierList.size(); i++){
				if (frontierList.get(i).getValue() < node.getValue()){
					continue;
				}
				else{
					frontierList.add(i, node);				
				}
			}
		}		
		return frontierList;
	}
	
	public void removeFirst(){
		frontierList.removeFirst();
	}
	
	public boolean isEmptyList(){
		return frontierList.isEmpty();
	}
	
	///////////////////////////Queue part////////////////////////////////
	
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
}
