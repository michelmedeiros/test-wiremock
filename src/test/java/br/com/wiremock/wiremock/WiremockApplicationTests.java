package br.com.wiremock.wiremock;

import br.com.wiremock.wiremock.br.com.controller.WireMockController;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static sun.nio.cs.Surrogate.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WiremockApplicationTests {

	@InjectMocks
	private WireMockController controller;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);

	@Test
	public void executeUrlOnly() throws IOException {

		stubFor(get(urlEqualTo("some/thing"))
				.willReturn(aResponse()
				.withStatus(200)
				.withStatusMessage("Everything was just fine!")
				.withHeader("Content-Type", "text/plain")
				.withBody("Hello Ana"))
		);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("http://localhost:8089/some/thing");
		HttpResponse response = httpClient.execute(request);
		assertEquals(200, response.getStatusLine().getStatusCode());

		stubFor(get("/json")
				.willReturn(okJson("{ \"message\": \"Hello\" }")));

	}
}
