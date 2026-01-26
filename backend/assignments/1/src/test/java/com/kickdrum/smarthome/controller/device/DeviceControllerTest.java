package com.kickdrum.smarthome.controller.device;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kickdrum.smarthome.dto.request.device.MoveDeviceRequest;
import com.kickdrum.smarthome.security.JwtUtil;
import com.kickdrum.smarthome.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = DeviceController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
@AutoConfigureMockMvc
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeviceService deviceService;

    // 🔥 THIS IS THE MISSING PIECE
    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void moveDevice_success() throws Exception {

        MoveDeviceRequest request = new MoveDeviceRequest();
        request.setTargetRoomId(20L);

        doNothing().when(deviceService)
                .moveDevice(1L, 10L, 20L);

        mockMvc.perform(
                        post("/api/devices/10/move")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Device moved successfully"));
    }
}
