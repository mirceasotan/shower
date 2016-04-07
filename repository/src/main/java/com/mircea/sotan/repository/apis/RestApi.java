package com.mircea.sotan.repository.apis;

/**
 * @author mircea on 4/6/16
 */
public interface RestApi {
    int DEFAULT_HTTP_CONNECTION_ERROR_CODE = -1;
    String AUTHORIZATION_HEADER_TEMPLATE = "Bearer {token}";
    String NO_INTERNET_CONNECTION_MESSAGE = "No Internet Connection";
    String UNKNOWN_STATUS = "Unknown Status";
}
