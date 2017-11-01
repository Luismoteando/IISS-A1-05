import java.util.*;

public class Frontier {
	
	LinkedList<Node> frontierList;
	PriorityQueue<Node> frontierQueue;
	
	///////////////////////////////List part///////////////////////////////
	public void createFrontierList(){
		frontierList = new LinkedList<Node>();
	}
	
	public void insertInList(Node node){
		/*if (frontierList.size() == 0){
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
		}*/		
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
