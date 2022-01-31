package main.pathfinder.informed;

/**
 * SearchTreeNode that is used in the Search algorithm to construct the Search
 * tree.
 */
class SearchTreeNode {
    // [!] TODO: You're free to modify this class to your heart's content
    
    MazeState state;
    String action;
    SearchTreeNode parent;
    int cost,futureCost;
    // [!] TODO: Any other fields you want to add
    
    /**
     * Constructs a new SearchTreeNode to be used in the Search Tree.
     * 
     * @param state The MazeState (row, col) that this node represents.
     * @param action The action that *led to* this state / node.
     * @param parent Reference to parent SearchTreeNode in the Search Tree.
     */
    public SearchTreeNode (MazeState state, String action, SearchTreeNode parent, int cost, int futureCost) {
        // [!] TODO: You may modify the constructor as you please
        this.state = state;
        this.action = action;
        this.parent = parent;
        this.cost = cost;
        this.futureCost = futureCost;
    }
    
    // [!] TODO: Any methods you wish to define, private or otherwise
    
}