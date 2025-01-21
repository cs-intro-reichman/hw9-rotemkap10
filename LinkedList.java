/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node n = this.first;
		for(int i =0; i<index; i++)
		{
			n = n.next;
		}
		return n;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node n = new Node(block);
		if (size == 0) {  
			this.first = n;  
			this.last = n;
		}
		Node current = this.first;
		if(index ==0){
			n.next = this.first;
			this.first = n;
			size++;
		}
		if(index == size){
			this.last.next = n;
			this.last = n;
		}
		for(int i =0; i<index-1; i++){
			current = current.next;
		}
		n = current.next;
		current.next = n;
		size++;

	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node n = new Node(block);
		if (size == 0) {  
			this.first = n;  
			this.last = n;
		}
		else{
			this.last.next = n;
			this.last = n;
		}
		size++;
		
	}

	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node n = new Node(block);
		if (size == 0) {  
			this.first = n;  
			this.last = n;
		}
		else{
			n.next = this.first;
			this.first = n;
		}
		size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		return getNode(index).block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		Node n = this.first;
		for(int i =0; i<size; i++){
			if(n.block.equals(block)){
				return i;
			}
			n = n.next;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		Node current = this.first;
		Node prev = null;
		for(int i =0; i<size; i++){
			if(current.equals(node)){
				{
					if(prev ==null){
						this.first = current.next;
					}
					else{
						prev.next = current.next;
					}
					if(current == this.last){
						this.last = prev;
					}
				}
				size--;
			}
			prev = current;
			current = current.next;
		}
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
			if (index < 0 || index >= size) {
				throw new IllegalArgumentException("index must be between 0 and size - 1");
			}
			Node current = this.first;
			Node prev = null;
			for (int i = 0; i < index; i++) {
				prev = current;
				current = current.next;
			}
			if (prev == null) { 
				this.first = current.next;
			} 
			else { 
				prev.next = current.next;
			}
			if (current == this.last) { 
				this.last = prev;
			}
			size--; 
		}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int x = indexOf(block);
		if(x == -1){
			throw new IllegalArgumentException("no such block in the list");
		}
		remove(x);
		
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		if(size  == 0)
		{
			return "()";
		}
		else{
			String str = "(";
			Node current = this.first;
			for(int i =0; i<size; i++){
				str =  str + current.block.toString();
				if(i<size-1){
					str = str + " ";
				}
				current = current.next;
			}
			str = str + ")";
			return str;
		}
	}
}