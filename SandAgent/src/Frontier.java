import java.util.*;

public class Frontier {
	
	LinkedList<Node> frontier;
	
	public void createFrontier(){
		frontier = new LinkedList<Node>();
	}
	
	public LinkedList<Node> insert(Node node){
		if (frontier.get(0) == null){
			frontier.addFirst(node);
		}else{
			for(int i=0; i<frontier.size(); i++){
				if (frontier.get(i).getValue() < node.getValue()){
					continue;
				}
				else{
					frontier.add(i, node);				
				}
			}
		}		
		return frontier;
	}
	
	public void removeFirst(){
		frontier.removeFirst();
	}
	
	public boolean isEmpty(){
		return frontier.isEmpty();
	}
	

}
