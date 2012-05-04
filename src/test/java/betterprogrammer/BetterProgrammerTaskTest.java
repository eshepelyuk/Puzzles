package betterprogrammer;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.isEquals;

public class BetterProgrammerTaskTest {
    @Test
    public void isPalindrome() throws Exception {
        assert !BetterProgrammerTask.isPalindrome(null);
        assert !BetterProgrammerTask.isPalindrome("   ");
        assert !BetterProgrammerTask.isPalindrome("abab");

        assert BetterProgrammerTask.isPalindrome(" a ");
        assert BetterProgrammerTask.isPalindrome(" abba ");
        assert BetterProgrammerTask.isPalindrome(" abcba ");
    }

    @Test
    public void removeDuplicates() throws Exception {
        assert BetterProgrammerTask.removeDuplicates(null) == null;
        assert isEquals(BetterProgrammerTask.removeDuplicates(new int[]{}), new int[]{});
        assert isEquals(BetterProgrammerTask.removeDuplicates(new int[]{1, 2}), new int[]{1, 2});
        assert isEquals(BetterProgrammerTask.removeDuplicates(new int[]{2, 1, 2, 3}), new int[]{2, 1, 3});
    }

    private static class NodeSupport implements BetterProgrammerTask.Node {

        private int value;
        private List<BetterProgrammerTask.Node> children;

        private NodeSupport(int value, List<BetterProgrammerTask.Node> children) {
            this.value = value;
            this.children = children;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public List<BetterProgrammerTask.Node> getChildren() {
            return children;
        }
    }

    @Test
    public void traverseTreeInDepth1() throws Exception {
        //given
        BetterProgrammerTask.Node leaf1 = new NodeSupport(4, Collections.EMPTY_LIST);
        BetterProgrammerTask.Node leaf2 = new NodeSupport(3, Collections.EMPTY_LIST);
        BetterProgrammerTask.Node leaf3 = new NodeSupport(2, Collections.EMPTY_LIST);
        BetterProgrammerTask.Node root = new NodeSupport(1, Arrays.asList(leaf1, leaf2, leaf3));

        //when
        List<BetterProgrammerTask.Node> value = BetterProgrammerTask.traverseTreeInDepth(root);

        //then
        assert value.size() == 4;
    }

    @Test
    public void traverseTreeInDepth2() throws Exception {
        //given
        BetterProgrammerTask.Node leaf5 = new NodeSupport(5, Collections.EMPTY_LIST);

        BetterProgrammerTask.Node leaf3 = new NodeSupport(3, Collections.EMPTY_LIST);
        BetterProgrammerTask.Node leaf4 = new NodeSupport(4, Collections.EMPTY_LIST);
        BetterProgrammerTask.Node leaf2 = new NodeSupport(2, Arrays.asList(leaf3, leaf4));

        BetterProgrammerTask.Node leaf7 = new NodeSupport(7, Collections.EMPTY_LIST);
        BetterProgrammerTask.Node leaf8 = new NodeSupport(8, Collections.EMPTY_LIST);
        BetterProgrammerTask.Node leaf6 = new NodeSupport(2, Arrays.asList(leaf7, leaf8));

        BetterProgrammerTask.Node root = new NodeSupport(1, Arrays.asList(leaf2, leaf5, leaf6));

        //when
        List<BetterProgrammerTask.Node> value = BetterProgrammerTask.traverseTreeInDepth(root);

        //then
        assert value.size() == 8;
    }

    @Test
    public void getReversalsToSort() throws Exception {
        List<Integer> list = BetterProgrammerTask.getReversalsToSort(null);
        assert isEquals(list.toArray(new Integer[list.size()]), new Integer[]{});

        list = BetterProgrammerTask.getReversalsToSort(new int[]{11});
        assert isEquals(list.toArray(new Integer[list.size()]), new Integer[]{});

        list = BetterProgrammerTask.getReversalsToSort(new int[]{11, 12, 13, 14});
        assert isEquals(list.toArray(new Integer[list.size()]), new Integer[]{});

        list = BetterProgrammerTask.getReversalsToSort(new int[]{11, 11, 11, 11});
        assert isEquals(list.toArray(new Integer[list.size()]), new Integer[]{});

//        list = BetterProgrammerTask.getReversalsToSort(new int[]{14,13,12,11});
//        assert isEquals(list.toArray(new Integer[list.size()]), new Integer[]{4});
//
//        list = BetterProgrammerTask.getReversalsToSort(new int[]{12,13,11,14});
//        assert isEquals(list.toArray(new Integer[list.size()]), new Integer[]{2,3});
//
//        list = BetterProgrammerTask.getReversalsToSort(new int[]{14,13,12,11});
//        assert isEquals(list.toArray(new Integer[list.size()]), new Integer[]{3,4,3});

    }
}
