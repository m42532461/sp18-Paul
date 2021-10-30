package lab11.graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        marked[s] = true;
        announce();
        Queue q = new edu.princeton.cs.algs4.Queue<Integer>();
        q.enqueue(s);

        while (!q.isEmpty()) {
            s = (int)q.dequeue();
            if (s == t) {
                targetFound = true;
            }

            if (targetFound) {
                return;
            }
            for (int i : maze.adj(s)){
                if (!marked[i]) {
                    q.enqueue(i);
                    marked[i] = true;
                    edgeTo[i] = s;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs(s);
    }
}

