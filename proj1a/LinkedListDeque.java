public class LinkedListDeque<T> {
	public int size;
	private IntNode sentinel;

	public class IntNode {
		public IntNode prev;
		public T item;
		public IntNode next;
		public IntNode(T i,IntNode f, IntNode r) {
			item = i;
			prev = f;
			next = r;
		}
	}

	public LinkedListDeque() {
		size = 0;
		sentinel = new IntNode(null , sentinel, sentinel);
	}

	public LinkedListDeque(T t) {
		size = 1;
		IntNode p = new IntNode(t, sentinel,sentinel);
		sentinel = new IntNode(null , p, p);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public LinkedListDeque(LinkedListDeque other) {
		LinkedListDeque d1 = new LinkedListDeque();
		while(other.size() != 0) {
			d1.addLast(other.removeFirst());
		}
	}

	public void addFirst(T item) {
		size += 1;
		IntNode p = new IntNode(item, sentinel, sentinel.next);
		sentinel.next = p;
	}

	public void addLast(T item) {
		size += 1;
		IntNode p = new IntNode(item, sentinel.prev, sentinel);
		sentinel.prev = p;
	}

	public T removeFirst() {
		IntNode p = sentinel;
		T item = p.next.item;
		p.next = p.next.next;
		size -= 1;
		return item;
	}

	public T removeLast() {
		IntNode p = sentinel;
		T item = p.prev.item;
		IntNode newLast = p.prev.prev;
		newLast.next = p;
		p.prev = newLast;
		size -= 1;
		return item;
	}
	/* print items of a Deque line by line */
	public void printDeque() {
		IntNode p = sentinel.next;
		while(p != null) {
			System.out.println(p.item);
			p = p.next;
		}
	}
	/* get an item in one of the index by iterating */
	public T get(int index) {
		IntNode p = sentinel;
		int i = 0;
		while(i <= index && p.next != null) {
			p = p.next;
			i += 1;
		}
		return p.item;
	}

	/* a helper function created in private for rucursively get */
	private T getRecursive(int index, IntNode current) {
		if(index == 0) {
			return current.item;
		} else {
			return getRecursive(index - 1, current.next);
		}
	}

	public T getRecursive(int index) {
		IntNode p = sentinel.next;
		return getRecursive(index, p);
	}


	/* here are some test used to check code runing */

	public static void main(String[] args) {
		LinkedListDeque<Integer> li = new LinkedListDeque<>();
		li.addFirst(5);
		// System.out.println(li.size());
		li.addLast(10);
		li.addLast(15);
		// System.out.println(li.size());
		li.removeLast();
		// System.out.println(li.size());
		li.addFirst(100);
		li.addFirst(1235);
		// li.printDeque();
		// System.out.println(li.get(0));
		// System.out.println(li.get(1));
		System.out.println(li.getRecursive(0));
		System.out.println(li.getRecursive(1));
		System.out.println(li.getRecursive(2));
		System.out.println(li.getRecursive(2));
	}
}