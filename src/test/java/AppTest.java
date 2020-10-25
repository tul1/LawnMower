import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AppTest {

    private ByteArrayOutputStream testOutput;

    @BeforeEach
    public void setup() {
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void main() throws FileNotFoundException {
        // given

        // when
        App.main(new String[] {"src/test/resources/file.txt"});

        // then
        assertEquals("1 3 N\n5 1 E\n", testOutput.toString());
    }

    @Test
    void mainThrowFileNotFound() {
        // given

        // when
        assertThrows(FileNotFoundException.class, () -> App.main(new String[] {""}));

        // then
    }

    @Test
    void mainThrowIllegalArgument() {
        // given

        // when
        assertThrows(IllegalArgumentException.class, () -> App.main(new String[] {}));
        assertThrows(IllegalArgumentException.class, () -> App.main(new String[] {"hello", "world"}));

        // then
    }

    @Test
    void mainThrowIllegalState() {
        // given

        // when
        assertThrows(IllegalStateException.class, () -> App.main(
                new String[] {"src/test/resources/incorrect_command.txt"}));

        // then
    }
}