package com.wagawin.test.controller;

import com.wagawin.test.entity.ParentSummary;
import com.wagawin.test.repository.ParentSummaryRepository;
import com.wagawin.test.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(PersonsController.class)
public class PersonsMockMvcWithContextTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParentSummaryRepository parentSummaryRepository;

    @Test
    public void canRetrieveWhenExists() throws Exception {
        List<ParentSummary> parentSummaryList = TestUtils.createParentSummaryList();

        given(parentSummaryRepository.findAll())
                .willReturn(parentSummaryList);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/persons/children")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void canRetrieveWhenDoesNotExist() throws Exception {
        // given
        given(parentSummaryRepository.findParentSummaries())
                .willReturn(Collections.emptyList());

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/persons/children")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }
}
