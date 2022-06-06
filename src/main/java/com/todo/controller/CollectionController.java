package com.todo.controller;

import com.todo.entity.CollectionEntity;
import com.todo.model.GeneralResponse;
import com.todo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("collections")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @PostMapping()
    public ResponseEntity<GeneralResponse<CollectionEntity>> addNewCollection(@RequestBody CollectionEntity collection) {
        GeneralResponse<CollectionEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        CollectionEntity data = null;

        try {
            data = collectionService.saveCollection(collection);

            response.setMessage("Colleción creada correctamente");
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
    public ResponseEntity<GeneralResponse<List<CollectionEntity>>> getCollections() {

        GeneralResponse<List<CollectionEntity>> response = new GeneralResponse<>();
        HttpStatus status = null;
        List<CollectionEntity> data = null;
        String message = "";

        try {

            data = collectionService.getAllCollections();

            if (data.isEmpty()) {
                response.setErrorCode(1);
                response.setMessageResult("No se econtraron collecciones");
            } else {
                response.setErrorCode(0);
                response.setMessageResult("Colleciones encontradas");
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
    public ResponseEntity<GeneralResponse<CollectionEntity>> getCollectionById(@PathVariable("id") Integer id) {

        GeneralResponse<CollectionEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        CollectionEntity data = null;
        String message = "";

        try {

            data = collectionService.getCollectionById(id);

            if (data == null) {
                response.setErrorCode(1);
                response.setMessageResult("No se econtró ninguna collección");
            } else {
                response.setErrorCode(0);
                response.setMessageResult("Colleción encontrada");
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
    public ResponseEntity<GeneralResponse<CollectionEntity>> updateCollection(@RequestBody CollectionEntity collection, @PathVariable("id") Integer id) {
        GeneralResponse<CollectionEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        CollectionEntity data = null;
        String message = "";

        try {
            data = collectionService.getCollectionById(id);

            if (data == null) {
                response.setErrorCode(1);
                response.setMessageResult("No se encontró la colección");
            } else {
                System.out.println("Collecion: " + data.getCollection_name());
                response.setErrorCode(0);
                response.setMessageResult("Se encontró la colección: " + data.getCollection_name());
            }

            data.setCollection_name(collection.getCollection_name());
            data.setCollection_icon(collection.getCollection_icon());
            collectionService.saveCollection(data);
            message = "Colección actualizado correctamente";
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

            collectionService.deleteCollectionById(id);
            response.setErrorCode(0);
            response.setMessageResult("Colleción eliminada correctamente");

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
