package com.epam.automation.utils;

public class Constants {

    // URLs
    public static final String BASE_URL = "https://www.amazon.com/";
    public static final String LOGIN_URL = BASE_URL + "ap/signin";
    public static final String CART_URL = BASE_URL + "gp/cart/view.html";

    // Timeouts (In seconds)
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 15;
    public static final int PAGE_LOAD_TIMEOUT = 30;

    // Rutas de archivos
    public static final String SCREENSHOTS_PATH = "screenshots/";
    public static final String LOGS_PATH = "logs/";
    public static final String TEST_DATA_PATH = "src/test/resources/testdata/";

    // Mensajes de error comunes
    public static final String ERROR_PAGE_NOT_LOADED = "La página no se cargó correctamente";
    public static final String ERROR_ELEMENT_NOT_FOUND = "Elemento no encontrado en la página";
    public static final String ERROR_LOGIN_FAILED = "Login falló";

    // Productos de prueba comunes
    public static final String[] TEST_PRODUCTS = {
            "laptop",
            "wireless mouse",
            "usb cable",
            "headphones",
            "keyboard"
    };

    // Estados de USA para testing
    public static final String[] US_STATES = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California",
            "Colorado", "Connecticut", "Delaware", "Florida", "Georgia"
    };

    // Configuración de navegadores
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String EDGE = "edge";

    // Códigos de respuesta HTTP
    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_SERVER_ERROR = 500;
}
