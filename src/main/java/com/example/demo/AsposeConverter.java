package com.example.demo;

import com.aspose.words.cloud.ApiClient;
import com.aspose.words.cloud.ApiException;
import com.aspose.words.cloud.api.WordsApi;
import com.aspose.words.cloud.model.FilesUploadResult;
import com.aspose.words.cloud.model.requests.UploadFileRequest;
import org.apache.commons.io.file.PathUtils;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AsposeConverter {

    public ApiClient apiClient;
    public WordsApi wordsApi;

    public void convert(String filename) throws IOException, MessagingException, ApiException {
        apiClient = new ApiClient("jade", "jade", "http://localhost:5199");
        wordsApi = new WordsApi(apiClient);
        String localPath = Paths.get("src/main/resources/files", filename).toString();
        String remotePath = Paths.get("/Data", "somename.docx").toString();
        UploadFileRequest uploadRequest = new UploadFileRequest(Files.readAllBytes(Paths.get(localPath)), remotePath, null);
        wordsApi.uploadFile(uploadRequest);
    }
}
