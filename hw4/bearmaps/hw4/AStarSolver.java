package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private ArrayHeapMinPQ<Vertex> PQ;
    private double[] distTo;
    private SolverOutcome outcome;
    private int numStatesExplored;
    private double solutionWeight;
    private double explorationTime;
    private List<Vertex> solution;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        PQ = new ArrayHeapMinPQ<Vertex>();
        numStatesExplored = 0;
        solutionWeight = 0.;
        solution = new ArrayList<Vertex>();
        distTo = new double[10000];


        PQ.add(start, 0);
        while (PQ.size() != 0) {

            Vertex v = PQ.removeSmallest();
            numStatesExplored++;

            if (v.equals(end)) {
                solution.add(v);
                outcome = SolverOutcome.SOLVED;
                explorationTime = sw.elapsedTime();
                solutionWeight = distTo[(int)v];
                return;
            }
            for (WeightedEdge<Vertex> e: input.neighbors(v)) {
                relax(e, input, end);
            }
            solution.add(v);
        }
        outcome = SolverOutcome.UNSOLVABLE;
        explorationTime = sw.elapsedTime();
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
        if (distTo[(int) q] > distTo[(int) p] + w) {
            distTo[(int) q] = distTo[(int) p] + w;
            if (PQ.contains(q)) {
                PQ.changePriority(q, distTo[(int)q] + input.estimatedDistanceToGoal(q,goal));
            } else {
                PQ.add(q, distTo[(int)q] + input.estimatedDistanceToGoal(q,goal));
            }
        }
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
