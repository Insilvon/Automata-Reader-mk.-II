import java.lang.reflect.Array;
import java.util.ArrayList;

public class FA {
    private ArrayList<State> states;
    public ArrayList<State> getStates(){
        return this.states;
    }

    private ArrayList<String> finalStates;
    public ArrayList<String> getFinalStates(){
        return this.finalStates;
    }

    private ArrayList<String> alphabet;
    public ArrayList<String> getAlphabet(){
        return this.alphabet;
    }

    private State startState;
    public State getStartState(){
        return this.startState;
    }

    public FA(ArrayList<String[]> transitions, String[] states, ArrayList<String> alphabet, String startState, ArrayList<String> finalStates) {
        this.alphabet = alphabet;
        this.finalStates = finalStates;
        this.states = createStates(states, startState);
        createTransitions(transitions);
    }

    /**
     * Loops through all the states given in the input file and
     * creates State objects for them. The States lack transitions,
     * but contain the state name and whether or not the state is a
     * final state or not.
     * @param states
     * @return
     */
    private ArrayList<State> createStates(String[] states, String startState){
        ArrayList<State> newStates = new ArrayList<State>();
        for (String name:states){
            State temp = new State(name);
            if (name.equals(startState)) this.startState=temp;
            if (this.finalStates.contains(name)) temp.setFinal(true);
            newStates.add(temp);
        }
        return newStates;
    }

    /**
     * Loops through all of the transition data pulled from the input file and
     * creates new Transition objects based off of them.
     * It will also add those Transition objects to the corresponding From state.
     * @param values
     */
    private void createTransitions(ArrayList<String[]> values){
        ArrayList<Transition> newTrans = new ArrayList<Transition>();
        for (String[] transData : values){
            String first = transData[0];
            String second = transData[2];
            String symbol = transData[1];
            State firstState = this.getState(first);
            State secondState = this.getState(second);
            Transition temp = new Transition(firstState, symbol, secondState);
            // Now that you made the object, attach it to this state
            this.getState(first).addTransition(temp);
        }
    }

    /**
     * Returns the state that matches the input name specified
     * @param name
     * @return
     */
    public State getState(String name){
        for(State item:states){
            if (item.getName().equals(name)) return item;
        }
        return null;
    }
}