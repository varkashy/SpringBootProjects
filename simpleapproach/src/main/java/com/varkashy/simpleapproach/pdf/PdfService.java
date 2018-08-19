package com.varkashy.simpleapproach.pdf;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/pdffile")
    public ResponseEntity<byte[]> generatePdfResponseEntity(){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
        httpHeaders.add("Pragma", "no-cache");
        httpHeaders.add("Expires", "0");
        //httpHeaders.add("content-disposition","inline;filename=sample.pdf");
        httpHeaders.add("content-disposition","attachment;filename=sample.pdf");
        httpHeaders.add("content-type","application/pdf;charset=utf-8");
        byte[] dataInByte=null;
        try {
            FileInputStream fis = new FileInputStream(new File(this.getClass().getResource( "/helloWorld.pdf" ).toURI()));
            dataInByte= IOUtils.toByteArray(fis);
        }
        catch(IOException ex){
            System.out.println(ex);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if(dataInByte!=null)
            return new ResponseEntity<>(dataInByte,httpHeaders,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
