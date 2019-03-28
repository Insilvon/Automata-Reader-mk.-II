public class Transition {

    private State from;
    private State to;
    private String symbol;

    public Transition(State from, State to, String symbol){
        this.from = from;
        this.to = to;
        this.symbol = symbol;
    }

    public State getFrom(){
        return this.from;
    }
    public State getTo(){
        return this.to;
    }
    public String getSymbol(){
        return this.symbol;
    }
}
