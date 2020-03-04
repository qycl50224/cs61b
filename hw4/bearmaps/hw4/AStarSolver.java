package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import java.util.HashMap;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private ArrayHeapMinPQ<Vertex> PQ;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, WeightedEdge<Vertex>> edgeTo;
    private SolverOutcome outcome;
    private int numStatesExplored;
    private double solutionWeight;
    private double explorationTime;
    private List<Vertex> solution;
    private final double INF = Double.POSITIVE_INFINITY;

    /*
     * we find that Vertex can be Integer or String or Char, So
     * it can't just be indexed using array, we can choose Hash Map
     */

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        PQ = new ArrayHeapMinPQ<Vertex>();
        numStatesExplored = 0;
        solutionWeight = 0.;
        solution = new ArrayList<Vertex>();
        distTo = new HashMap<>();
        distTo.put(start, 0.);
        edgeTo = new HashMap<>();

        PQ.add(start, distTo.get(start));
        while (PQ.size() != 0) {

            Vertex v = PQ.removeSmallest();
            numStatesExplored++;

            /*
             * check if we have found the final end, if so, finished
             * and return
             */
            if (v.equals(end)) {
                solution = pathTo(v);
                outcome = SolverOutcome.SOLVED;
                explorationTime = sw.elapsedTime();
                solutionWeight = distTo.get(v);
                return;
            }

            /*
             * the core algorithms going on here
             */
            for (WeightedEdge<Vertex> e: input.neighbors(v)) {
                /*
                 * as hashMap itself didn't contains the key, so when we meet
                 * a new key we should put it in and initiate it's distance to
                 * INF
                 */
                if (!distTo.containsKey(e.to())) {
                    distTo.put(e.to(), INF);
                }
                relax(e, input, end);
            }
        }

        outcome = SolverOutcome.UNSOLVABLE;
        explorationTime = sw.elapsedTime();
        /*
         * check if it is over the up limit
         */
        if (explorationTime > timeout) {
            outcome = SolverOutcome.TIMEOUT;
        }
        solution.clear();
        solutionWeight = 0;
    }

    private void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> input, Vertex goal) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if (distTo.get(q) > distTo.get(p) + w) {
            distTo.replace(q, distTo.get(p) + w);
            edgeTo.put(q,e);
            if (PQ.contains(q)) {
                PQ.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q,goal));
            } else {
                PQ.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q,goal));
            }
        }
    }

    private List<Vertex> pathTo(Vertex v) {
        Stack<Vertex> path = new Stack<>();
        for (WeightedEdge<Vertex> e = edgeTo.get(v); e != null; e = edgeTo.get(e.from())) {
            path.push(e.from());
        }
        List<Vertex> truePath = new ArrayList<>();
        while(!path.isEmpty()) {
            truePath.add(path.pop());
        }
        truePath.add(v);
        return truePath;
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }
    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return explorationTime;
    }
}
