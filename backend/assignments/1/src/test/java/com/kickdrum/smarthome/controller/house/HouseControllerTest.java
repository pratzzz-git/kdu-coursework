package com.kickdrum.smarthome.controller.house;

import com.kickdrum.smarthome.entity.House;
import com.kickdrum.smarthome.security.AuthenticatedUser;
import com.kickdrum.smarthome.security.JwtAuthenticationFilter;
import com.kickdrum.smarthome.security.JwtUtil;
import com.kickdrum.smarthome.service.HouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HouseController.class)
@AutoConfigureMockMvc(addFilters = false)
class HouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseService houseService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void listHouses_success() throws Exception {

        AuthenticatedUser user =
                new AuthenticatedUser(1L, "user@test.com");

        List<House> houses = List.of(
                new House("My Home", "Delhi"),
                new House("Vacation House", "Goa")
        );

        when(houseService.listHousesForUser(1L))
                .thenReturn(houses);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null);

        mockMvc.perform(
                        get("/api/houses")
                                .principal(authentication)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("My Home"))
                .andExpect(jsonPath("$.data[1].name").value("Vacation House"));
    }
}
