package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private WorldState initial;
    private List<WorldState> solution;
    public class SearchNode {
        private WorldState state;
        private int distanceWithInitial;
        private WorldState previous;

        public SearchNode(WorldState state, int distanceWithInitial, WorldState previous) {
            this.state = state;
            this.distanceWithInitial = distanceWithInitial;
            this.previous = previous;
        }
    }
    private class NodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            int o1Edtg = o1.state.estimatedDistanceToGoal();
            int o2Edtg = o2.state.estimatedDistanceToGoal();
            int o1Priority = o1.distanceWithInitial + o1Edtg;
            int o2Priority = o2.distanceWithInitial + o2Edtg;
            return o1Priority - o2Priority;
        }
    }
    /*
    Constructor which solves the puzzle, computing
    everything necessary for moves() and solution() to
    not have to solve the problem again. Solves the
    puzzle using the A* algorithm. Assumes a solution exists.
    */
    public Solver(WorldState initial){
        this.initial = initial;
        SearchNode initialNode = new SearchNode(initial, 0, null);
        MinPQ<SearchNode> pq = new MinPQ(new NodeComparator());
        this.solution = new ArrayList<>();
        pq.insert(initialNode);
        while (!pq.isEmpty()) {
            SearchNode min = pq.delMin();
            solution.add(min.state);
            if (min.state.isGoal()) { return; }
            for (WorldState i : min.state.neighbors()) {
                pq.insert(new SearchNode(i, min.distanceWithInitial+1, min.state));
            }
        }
    }
    /*
    Returns the minimum number of moves to solve the puzzle starting
    at the initial WorldState.
     */
    public int moves(){
        return initial.estimatedDistanceToGoal();
    }
    /*
    Returns a sequence of WorldStates from the initial WorldState
    to the solution.
     */
    public Iterable<WorldState> solution(){
        return this.solution;
    }
}
