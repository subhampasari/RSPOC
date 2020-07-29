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
public class RefMarkerController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/api/v1/createNewRefMarker")
    public ResponseEntity createNew(@RequestBody RefMarker object) {
        jdbcTemplate.update("INSERT INTO ref_markers(refName, experience, product, feature, timestamp) VALUES (?,?,?,?,?)",
                object.refName, object.experience, object.product, object.feature, object.timestamp);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/api/v1/deleteRefMarker")
    public ResponseEntity delete(@RequestParam String refName) {
        jdbcTemplate.update("delete from ref_markers where refName = ?", refName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getRefMarker")
    public List<Map<String, Object>> getRefMarker(@RequestParam String refName) {

        ArrayList<Entity> allEntities = new ArrayList<>();

        return jdbcTemplate.queryForList("SELECT * FROM ref_markers where refname = ?", refName);
    }

}
