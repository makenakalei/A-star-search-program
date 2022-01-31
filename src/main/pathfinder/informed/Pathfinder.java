package main.pathfinder.informed;

import java.util.*;

//import main.pathfinder.uninformed.SearchTreeNode;

/**
 * Maze Pathfinding algorithm that implements a basic, uninformed, breadth-first tree search.
 */
public class Pathfinder {
    
    /**
     * Given a MazeProblem, which specifies the actions and transitions available in the
     * search, returns a solution to the problem as a sequence of actions that leads from
     * the initial to a goal state.
     * 
     * @param problem A MazeProblem that specifies the maze, actions, transitions.
     * @return An ArrayList of Strings representing actions that lead from the initial to
     * the goal state, of the format: ["R", "R", "L", ...]
     */
    public static ArrayList<String> solve (MazeProblem problem) {
    	Queue<SearchTreeNode> frontier = new LinkedList<SearchTreeNode>();
    	Set<MazeState> goals = problem.getGoalStates();
    	boolean keyFound = false;
    	SearchTreeNode initialState = new SearchTreeNode(problem.getInitialState(), "", null, 0, distance(problem.getInitialState(), goals, problem.getKeyState(), keyFound));
    	ArrayList<String> actions = new ArrayList<String>();
    	frontier.add(initialState);
    	
    	
    	
    	return null;
        
    }
    
    private static int distance (MazeState current, Set<MazeState> goals, MazeState keyState, boolean keyFound) {
    	if(keyFound) {
    		ArrayList<Integer> distances = new ArrayList<Integer>();
    		for (MazeState goal: goals) {
    			distances.add(Math.abs(goal.col - current.col) + Math.abs(goal.row - current.row));
    	    }
    		Collections.sort(distances);
    		return distances.get(0);
    	} else {
    		return Math.abs(keyState.col - current.col) + Math.abs(keyState.row - current.row);
    	}
    }
    	
    
}
