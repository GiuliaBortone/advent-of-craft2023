package greeting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreeterTest {
    @Test
    void saysHello() {
        var greeter = GreeterFactory.identifyAndCreate();

        assertThat(greeter.greet())
                .isEqualTo("Hello.");
    }

    @Test
    void saysHelloFormally() {
        var greeter = GreeterFactory.identifyAndCreate("formal");

        assertThat(greeter.greet())
                .isEqualTo("Good evening, sir.");
    }

    @Test
    void saysHelloCasually() {
        var greeter = GreeterFactory.identifyAndCreate("casual");

        assertThat(greeter.greet())
                .isEqualTo("Sup bro?");
    }

    @Test
    void saysHelloIntimately() {
        var greeter = GreeterFactory.identifyAndCreate("intimate");

        assertThat(greeter.greet())
                .isEqualTo("Hello Darling!");
    }
}
