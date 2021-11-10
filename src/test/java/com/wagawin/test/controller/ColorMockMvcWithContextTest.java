package com.wagawin.test.controller;

import com.wagawin.test.entity.Child;
import com.wagawin.test.entity.Son;
import com.wagawin.test.repository.ChildRepository;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ColorController.class)
public class ColorMockMvcWithContextTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ChildRepository childRepository;

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        Child testChild = TestUtils.createChild();
        Son son = TestUtils.createSon(testChild);
        Optional<Child> optionalSon = Optional.of(son);

        given(childRepository.findById(1L))
                .willReturn(optionalSon);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/color/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void canNotRetrieveByIdWhenIsNotSonOrDaughter() throws Exception {
        Child testChild = TestUtils.createChild();
        Optional<Child> optionalSon = Optional.of(testChild);

        given(childRepository.findById(1L))
                .willReturn(optionalSon);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/color/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
        // given
        given(childRepository.findById(2L))
                .willReturn(Optional.empty());

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/color/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }
}
