package main.pathfinder.informed;

import java.util.*;


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
    	if (problem.getKeyState() == null) {
    		return null;
    	}
    	PriorityQueue<SearchTreeNode> frontier = new PriorityQueue<SearchTreeNode>(100, new TreeNodeComparator());
    	Set<MazeState> goals = problem.getGoalStates();
    	Set<MazeState> graveyard = new HashSet<MazeState>();
    	boolean keyFound = false;
    	SearchTreeNode initialState = new SearchTreeNode(problem.getInitialState(), "", null, 0, distance(problem.getInitialState(), goals, problem.getKeyState(), keyFound));
    	ArrayList<String> actions = new ArrayList<String>();
    	frontier.add(initialState);
    	
    	while (!frontier.isEmpty()) {
    		SearchTreeNode expanding = frontier.poll();
    		graveyard.add(expanding.state);
    		Map<String, MazeState> children = problem.getTransitions(expanding.state);
    		
            for (Map.Entry<String, MazeState> child: children.entrySet()) {
            	if (graveyard.contains(child.getValue())) {
            		continue;
            	}
            	if (!keyFound) {
            		if (child.getValue().equals(problem.getKeyState())) {
                		keyFound = true;
                		graveyard.clear();
                		graveyard.add(child.getValue());
                		
                		SearchTreeNode childNode = new SearchTreeNode(child.getValue(), child.getKey(), expanding, expanding.cost + problem.getCost(child.getValue()), expanding.cost + problem.getCost(child.getValue())*(-1));
                		frontier.clear();
                		frontier.add(childNode);
                		break;
                	} else {
            		    SearchTreeNode childNode = new SearchTreeNode(child.getValue(), child.getKey(), expanding, expanding.cost + problem.getCost(child.getValue()), distance(child.getValue(), goals, problem.getKeyState(), keyFound));
            		    frontier.add(childNode);
                	}
            	} else if (keyFound) {
            		if (problem.isGoalState(child.getValue())) {
            			actions.add(child.getKey());
                		while (expanding.parent != null){
                			actions.add(expanding.action);
                			expanding = expanding.parent;
                		}
                		Collections.reverse(actions);
                		return actions;
                	} else {
                		SearchTreeNode childNode = new SearchTreeNode(child.getValue(), child.getKey(), expanding, expanding.cost + problem.getCost(child.getValue()), distance(child.getValue(), goals, problem.getKeyState(), keyFound));
                		frontier.add(childNode);
                	}
            	
            }
         }
    }
    	return null;
 }
    
    private static int distance (MazeState current, Set<MazeState> goals, MazeState keyState, boolean keyFound) {
    		ArrayList<Integer> distances = new ArrayList<Integer>();
    		
    		
    		if (keyFound) {
    			for (MazeState goal: goals) {
        			distances.add(Math.abs(goal.col - current.col) + Math.abs(goal.row - current.row));
        	    }
    			Collections.sort(distances);
        		return distances.get(0);
    		}
    		else {
        		int dist = Math.abs(keyState.col - current.col) + Math.abs(keyState.row - current.row);
        	    
    		    return dist;
    	} 
    }
}
    class TreeNodeComparator implements Comparator<SearchTreeNode>{
        
       
        public int compare(SearchTreeNode n1, SearchTreeNode n2) {
            if (n1.cost + n1.futureCost < n2.cost + n2.futureCost)
                return -1;
            else if (n1.cost + n1.futureCost > n2.cost + n2.futureCost) {
                return 1;                           
            } 
            return 0;
        }
    }    
    	
    

