import java.util.ArrayList;

public class DFA extends FA {
    State sinkState = new State("phi");
    public DFA(ArrayList<String[]> transitions, String[] states, ArrayList<String> alphabet, String startState, ArrayList<String> finalStates){
        super(transitions, states, alphabet, startState, finalStates);
        // Go ahead and add the self-loops to the sink state
        for(String symbol : alphabet) sinkState.addTransition(new Transition(sinkState, symbol, sinkState));
        cleanup();
    }
    public void verifyInput(ArrayList<String> values){
        boolean status;
        String input = "";
        System.out.println("=====[DFA RESULTS]=====");
        for (String value : values) {
            input = value;
            status = verify(input);
            if (status) {

                System.out.println("The string " + input + " has been accepted.");
            } else System.out.println("The string " + input + " has been rejected.");
        }
    }
    private boolean verify(String input){
        State currentState = this.getStartState();
        for (int i = 0; i<input.length(); i++){
            String symbol = input.charAt(i)+"";
            currentState = currentState.nextState(symbol);
        }
        return currentState.isFinal();
    }
    // adds all the missing transitions to the DFA
    private void cleanup(){
        for (State currentState : this.getStates()){
            for (String symbol : this.getAlphabet()){
                if (!currentState.hasTransition(symbol)){
                    Transition temp = new Transition(currentState, symbol, sinkState);
                    currentState.addTransition(temp);
                }
            }
        }
    }
}
