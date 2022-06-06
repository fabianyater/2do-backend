package com.todo.controller;

import com.todo.entity.CollectionEntity;
import com.todo.entity.TaskEntity;
import com.todo.model.GeneralResponse;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<GeneralResponse<TaskEntity>> addNewTask(@RequestBody TaskEntity task) {
        GeneralResponse<TaskEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        TaskEntity data = null;

        try {
            data = taskService.saveTask(task);

            response.setMessage("Tarea creada correctamente");
            response.setSuccess(true);
            response.setData(data);
            status = HttpStatus.OK;

        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage("Algo ha salido mal. Error: " + e.getLocalizedMessage());
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    @GetMapping
    public ResponseEntity<GeneralResponse<List<TaskEntity>>> getTasks() {

        GeneralResponse<List<TaskEntity>> response = new GeneralResponse<>();
        HttpStatus status = null;
        List<TaskEntity> data = null;
        String message = "";

        try {

            data = taskService.getAllTasks();

            if (data.isEmpty()) {
                response.setErrorCode(1);
                response.setMessageResult("No se econtraron tareas");
            } else {
                response.setErrorCode(0);
                response.setMessageResult("Tareas encontradas");
            }

            message = "Successful transaction";

            response.setMessage(message);
            response.setSuccess(true);
            response.setData(data);
            status = HttpStatus.OK;

        } catch (Exception e) {

            String msg = "Something has failed. Please contact suuport.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage(msg);
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralResponse<TaskEntity>> getTaskById(@PathVariable("id") Integer id) {

        GeneralResponse<TaskEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        TaskEntity data = null;
        String message = "";

        try {

            data = taskService.getTaskById(id);

            if (data == null) {
                response.setErrorCode(1);
                response.setMessageResult("No se econtró ninguna tarea");
            } else {
                response.setErrorCode(0);
                response.setMessageResult("Tarea encontrada");
            }

            message = "Successful transaction";

            response.setMessage(message);
            response.setSuccess(true);
            response.setData(data);
            status = HttpStatus.OK;

        } catch (Exception e) {

            String msg = "Something has failed. Please contact suuport.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage(msg);
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<GeneralResponse<TaskEntity>> updateTask(@RequestBody TaskEntity task, @PathVariable("id") Integer id) {
        GeneralResponse<TaskEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        TaskEntity data = null;
        String message = "";

        try {
            data = taskService.getTaskById(id);

            if (data == null) {
                response.setErrorCode(1);
                response.setMessageResult("No se encontró la tarea");
            } else {
                System.out.println("Tarea: " + data.getTask_title());
                response.setErrorCode(0);
                response.setMessageResult("Se encontró la tarea: " + data.getTask_title());
            }

            data.setTask_title(task.getTask_title());
            data.setTask_comment(task.getTask_comment());
            data.setTask_due_date(task.getTask_due_date());
            data.setTask_reminder(task.getTask_reminder());
            data.setTask_state(task.getTask_state());
            taskService.saveTask(data);
            message = "Tarea actualizado correctamente";
            response.setMessage(message);
            response.setSuccess(true);
            response.setData(data);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            message = "Something has failed. Please contact suuport.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage(message);
            response.setSuccess(false);
        }
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GeneralResponse<Integer>> deleteCollection(@PathVariable("id") Integer id) {
        GeneralResponse<Integer> response = new GeneralResponse<>();
        HttpStatus status = null;

        try {

            taskService.deleteTaskById(id);
            response.setErrorCode(0);
            response.setMessageResult("Tarea eliminada correctamente");

            response.setMessage("Successful transaction");
            response.setSuccess(true);
            response.setData(id);
            status = HttpStatus.OK;

        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage("Something has failed. Please contact suuport.");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

}
