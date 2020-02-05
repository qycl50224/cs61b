public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dd = new LinkedListDeque<>();
        int len = word.length();
        for(int i = 0; i < len; i += 1) {
            dd.addLast(word.charAt(i));
        }
        return dd;
    }

    public boolean isPalindrome(String word) {
        return true;
    }
}