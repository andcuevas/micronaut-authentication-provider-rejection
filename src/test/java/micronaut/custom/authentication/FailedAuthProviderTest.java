package micronaut.custom.authentication;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@MicronautTest
public class FailedAuthProviderTest {
    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeAll
    public static void setupServer() {

        server = ApplicationContext.run(EmbeddedServer.class);

        client = server
                .getApplicationContext()
                .createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    public static void stopServer() {
        if (client != null) {
            client.stop();
        }
        if (server != null) {
            server.stop();
        }
    }

    @Test
    public void test() {

        try {
            HttpResponse<Object> response = client.toBlocking().exchange(HttpRequest
                    .POST("/login", "{\"username\":\"a\",\"password\":\"b\"}"));
            fail("The result should be Unauthorized and not " + response.getStatus());
        } catch (HttpClientResponseException e) {
            assertThat(e.getMessage(), is("Unauthorized"));
        }

    }

}