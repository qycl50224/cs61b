public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dd = new LinkedListDeque<>();
        int len = word.length();
        for(int i = 0; i < len; i += 1) {
            dd.addLast(word.charAt(i));
        }
        return dd;
    }

    /*
     * a helper function which has a parameter of deque
     */
    private boolean isPalindrome(Deque<Character> dq) {
        if(dq.isEmpty()) {
            return true;
        } else if(dq.size() == 1) {
            return true;
        } else {
            if(dq.removeFirst() == dq.removeLast()) {
                return isPalindrome(dq);
            } else {
                return false;
            }
        }
    }
    /*
     * task3 a and b
     */
    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    /*
     * an overloaded method of isPalindrome in task4
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> dq = wordToDeque(word);
        int length = dq.size();
        while(dq.size() != 1 && dq.size() != 0 ) {
            if(! cc.equalChars(dq.removeFirst(), dq.removeLast()) ) {
                return false;
            }
        }
        return true;
    }
}