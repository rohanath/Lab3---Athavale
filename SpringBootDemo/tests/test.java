import org.junit.*;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class ExampleWebTestCase {

    @Before
    public void prepare() {
        setBaseUrl("http://localhost:8080/login");
    }

    @Test
    public void testLogin() {
        beginAt("http://localhost:3000");
        clickLink("login");
        assertTitleEquals("Login");
        setTextField("username", "rohan@gmail.com");
        setTextField("password", "rohana");
        submit();
        assertTitleEquals(200);
    }
}