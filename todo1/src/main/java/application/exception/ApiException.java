package application.exception;

import javax.servlet.http.HttpServletResponse;

public class ApiException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;
    private String description;
    private int statusCode;

    /**
     * Creates a new ApiException
     *
     * @param code        The error code to report
     * @param description The description to report
     */
    public ApiException(final String code, final String description) {
        this.code = code;
        this.description = description;
        statusCode = HttpServletResponse.SC_BAD_REQUEST;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "ApiException{"
                + "code='" + code + '\''
                + ", description='" + description + '\''
                + ", statusCode=" + statusCode
                + '}';
    }
}