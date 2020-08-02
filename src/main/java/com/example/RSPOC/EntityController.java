package com.example.RSPOC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EntityController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/api/v1/createNewEntity")
    public ResponseEntity createNew(@RequestBody Entity object) {
        jdbcTemplate.update("INSERT INTO entities(type, value, timestamp) VALUES (?,?,?)", object.type, object.value, object.timestamp);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getEntities")
    public List<Map<String, Object>> greeting() {

        ArrayList<Entity> allEntities = new ArrayList<>();

        return jdbcTemplate.queryForList("SELECT type, value, timestamp FROM entities");
    }
}
