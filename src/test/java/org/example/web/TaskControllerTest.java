package org.example.web;

import org.example.model.Task;
import org.example.service.TaskService;
import org.example.web.vo.TaskRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @Test
    void createTask() throws Exception {
        TaskRequest sampleReq = new TaskRequest();
        sampleReq.setTitle("test");
        sampleReq.setDescription("test description");

        Task sampleTask = Task.builder()
                .id(1L)
                .title("test")
                .description("test description")
                .build();

        when(taskService.add(eq(sampleReq.getTitle()),
                            eq(sampleReq.getDescription()),
                            eq(sampleReq.getDueDate())))
                .thenReturn(sampleTask);

        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"test\", \"description\": \"test description\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"test\",\"description\":\"test description\"}"))
                .andReturn();
    }
}