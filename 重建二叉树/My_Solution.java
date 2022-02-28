
import model.TreeNode;

import java.util.*;

public class Solution {
    /*
    1.给根节点赋值
    2.构造左子树
    3.构建右子树
     */
    Set<Integer> set = new HashSet<>();
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
        if (pre.length == 0) return null;
        else if (pre.length == 1) return new TreeNode(pre[0]);
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < vin.length; i++) map.put(vin[i], i);
        buildTree(pre, 0, vin, map, set, root);
        return root;
    }

    private void buildTree(int[] pre, int preRootIndex, int[] vin, Map<Integer, Integer> map, Set<Integer> set, TreeNode root) {
        root.val = pre[preRootIndex];
        set.add(pre[preRootIndex]);
        int index = map.get(pre[preRootIndex]);
        //一个是4，另外一个是6，这里会越界。
        if (index - 1 < 0) {
            if (isExistInTree(vin[index + 1], set)) return;

        } else if (index + 1 >= vin.length) {
            if (isExistInTree(vin[index - 1], set)) return;

        } else if ((isExistInTree(vin[index - 1], set) && (isExistInTree(vin[index + 1], set)))) return;

        //构造左子树
        if (!((index - 1 < 0) || (isExistInTree(vin[index - 1], set)))) {
            root.left = new TreeNode(pre[preRootIndex + 1]);
            buildTree(pre, preRootIndex + 1, vin, map, set, root.left);
        }
        //构造右子树
        if (!((index + 1 >= vin.length) || (isExistInTree(vin[index + 1], set)))) {
            root.right = new TreeNode(pre[preRootIndex + 1]);
            buildTree(pre, preRootIndex + 1, vin, map, set, root.right);
        }

    }

    private boolean isExistInTree(int target, Set<Integer> set) {
        if (set.contains(target)) return true;
        return false;
    }
}