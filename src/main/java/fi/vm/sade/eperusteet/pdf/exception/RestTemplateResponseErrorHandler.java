package fi.vm.sade.eperusteet.pdf.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().series() == SERVER_ERROR) {
            log.error("Remote server error ({}).", httpResponse.getRawStatusCode());
            throw new ServiceException("Remote server error.");
        } else if (httpResponse.getStatusCode().series() == CLIENT_ERROR) {
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info("Ei löytynyt ({}).", httpResponse.getRawStatusCode());
            } else if (httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
                log.info("Virheellinen pyyntö ({}).", httpResponse.getRawStatusCode());
            } else if (httpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                log.info("Pyyntö estetty ({}).", httpResponse.getRawStatusCode());
            } else if (httpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                log.info("Ei oikeuksia ({}).", httpResponse.getRawStatusCode());
            }
        }
    }
}
