package com.github.diegonighty.http;

import com.github.diegonighty.http.request.types.HttpDeleteRequest;
import com.github.diegonighty.http.request.types.HttpGetRequest;
import com.github.diegonighty.http.request.types.HttpInputRequest;
import com.github.diegonighty.http.util.HeaderMap;
import java.util.Map;

public interface HttpConnection<T> {

  /**
   * Add header to http request
   * @param field Field that will be added in the headers of the request
   * @param value Value of the field, this will be serialized to string
   * @return The same connection with the changes
   * @see <a href="https://en.wikipedia.org/wiki/List_of_HTTP_header_fields"> List of header fields and usage </a>
   */
  default <V> HttpConnection<T> addRequestField(RequestField field, V value) {
    return addRequestField(field.parse(), value);
  }

  /**
   * Add header to http request
   * @param field Field that will be added in the headers of the request
   * @param value Value of the field, this will be serialized to string
   * @return The same connection with the changes
   * @see <a href="https://en.wikipedia.org/wiki/List_of_HTTP_header_fields"> List of header fields and usage </a>
   */
  <V> HttpConnection<T> addRequestField(String field, V value);

  /**
   * Add headers to http request
   * @param map Map containing all fields and values, the values will be serialized to string
   * @return The same connection with the changes
   * @see <a href="https://en.wikipedia.org/wiki/List_of_HTTP_header_fields"> List of header fields and usage </a>
   */
  default HttpConnection<T> addRequestFields(HeaderMap map) {
    return addRequestFields(map.getHeaderMap());
  }

  /**
   * Add headers to http request
   * @param map Map containing all fields and values, the values will be serialized to string
   * @return The same connection with the changes
   * @see <a href="https://en.wikipedia.org/wiki/List_of_HTTP_header_fields"> List of header fields and usage </a>
   */
  HttpConnection<T> addRequestFields(Map<String, Object> map);

  /**
   * Performs a get request
   * @return get request
   */
  HttpGetRequest<T> createGetRequest();

  /**
   * Performs a post request
   * @return post request
   */
  HttpInputRequest<T> createPostRequest();

  /**
   * Performs a patch request
   * @return patch request (it really is a POST request, but spoofed with a header inside the connection)
   */
  HttpInputRequest<T> createPatchRequest();

  /**
   * Performs a put request
   *
   * @return put request
   */
  HttpInputRequest<T> createPutRequest();

  /**
   * Performs a delete request
   *
   * @return delete request
   */
  HttpDeleteRequest createDeleteRequest();

  /**
   * HTTP Methods for the request
   * @see <a href="https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods"> List of method request and usage </a>
   */
  enum HttpMethod {

    GET,
    POST,
    PUT,
    DELETE

  }
  /**
   * HTTP headers for the request
   * @see <a href="https://en.wikipedia.org/wiki/List_of_HTTP_header_fields"> List of header fields and usage </a>
   */
  enum RequestField {

    A_IM,
    ACCEPT,
    ACCEPT_CHARSET,
    ACCEPT_DATATIME,
    ACCEPT_ENCODING,
    ACCEPT_LANGUAGE,
    ACCESS_CONTROL_REQUEST_METHOD,
    ACCESS_CONTROL_REQUEST_HEADERS,
    AUTHORIZATION,
    CACHE_CONTROL,
    CONNECTION,
    CONTENT_ENCODING,
    CONTENT_LENGTH,
    CONTENT_MD5,
    CONTENT_TYPE,
    COOKIE,
    DATE,
    EXPECT,
    FORWARDED,
    FROM,
    HOST,
    IF_MATCH,
    IF_MODIFIED_SINCE,
    IF_NONE_MATCH,
    IF_RANGE,
    IF_UNMODIFIED_SINCE,
    MAX_FORWARDS,
    ORIGIN,
    PRAGMA,
    PREFER,
    PROXY_AUTHORIZATION,
    RANGE,
    REFERER,
    TE,
    TRAILER,
    TRANSFER_ENCODING,
    USER_AGENT,
    UPGRADE,
    VIA,
    WARNING;

    /**
     * parse http header to string
     * @return Header parsed
     */
    public String parse() {
      return toString().replace("_", "-");
    }
  }
}
