import java.util.ArrayList;

public class State {
    private String name;
    private boolean isFinal;
    private ArrayList<Transition> transitions = new ArrayList<Transition>();

    public State(String name, boolean isFinal, ArrayList<Transition> transitions){
        this.name = name;
        this.isFinal = isFinal;
        this.transitions = transitions;
    }

    public String getName() {
        return this.name;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public ArrayList<Transition> getTransitions() {
        return this.transitions;
    }

    public Transition hasTransition(String name){
        for (Transition item:this.transitions){
            if (item.getSymbol().equals(name)) return item;
        }
        return null;
    }
}
