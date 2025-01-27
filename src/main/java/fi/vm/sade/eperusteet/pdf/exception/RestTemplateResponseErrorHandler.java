package fi.vm.sade.eperusteet.pdf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().is4xxClientError() || httpResponse.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().is5xxServerError()) {
            throw new ServiceException("Remote server error " + httpResponse.getStatusCode());
        } else if (httpResponse.getStatusCode().is4xxClientError()) {
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ServiceException("Ei löytynyt " + httpResponse.getStatusCode());
            } else if (httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new ServiceException("Virheellinen pyyntö " + httpResponse.getStatusCode());
            } else if (httpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new ServiceException("Pyyntö estetty " + httpResponse.getStatusCode());
            } else if (httpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new ServiceException("Ei oikeuksia " + httpResponse.getStatusCode());
            }
        }
    }
}
