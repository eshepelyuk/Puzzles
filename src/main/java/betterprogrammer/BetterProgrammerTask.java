package betterprogrammer;

import java.util.*;

public class BetterProgrammerTask {

    public static boolean isPalindrome(String s) {
        if (null == s || "".equals(s.trim())) {
            return false;
        }

        /*
          Definition: A palindrome is a string that reads the same forward and backward.
          For example, "abcba" is a palindrome, "abab" is not.
          Please implement this method to
          return true if the parameter is a palindrome and false otherwise.
         */
        String myStr = s.trim();
        for (int i = 0; i < (myStr.length() / 2); i++) {
            if (myStr.charAt(i) != myStr.charAt(myStr.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static int[] removeDuplicates(int[] a) {
        /*
          Please implement this method to
          remove all duplicates from the original array. Retain the order of the elements and
          always retain the first occurrence of the duplicate elements.
          For example, parameter: {2,1,2,3}, result: {2,1,3}
         */
        if (null == a) return null;

        Set<Integer> set = new LinkedHashSet<Integer>(a.length);
        for (int i : a) {
            set.add(i);
        }

        int[] retval = new int[set.size()];
        int i = 0;
        for (Integer integer : set) {
            retval[i++] = integer;
        }
        return retval;
    }

    // Please do not change this interface
    public static interface Node {
        int getValue();

        List<Node> getChildren();
    }

    public static List<Node> traverseTreeInDepth(Node root) {
        /*
          Please implement this method to
          traverse the tree in depth and return a list of all passed nodes.

          The method shall work optimally with large trees.
         */

        Deque<Node> stack = new LinkedList<Node>();
        List<Node> retval = new ArrayList<Node>(stack.size());

        stack.push(root);
        while (stack.size() > 0) {
            Node node = stack.pop();
            retval.add(node);

            for (Node n : node.getChildren()) {
                stack.push(n);
            }
        }

        return retval;
    }

    private static boolean isSorted(List<Integer> l) {
        for (int i = 0; i < (l.size() - 1); i++) {
            if (l.get(i) > l.get(i + 1)) return false;
        }
        return true;
    }

    public static List<Integer> getReversalsToSort(int[] a) {
        /*
         You need to sort an array of integers by repeatedly reversing
         the order of the first several elements of it.

         For example, to sort [12,13,11,14], you need to  reverse the order of the first two (2)
         elements and get [13,12,11,14] and then reverse the order of the first three (3)
         elements and get [11,12,13,14]

         The method should return the shortest(!) possible list of integers corresponding to the required reversals.
         For the previous example, given an array [12,13,11,14]
         the method should return a list with Integers 2 and 3.
         */


        List<Integer> retval = new ArrayList<Integer>();
        if (a == null || a.length < 2) return retval;

        List<Integer> listA = new ArrayList<Integer>();
        for (int i : a) {
            listA.add(i);
        }

        int cnt = 2;
        while (!isSorted(listA)) {
            Collections.reverse(listA.subList(0, cnt));
            retval.add(cnt++);
        }

        return retval;
    }
}
