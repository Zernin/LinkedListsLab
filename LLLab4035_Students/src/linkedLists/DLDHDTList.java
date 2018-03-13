package linkedLists;

import java.util.NoSuchElementException;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		header = new DNode<E>(null,null,trailer);
		trailer = new DNode<E>(null,header,null);
		length =0;
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		DNode<E> n = (DNode<E>) nuevo;
		DNode<E> t = (DNode<E>) target;
		n.setPrev(t.getPrev());
		n.setNext(t);
		t.getPrev().setNext(n);
		t.setPrev(n);
		length++;
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		if(length == 0 || ((DNode<E>)target).getNext() == trailer)
			throw new NoSuchElementException("Node Out of Bound");
		
		
		return ((DNode<E>)target).getNext(); 
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if(length == 0 || ((DNode<E>)target).getPrev() == header)
			throw new NoSuchElementException("Node Out of Bound");
		
		
		return ((DNode<E>)target).getPrev(); 
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		DNode<E> t = (DNode<E>) target;
		DNode<E> tNext = (DNode<E>) t.getNext();
		DNode<E> tPrev = (DNode<E>) t.getPrev();
		tNext.setPrev(tPrev);
		tPrev.setNext(tNext);
		t.clean();
		t = null;
		length--;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		// TODO
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}

}