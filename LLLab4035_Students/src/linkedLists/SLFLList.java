package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		SNode<E> sd = (SNode) nuevo;
		if (length()== 0)
			first = last = sd;
			
		else{
			sd.setNext(first);
			first = sd;
		}
		length++;
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		SNode<E> t = (SNode<E>) target;
		SNode<E> n = (SNode<E>) nuevo;
		if(length() == 0)
			addFirstNode(nuevo);
		
		else if(t.getNext() == null){
			n.setNext(null);
			t.setNext(n);
			last = n;
			length++;
		}
		
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		SNode<E> t = (SNode<E>) target;
		SNode<E> n = (SNode<E>) nuevo;
		((SNode<E>)(this.getNodeBefore(t))).setNext(n);
		n.setNext(t);
		
		if(t == first) {
			first = n;
	}
}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if(length == 0)
			throw new NoSuchElementException("");
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if(length == 0)
			throw new NoSuchElementException("");
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		if(target == last)
			throw new NoSuchElementException ("");
		return ((SNode<E>)target).getNext();
	}

	public Node<E> getNodeBefore(Node<E> target) throws NoSuchElementException {
		if(target == first)
			throw new NoSuchElementException ("");
		SNode<E> targetNode = first;
		while(targetNode.getNext() != target) {
			targetNode= targetNode.getNext();
		}
		return targetNode;
		
	}	

	public int length() {
		// TODO Auto-generated method stub
		return length;
	}

	public void removeNode(Node<E> target) {
		if(length == 0)
		  return; 
		if(target == first){ 
			first = first.getNext(); 
			}
		else if(target == last){ 
			last = (SNode<E>)(this.getNodeBefore(last)); 
			}
		else {
			SNode<E> specialNode = (SNode<E>) (this.getNodeBefore(target));
			specialNode.setNext(((SNode<E>)target).getNext());
		}
		length--;
		((SNode<E>) target).setNext(null);
		((SNode<E>) target).setElement(null);
		target = null;
		
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}

}
