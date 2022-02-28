import model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
解决方法：
1.创建根节点，并为根节点赋值
2.构造左子树
3.构建右子树
 */
public class Solution1 {
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
        for (int i = 0; i < vin.length; i++) map.put(vin[i], i);
        return buildTree(pre, 0, pre.length - 1, vin, 0, vin.length, map);
    }

    /*
     *前序遍历：{1,2,4,7,3,5,6,8}
     *中序遍历：{4,7,2,1,5,3,8,6}
     * */
    TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                       int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {

        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1, inMap);

        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRoot + 1, inEnd, inMap);

        return root;
    }

}
