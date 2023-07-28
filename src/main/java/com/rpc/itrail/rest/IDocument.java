package com.rpc.itrail.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpc.itrail.DocumentRequest;
import com.rpc.itrail.DocumentResponse;
import com.rpc.itrail.ListDocument;
import com.rpc.itrail.model.Document;

@RequestMapping( "doc" )
public interface IDocument {
    
    @GetMapping("/find")
    public Document findById( Long id );

    @GetMapping("/all")
    public List<Document> findAll();

    @PostMapping("/add")
    public Document addDocument( Document request );

}
