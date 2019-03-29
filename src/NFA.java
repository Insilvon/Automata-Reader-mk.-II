import java.util.ArrayList;

public class NFA extends FA {
    public NFA(ArrayList<String[]> transitions, String[] states, ArrayList<String> alphabet, String startState, ArrayList<String> finalStates){
        super(transitions, states, alphabet, startState, finalStates);
    }

    public boolean verify(String input, State currentState){
        if (input.length()==0){
            return (currentState.isFinal());
        }
        // If we have a transition for the actual symbol that eventually is true, awesome!
        if (currentState.hasTransition(input.charAt(0)+"")){
            if (verify(shrinkInput(input), currentState)) return true;
        }
        // If we didn't have a path thus far with the symbol, check for "e"!
        if (currentState.hasTransition("e")){
            if (verify(input, currentState)==true) return true;
        }
        // If we haven't returned true yet, then I'm sorry this isn't valid
        return false;
    }

    private String shrinkInput(String input){
        String nextInput = "";
        for (int i = 1; i<input.length(); i++){
            nextInput+=input.charAt(i);
        }
        return nextInput;
    }
}
