package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.dto.YandexLink;
import by.grigoryev.anastasia.service.YandexDiskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class YandexDiskServiceImpl implements YandexDiskService {

    @Value("${yandex.upload.link.url}")
    private String uploadLink;

    @Value("${yandex.oauth2.token}")
    private String token;

    @Override
    public void uploadByTheReceivedLink() {
        YandexLink link = getALinkToUploadAFile();
        WebClient webClient = WebClient.create();

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "NewYearTestResults.xlsx";

        if (link != null) {
            try (FileInputStream stream = new FileInputStream(fileLocation)) {
                webClient.put()
                        .uri(link.getHref())
                        .accept(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(stream.readAllBytes()))
                        .retrieve()
                        .bodyToMono(Void.class)
                        .log("uploadByTheReceivedLink")
                        .block();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        } else {
            log.warn("Link is empty");
        }
    }

    private YandexLink getALinkToUploadAFile() {
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri(uploadLink)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(YandexLink.class)
                .log("getALinkToUploadAFile")
                .block();
    }

}
