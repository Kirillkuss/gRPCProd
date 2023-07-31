package com.rpc.itrail.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.rpc.itrail.model.Document;
import com.rpc.itrail.rest.IDocument;
import com.rpc.itrail.service.DocumentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DocumentController implements IDocument {

    private final DocumentService documentService;
    
    @Override
    public Document findById(Long id) {
        return documentService.findById(id);
    }

    @Override
    public List<Document> findAll() {
        return documentService.findAll();
    }

    @Override
    public Document addDocument( Document document) {
        return documentService.addDocument( document );
    }
    
}
