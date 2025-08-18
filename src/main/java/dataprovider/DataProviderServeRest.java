package dataprovider;

import clients.UsuarioClient;
import dtos.UsuarioDTO;
import factories.UsuarioFactory;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;

public class DataProviderServeRest {

    @DataProvider(name = "idsInvalidos")
    public static Object[][] idsInvalidos() {
        return new Object[][] {
                { "0uxuPY0cbmQhpXXX" },
                { "0uxuPY0cbmQhpXX" }
        };
    }

}
