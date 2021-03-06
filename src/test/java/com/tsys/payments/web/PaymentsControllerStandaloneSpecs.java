package com.tsys.payments.web;

import com.tsys.payments.service.local.PaymentsService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)

@Tags({
        @Tag("Standalone"),
        @Tag("UnitTest")
})
public class PaymentsControllerStandaloneSpecs {

    @Mock
    private PaymentsService paymentsService;

    @InjectMocks
    private PaymentsController paymentsController;


    private MockMvc mockMvc;

    @BeforeEach
    public void buildMockMvc() {
        // MockMvc standalone approach
        mockMvc = MockMvcBuilders.standaloneSetup(paymentsController)
                .build();
    }

    @Test
    public void health() throws Exception {
        // Given
        final var request = givenRequestFor("/ping", false);
        // When
        final ResultActions resultActions = whenTheRequestIsMade(request);
        // Then
        thenExpect(resultActions,
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().bytes("{ 'PONG' : 'PaymentsController is running fine!' }".getBytes()));
    }

    @Test
    public void homesToIndexPage() throws Exception {
        final var request = givenRequestFor("/", false);
        final ResultActions resultActions = whenTheRequestIsMade(request);
        thenExpect(resultActions,
                MockMvcResultMatchers.status().isOk());
    }

    private MockHttpServletRequestBuilder givenRequestFor(String url, boolean isPostRequest) {
        final MockHttpServletRequestBuilder builder =
                isPostRequest ? MockMvcRequestBuilders.post(url)
                        : MockMvcRequestBuilders.get(url);
        return builder.characterEncoding("UTF-8");
    }

    private ResultActions whenTheRequestIsMade(MockHttpServletRequestBuilder request) throws Exception {
        return mockMvc.perform(request);
    }

    private void thenExpect(ResultActions resultActions, ResultMatcher... matchers) throws Exception {
        resultActions.andExpect(ResultMatcher.matchAll(matchers));
    }
}
