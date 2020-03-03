package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private ArrayHeapMinPQ<Vertex> PQ;
    private double[] distTo;
    private SolverOutcome outcome;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        PQ = new ArrayHeapMinPQ<Vertex>();
        PQ.add(start, 0);
        while (PQ.size() != 0) {
            Vertex v = PQ.removeSmallest();
            for (WeightedEdge<Vertex> e: input.neighbors(v)) {
                relax(e,input,end);

            }
        }
    }



    }

    private void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> input, Vertex goal) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if (distTo[(int)q] > distTo[(int)p] + w) {
            distTo[(int)q] = distTo[(int)p] + w;
            if (PQ.contains(q)) {
                PQ.changePriority(q, input.estimatedDistanceToGoal(q, goal));
            } else {
                PQ.add(q, input.estimatedDistanceToGoal(q, goal));
            }
        }

    @Override
    public SolverOutcome outcome() {
        if (time > timeout) {
            return SolverOutcome.TIMEOUT;
        } else if (PQ.size() == 0) {
            return SolverOutcome.UNSOLVABLE;
        } else {
            return SolverOutcome.SOLVED;
        }
        return outcome;
    }
    @Override
    public List<Vertex> solution() {}

    @Override
    public double solutionWeight() {}

    @Override
    public int numStatesExplored() {}

    @Override
    public double explorationTime() {}
}
