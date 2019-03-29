import java.util.ArrayList;

public class NFA extends FA {
    public NFA(ArrayList<String[]> transitions, String[] states, ArrayList<String> alphabet, String startState, ArrayList<String> finalStates){
        super(transitions, states, alphabet, startState, finalStates);
    }
    public void verifyInput(ArrayList<String> values){
        boolean status;
        String input = "";
        System.out.println("=====[NFA RESULTS]=====");
        for (String value : values) {
            input = value;
            status = verify(input, this.getStartState());

            if (status) System.out.println("The string " + input + " has been accepted.");
            else System.out.println("The string " + input + " has been rejected.");
        }
    }
    private boolean verify(String input, State currentState){
        if (input.length()==0){
            return (currentState.isFinal());
        }
        // If we have a transition for the actual symbol that eventually is true, awesome!
        // FIRST LOOP IS FOR CORRECT FINDINGS
        ArrayList<Transition> allTrans = currentState.getTransitions();
        for (int i = 0; i<allTrans.size(); i++){
            Transition temp = allTrans.get(i);
            if (temp.getSymbol().equals(input.charAt(0)+"")){
                // If the rest of the string works with the next state from this specific transition, awesome.
                if (verify(shrinkInput(input),temp.getTo())) return true;
            }
            if (temp.getSymbol().equals("e")){
                if (verify(input, temp.getTo())) return true;
            }
        }
//        if (currentState.hasTransition(input.charAt(0)+"")){
//            if (verify(shrinkInput(input), currentState)) return true;
//        }
//        // If we didn't have a path thus far with the symbol, check for "e"!
//        if (currentState.hasTransition("e")){
//            if (verify(input, currentState)==true) return true;
//        }
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
