public class OffByN implements CharacterComparator {
    public int N;
    public OffByN(int n) {
        N = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int a = x;
        int b = y;
        if(a-b == N || b-a == N) {
            return true;
        }
        return false;
    }
}