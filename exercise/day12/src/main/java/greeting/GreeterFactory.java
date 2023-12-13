package greeting;

public class GreeterFactory {
    public static Greeter identifyAndCreate(String formality) {
        if (formality.equals("formal")) {
            return new FormalGreeter();
        }

        if (formality.equals("casual")) {
            return new CasualGreeter();
        }

        if (formality.equals("intimate")) {
            return new IntimateGreeter();
        }

        return identifyAndCreate();
    }

    public static Greeter identifyAndCreate() {
        return new NormalGreeter();
    }


}
