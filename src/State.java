import java.util.ArrayList;

public class State {
    private String name;
    private boolean isFinal;
    private ArrayList<Transition> transitions = new ArrayList<Transition>();

    public State(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // set whether this is a final state or not
    public void setFinal(boolean value){
        this.isFinal = value;
    }

    // tell whether this is a final state or not
    public boolean isFinal() {
        return this.isFinal;
    }

    public ArrayList<Transition> getTransitions() {
        return this.transitions;
    }

    // Checks to see if this State has an arrow for the symbol you give it
    public boolean hasTransition(String name){
        for (int i = 0; i<transitions.size(); i++){
            if (transitions.get(i).getSymbol().equals(name)) return true;
        }
//        for (Transition item:this.transitions){
//            if (item.getSymbol().equals(name)) return true;
//        }
        return false;
    }

    /**
     * Returns the next state to go to with the current symbol
     * @param name
     * @return
     */
    public State nextState(String name){
        Transition temp = this.getTransition(name);
        return temp.getTo();
    }
    public Transition getTransition(String name){
        for (Transition item:this.transitions){
            if (item.getSymbol().equals(name)) return item;
        }
        return null;
    }
    // Adds a transition to this State's array of arrows
    public void addTransition(Transition pointer){
        this.transitions.add(pointer);
    }
}
