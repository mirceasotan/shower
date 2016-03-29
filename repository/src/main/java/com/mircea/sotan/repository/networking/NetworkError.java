package com.mircea.sotan.repository.networking;

public class NetworkError extends Throwable {

    private int httpCode = -1;
    private String body;
    private String code;
    private String message;

    public NetworkError() {
        super();
    }

    public NetworkError(Throwable e) {
        super(e);
    }

    public NetworkError(int httpCode) {
        this.httpCode = httpCode;
    }

    public NetworkError(String message) {
        super(message);
        this.message = message;
    }

    public NetworkError(int httpCode, String code) {
        this.httpCode = httpCode;
        this.code = code;
    }

    public NetworkError(int httpCode, String code, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

//    public static NetworkError createFrom(Response response) {
//        NetworkError networkError = new NetworkError();
//        networkError.httpCode = response.code();
//        Gson gson = new Gson();
//
//        if (response.body() == null) {
//            return networkError;
//        }
//
//        try {
//            networkError.body = response.body().string();
//            JsonElement jsonBody = new JsonParser().parse(networkError.body);
//
//            if (response.header(ApiRequest.Header.CONTENT_TYPE, "").contains(WSConstants.VALIDATION_ERROR_JSON)) {
//                networkError = createFromValidationError(networkError, jsonBody, gson);
//            } else {
//                networkError = createFromApiMessage(networkError, jsonBody, gson);
//            }
//
//        } catch (IOException | JsonSyntaxException e) {
//            e.printStackTrace();
//        }
//        return networkError;
//    }
//
//    private static NetworkError createFromValidationError(NetworkError networkError, JsonElement jsonBody, Gson gson) {
//        try {
//            ValidationError error;
//            String description = "";
//            if (jsonBody.isJsonArray()) {
//                for (int i = 0; i < jsonBody.getAsJsonArray().size(); i++) {
//                    error = gson.fromJson(jsonBody.getAsJsonArray().get(i), ValidationError.class);
//                    description += error.getDescription() + "\n";
//                }
//            } else {
//                error = gson.fromJson(jsonBody, ValidationError.class);
//                description = error.getDescription();
//            }
//
//            networkError.message = description;
//
//            return networkError;
//        } catch (Exception e) {
//            return networkError;
//        }
//    }
//
//    private static NetworkError createFromApiMessage(NetworkError networkError, JsonElement jsonBody, Gson gson) {
//        try {
//            ApiMessage error;
//            if (jsonBody.isJsonArray()) {
//                error = gson.fromJson(jsonBody.getAsJsonArray().get(0), ApiMessage.class);
//            } else {
//                error = gson.fromJson(jsonBody, ApiMessage.class);
//            }
//
//            networkError.code = error.code;
//            networkError.message = error.text;
//
//
//            return networkError;
//        } catch (Exception e) {
//            return networkError;
//        }
//    }
//
//    /**
//     * @return - the server code for this error message, or -1 if not available.
//     */
//    @Nullable
//    public String getCode() {
//        return code;
//    }
//
//    /**
//     * @return - the response HTTP status code, or -1 if not available.
//     */
//    public int getHttpCode() {
//        return httpCode;
//    }
//
//    /**
//     * @return - the response body, or <code>null</code> if not available.
//     */
//    @Nullable
//    public String getBody() {
//        return body;
//    }
//
//    /**
//     * @return - the message sent by the server describing the error, or <code>null</code> if
//     * not available.
//     */
//    @Nullable
//    public String getMessage() {
//        return message;
//    }
//
//    public boolean wasUnableToReachHost() {
//        return getCause() != null && getCause() instanceof UnknownHostException;
//    }
//
//    public String getStatus() {
//        StringBuilder builder = new StringBuilder(String.valueOf(httpCode));
//
//        if (!DataUtils.isNullOrEmpty(code)) {
//            builder.append("-");
//            builder.append(code);
//        }
//
//        return builder.toString();
//    }
}
