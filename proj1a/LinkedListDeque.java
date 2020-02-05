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
		sentinel = new IntNode(null , null, null);
		sentinel.prev = sentinel;
		sentinel.next = sentinel;
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
		sentinel.prev = p;
	}

	public void addLast(T item) {
		size += 1;
		IntNode p = new IntNode(item, sentinel.prev, sentinel);
		sentinel.prev.next = p;
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
		while(p.item != null) {
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
		LinkedListDeque<Character> li = new LinkedListDeque<>();
		char a = 'a';
		char b = 'b';
		char c = 'c';
		char d = 'd';
		char e = 'e';
		char f = 'f';
		// li.addFirst(a);
		// System.out.println(li.size());
		li.addLast(b);
		// li.printDeque();
		li.addLast(c);
		// System.out.println(li.size());
		// li.removeLast();
		// System.out.println(li.size());
		li.addFirst(d);
		li.addFirst(e);
		li.removeFirst();
		li.printDeque();

	}
}
