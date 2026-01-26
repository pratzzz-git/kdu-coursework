package com.kickdrum.smarthome.integration;

import com.kickdrum.smarthome.entity.*;
import com.kickdrum.smarthome.repository.*;
import com.kickdrum.smarthome.security.AuthenticatedUser;
import com.kickdrum.smarthome.util.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RoomDeviceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseUserRepository houseUserRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    void listRoomsAndDevices_inSameHouse_success() throws Exception {


        House house = houseRepository.save(
                new House("My House", "Test Address")
        );

        Long userId = 1L;
        houseUserRepository.save(
                new HouseUser(house.getId(), userId, Role.ADMIN)
        );
        Room livingRoom = roomRepository.save(
                new Room(house.getId(), "Living Room")
        );

        Room bedroom = roomRepository.save(
                new Room(house.getId(), "Bedroom")
        );

        deviceRepository.saveAll(List.of(
                new Device("KS-101", house.getId(), livingRoom.getId()),
                new Device("KS-102", house.getId(), bedroom.getId())
        ));


        AuthenticatedUser principal =
                new AuthenticatedUser(userId, "test@test.com");

        var auth =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        List.of()
                );

        mockMvc.perform(
                        get("/api/rooms/house/{houseId}", house.getId())
                                .with(authentication(auth))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2));
    }
}
