public interface Deque<T>{
    public int size();
    public default boolean isEmpty(){
        if(size() == 0){
            return true;
        }
        return false;
    }
    public void addFirst(T item);
    public void addLast(T item);
    public T removeFirst();
    public T removeLast();
    public void printDeque();
    public T get(int index);

}