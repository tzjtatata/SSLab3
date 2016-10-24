package com.zxx.lyz;

import java.util.ArrayList;

/**
 * Created by 李沅泽 on 2016/10/23.
 */
public class KDTree {

    private final int DIMENSION;
    private Node root;

    public KDTree(int dimension, ArrayList<Data> points) {
        DIMENSION = dimension;
        root = new Node(points, DIMENSION);
        buildTree(root);
    }

    /*
        This function builds Tree in a recurse way.

        @param currentNode shows the Node which the function visits now in travelling.

     */
    public void buildTree(Node currentNode) {
        if (currentNode.LargeOrNot()) {
            currentNode.devide();
            if (currentNode.leftChild != null) buildTree(currentNode.leftChild);
            if (currentNode.leftChild != null) buildTree(currentNode.rightChild);
        } else currentNode.leafOrNot = true;
    }

    public Node search(Data testPoint) {
        Node currentNode = root;
        while (!currentNode.leafOrNot) {
            if (testPoint.getAttr()[currentNode.getDevidedDimensionOrder()] < currentNode.getMidPointOfDevidedDimension()) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
        }
        return currentNode;
    }

}
