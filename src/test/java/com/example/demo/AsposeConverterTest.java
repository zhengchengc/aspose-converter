package com.example.demo;

import com.aspose.words.cloud.ApiException;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AsposeConverterTest {

    private AsposeConverter converter;

    @Test
    void convert() throws MessagingException, IOException, ApiException {
        converter = new AsposeConverter();
        converter.convert("Dummy.docx");
    }
}