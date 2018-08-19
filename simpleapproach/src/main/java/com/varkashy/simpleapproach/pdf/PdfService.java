package com.varkashy.simpleapproach.pdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
@RestController
public class PdfService {

    @GetMapping(path = "/pdf",produces = "application/pdf")
    public byte[] generatePdf(){
        try {
            FileInputStream fis = new FileInputStream(new File(this.getClass().getResource( "/helloWorld.pdf" ).toURI()));
            return IOUtils.toByteArray(fis);
        }
        catch(IOException ex){
            System.out.println(ex);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "hello world".getBytes();
    }
}
