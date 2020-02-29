package bearmaps;
import java.util.List;

public class KDTree implements PointSet{
    private Node root;
    private boolean HORIZONTAL = false;

    private class Node {
        private Node ld;
        private Node ru;
        private Point point;
        private boolean splitDim;
        public Node(Point p, boolean splitDim) {
            this.point = p;
            this.splitDim = splitDim;
            this.ld = null;
            this.ru = null;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p: points) {
            root = insert(p, root, HORIZONTAL);
        }

    }

    public Node insert(Point p, Node n, boolean splitDim) {
        if (n == null) {
            return new Node(p, splitDim);
        } else if (p == n.point) {
            return n;
        }
        int cmp = comparePoints(p, n.point, splitDim);
        if (cmp < 0) {
            n.ld = insert(p, n.ld, !splitDim);
        } else {
            n.ru = insert(p, n.ru, !splitDim);
        }
        return n;


    }

    private int comparePoints(Point p1, Point p2, boolean splitDim) {
        if (splitDim == HORIZONTAL) {
            return Double.compare(p1.getX(), p2.getX());
        }
        return Double.compare(p1.getY(), p2.getY());
    }
    @Override
    public Point nearest(double x, double y) {
        Point p = new Point(x, y);
        return helperNearest(p, root, root.point);
    }

    private Point helperNearest(Point target,Node n, Point best) {
            if (n == null) {
                return best;
            }
            double bestDistance = Point.distance(target, best);
            double currDistance = Point.distance(target, n.point);
            if (Double.compare(currDistance, bestDistance) < 0) {
                best = n.point;
            }

            Node goodSideNode;
            Node badSideNode;
            int cmp = comparePoints(target, n.point, n.splitDim);
            if (cmp < 0) {
                goodSideNode = n.ld;
                badSideNode = n.ru;
            } else {
                goodSideNode = n.ru;
                badSideNode = n.ld;
            }

            best = helperNearest(target,goodSideNode, best);
            if (isWorthLooking(n, target, best)) {
                best = helperNearest(target, badSideNode, best);
            }
            return best;
    }

    private boolean isWorthLooking(Node node, Point target, Point best) {
        double distToBest = Point.distance(best, target);
        double distToBad;
        if (node.splitDim == HORIZONTAL) {
            distToBad = Point.distance(new Point(node.point.getX(), target.getY()), target);
        } else {
            distToBad = Point.distance(new Point(target.getX(), node.point.getY()), target);
        }
        return Double.compare(distToBad, distToBest) < 0;
    }

}
