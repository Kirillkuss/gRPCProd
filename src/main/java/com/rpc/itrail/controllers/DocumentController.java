package com.rpc.itrail.controllers;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpc.itrail.DocumentRequest;
import com.rpc.itrail.DocumentResponse;
import com.rpc.itrail.ListDocument;
import com.rpc.itrail.model.Document;
import com.rpc.itrail.repositories.DocumentRepository;
import com.rpc.itrail.rest.IDocument;
import com.rpc.itrail.service.DocumentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DocumentController implements IDocument {

    private final DocumentService documentService;
    private final DocumentRepository documentRepository;
    private final ObjectMapper objectMapper;


    @Override
    public Document findById(Long id) {
        return documentRepository.findById( id ).get();
    }

    @Override
    public List<Document> findAll() {
        
        return documentRepository.findAll();
    }

    @Override
    public Document addDocument( Document document) {
        return documentRepository.save( document );
    }

    @GetMapping("/test")
    public BigInteger test(){
        return documentService.findById();
    }
    
}
