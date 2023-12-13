package greeting;

public class NormalGreeter implements Greeter {
    @Override
    public String greet() {
        return "Hello.";
    }
}
