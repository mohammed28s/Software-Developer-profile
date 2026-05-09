package com.portfolio.api;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PortfolioApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void publicEndpointsAreAccessible() throws Exception {
        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mockMvc.perform(get("/api/skills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mockMvc.perform(get("/api/experience"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void adminEndpointsRequireAuthentication() throws Exception {
        mockMvc.perform(post("/api/admin/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "Portfolio API",
                                  "description": "Backend service",
                                  "techStack": "Spring Boot"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void authenticatedAdminCanCreateProject() throws Exception {
        mockMvc.perform(post("/api/admin/projects")
                        .with(httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "Portfolio API",
                                  "description": "Backend service for a dynamic developer portfolio",
                                  "techStack": "Spring Boot, PostgreSQL, Docker",
                                  "githubUrl": "https://github.com/example/project"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Portfolio API"))
                .andExpect(jsonPath("$.techStack").value("Spring Boot, PostgreSQL, Docker"));

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Portfolio API"));
    }

    @Test
    void skillProficiencyMustBeBetweenOneAndOneHundred() throws Exception {
        mockMvc.perform(post("/api/admin/skills")
                        .with(httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "skillName": "Java",
                                  "category": "Backend",
                                  "proficiencyLevel": 150
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("proficiencyLevel must be between 1 and 100"));
    }
}
