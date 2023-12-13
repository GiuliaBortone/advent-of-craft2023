package greeting;

public class FormalGreeter implements Greeter {
    @Override
    public String greet() {
        return "Good evening, sir.";
    }
}
