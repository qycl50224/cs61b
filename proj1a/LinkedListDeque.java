public class LinkedListDeque<T> {
	public int size;
	private IntNode sentinel;
	// private IntNode front;
	// private IntNode back;


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

	public LinkedListDeque(LinkedListDeque other) {
		
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

	public void remove(int ith) {
		int i = 0;
		IntNode p = sentinel;
		while( i < ith) {
			p = p.next;
			i += 1;
		}
		p.next = p.next.next;

	}

	public T get(int ith) {
		IntNode p = sentinel;
		int i = 0;
		while(i < ith) {
			p = p.next;
			i += 1;
		}
		return p.item;

	}



	public int size() {
		return size;
	}

	public static void main(String[] args) {
		LinkedListDeque<Integer> li = new LinkedListDeque<>();
		li.addFirst(5);
		System.out.println(li.size());
		li.addLast(10);
		li.addLast(15);
		System.out.println(li.size());
		li.remove(2);
		System.out.println(li.size());
	}
}