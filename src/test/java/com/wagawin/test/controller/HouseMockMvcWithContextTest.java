package com.wagawin.test.controller;

import com.wagawin.test.entity.House;
import com.wagawin.test.entity.HouseType;
import com.wagawin.test.repository.HouseRepository;
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
@WebMvcTest(HouseController.class)
public class HouseMockMvcWithContextTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private HouseRepository houseRepository;

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        House testHouse = TestUtils.createHouse(HouseType.FLAT);
        Optional<House> optionalHouse = Optional.of(testHouse);

        given(houseRepository.findByPersonId(1L))
                .willReturn(optionalHouse);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/house/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
        // given
        given(houseRepository.findById(2L))
                .willReturn(Optional.empty());

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/house/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }
}
