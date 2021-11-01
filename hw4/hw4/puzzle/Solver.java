package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private WorldState initial;
    private List<WorldState> solution;
    private Map<WorldState, Integer> dist = new HashMap<>();
    public class SearchNode {
        private WorldState state;
        private int distanceWithInitial;
        private SearchNode previous;

        public SearchNode(WorldState state, int distanceWithInitial, SearchNode previous) {
            this.state = state;
            this.distanceWithInitial = distanceWithInitial;
            this.previous = previous;
        }
    }
    private class NodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            int o1Edtg = getCache(o1);
            int o2Edtg = getCache(o2);
            int o1Priority = o1.distanceWithInitial + o1Edtg;
            int o2Priority = o2.distanceWithInitial + o2Edtg;
            return o1Priority - o2Priority;
        }

        // Optimize to avoid computing estimatedDistanceToGoal for several times
        public int getCache (SearchNode sn) {
            if(!dist.containsKey(sn.state)) {
                dist.put(sn.state, sn.state.estimatedDistanceToGoal());
            }
            return dist.get(sn.state);
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
        SearchNode currentNode = new SearchNode(initial, 0, null);
        MinPQ<SearchNode> pq = new MinPQ(new NodeComparator());
        pq.insert(currentNode);
        this.solution = new ArrayList<>();
        while (!pq.isEmpty()) {
            currentNode = pq.delMin();
            if (currentNode.state.isGoal()) { break; }
            for (WorldState nextState : currentNode.state.neighbors()) {
                // optimize to avoid that currentNode's neighbor is currentNode's parentNode
                if(currentNode.previous != null && nextState.equals(currentNode.previous.state)) {
                    continue;
                }
                pq.insert(new SearchNode(nextState, currentNode.distanceWithInitial+1, currentNode));
            }
        }
        Stack<WorldState> path = new Stack<>();
        for (SearchNode n = currentNode; n != null; n = n.previous) {
            path.push(n.state);
        }
        while (!path.isEmpty()) {
            solution.add(path.pop());
        }
    }
    /*
    Returns the minimum number of moves to solve the puzzle starting
    at the initial WorldState.
     */
    public int moves(){
        return this.solution.size()-1;
    }
    /*
    Returns a sequence of WorldStates from the initial WorldState
    to the solution.
     */
    public Iterable<WorldState> solution(){
        return this.solution;
    }
}
