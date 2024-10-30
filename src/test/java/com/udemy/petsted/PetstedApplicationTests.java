package com.udemy.petsted;

import com.udemy.petsted.users.UserEntity.ApiResponse;
import com.udemy.petsted.users.dto.UserCreateRequestDto;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class PetstedApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCreateUser() throws Exception {
		String requestBody = "{\n" +
			"  \"nickname\": \"testUser\",\n" +
			"  \"username\": \"testUsername\",\n" +
			"  \"password\": \"securePassword123\",\n" +
			"  \"email\": \"test@example.com\",\n" +
			"  \"hasPet\": true,\n" +
			"  \"profileUrl\": \"https://example.com/profile.jpg\",\n" +
			"  \"phoneNumber\": \"1234567890\",\n" +
			"  \"birthDate\": \"2000-01-01\",\n" +
			"  \"region\": \"Test Region\",\n" +
			"  \"manner\": 5.0\n" +
			"}";

		mockMvc.perform(post("/users/create")
				.with(csrf()) // CSRF 토큰 추가
				.contentType("application/json")
				.content(requestBody))
			.andExpect(status().isCreated()) // 기대하는 상태 코드 201
			.andExpect(jsonPath("$.status").value("success"))
			.andExpect(jsonPath("$.message").value("User created successfully"));
	}
}