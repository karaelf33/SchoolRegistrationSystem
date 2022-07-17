package com.example.schoolregistrationsystem.controller;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.service.impl.SearchServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {

    private final SearchServiceImpl searchService;
    public SearchController(SearchServiceImpl searchService) {
        this.searchService = searchService;
    }

    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto search(@PathVariable String code, @RequestParam String codeType) {
        return searchService.search(code,codeType);

    }

}
