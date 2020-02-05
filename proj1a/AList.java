public class AList<T> {
	private int size;
	private int last;
	T[] l;

	public AList() {
		size = 0;
		last = size - 1;
		l = (T[]) new Object[size];
	}

	public AList(T item) {
		size = 1;
		last = 0;
		l =(T[]) new Object[size];
		l[last] = item;

	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public static int[] insert(int[] arr,int item, int position) {
		int[] a = new int[arr.length+1];
		a[position] = item;
		System.arraycopy(arr, 0, a, 0, position);
		System.arraycopy(arr, position, a, position+1, a.length-1-position);
		return a;
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

	public T get(int index) {
		return l[index];
	}

	public void print() {
		for(int i = 0; i < size; i += 1) {
			System.out.println(l[i]);
		}
	}

	public static void main(String[] args) {
		AList a1 = new AList(5);
		a1.addLast(10);
		a1.addLast(15);
		a1.addLast(20);
		a1.addLast(25);
		System.out.println(a1.get(3));
		AList a2 = new AList(3);
		a2.addLast(6);
		a2.addLast(9);
		int[] i3 = new int[]{1, 2, 3, 4, 5 ,5 , 6, 7};
		i3 = insert(i3, 12, 3);
		for(int item : i3) {
			System.out.println(item);
		}
//		a1.print();
	}
}









/*

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

 */