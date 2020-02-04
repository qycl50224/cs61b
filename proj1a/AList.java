public class AList<T> {
	private int size;
	private int last;
	T[] l;

	public Alist(T item) {
		size = 1;
		last = 0;
		l = new T[size];
		l[last] = item;

	}
	public static int[] insert(int[] arr,int item, int position) {
		int[] a = new int[arr.length+1];
		a[position] = item;
		System.arraycopy(arr, 0, a, 0, position-1);
		System.arraycopy(arr, position, a, position+1, a.length-1-position);
	}


	public void addLast(T item) {
		T[] a = (T []) new Object[size+1];
		System.arraycopy(l, 0, a, 0, size);
		a[size] = item;
		l = a;
		size += 1;
		last += 1;
	}

	public T getLast() {
		return l[last];
	}

	public T get(int ith) {
		return l[ith - 1];
	}

	public int size() {
		return size;
	}
} 










public void insert(int item, int position) {
	IntNode p = first;
	int ith = 0;
	while(p.next != null) {
		if(ith == position - 1) {
			IntNode a = new IntNode(item, p.next);
			p.next = a;
			return 
		}
		p = p.next;
		ith += 1;
	}
	IntNode a = new IntNode(item, null);
	p.next = a;
}

public void reverse() {
	if(first == null || first.next == null) {
		return;
	}
	IntNode p = first.next;
	first.next = null;
	while(p != null) {
		IntNode temp = p.next;
		p.next = first;
		first = p;
		p = temp;
	}
}

public void helper_reverse_recurtively(IntNode p) {
	if(first == null || first.next == null) {
		return;
	} elif(p.next == null) {
		p.next = first;
		first = p;
		return;
	} else {
		IntNode temp = p.next;
		p.next = first;
		first = p;
		p = temp;
		return helper_reverse_recurtively(p);
	}
}
public void reverse_recurtively() {
	IntNode p = first.next;
	first.next = null;
	helper_reverse_recurtively(p);	
}

public static int[] insert()