import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class Cliente {

    String token = "e98bfd1c39e27ec3f1577b52cae418d088b065601cd89a060b2c1f72440ce77d";
    String baseURL= "https://gorest.co.in";
    String endpoint = "/public/v2/users";
    String cliente1_id = "/117911";

    String cliente2_id = "/117909";
    String cliente3_id = "/85958";

    //Atualizar resposta esperada
    @Test
    @DisplayName("Dado que tenho clientes cadastrados, quando usar o método GET no endpoint de consulta à todos os clientes, então… ")
    public void get_all_customers() {

        String respostaEsperada = "[{\"id\":117911,\"name\":\"Vasundhara Joshi PhD\",\"email\":\"joshi_vasundhara_phd@koch.name\",\"gender\":\"male\",\"status\":\"active\"},{\"id\":117910,\"name\":\"Ms. Dhananjay Pilla\",\"email\":\"ms_pilla_dhananjay@king.co\",\"gender\":\"female\",\"status\":\"inactive\"},{\"id\":117909,\"name\":\"Gotum Pillai\",\"email\":\"pillai_gotum@hartmann.org\",\"gender\":\"male\",\"status\":\"inactive\"},{\"id\":117908,\"name\":\"Amritambu Patel\",\"email\":\"patel_amritambu@beahan.net\",\"gender\":\"male\",\"status\":\"active\"},{\"id\":117907,\"name\":\"Kashyapi Talwar\",\"email\":\"kashyapi_talwar@kemmer.biz\",\"gender\":\"male\",\"status\":\"inactive\"},{\"id\":117905,\"name\":\"Mr. Aaditya Abbott\",\"email\":\"mr_abbott_aaditya@leffler.info\",\"gender\":\"female\",\"status\":\"active\"},{\"id\":117904,\"name\":\"Anjushri Mehra\",\"email\":\"anjushri_mehra@upton-hickle.biz\",\"gender\":\"female\",\"status\":\"inactive\"},{\"id\":117903,\"name\":\"Dhanpati Khatri\",\"email\":\"dhanpati_khatri@lind-buckridge.net\",\"gender\":\"female\",\"status\":\"inactive\"},{\"id\":85960,\"name\":\"Dharmaketu Adiga\",\"email\":\"dharmaketu_adiga@cremin.net\",\"gender\":\"male\",\"status\":\"active\"},{\"id\":85959,\"name\":\"Chetan Iyer\",\"email\":\"chetan_iyer@schmidt-dooley.info\",\"gender\":\"male\",\"status\":\"active\"}]";
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURL+endpoint)

                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Dado que tenho clientes cadastrados, quando usar o método GET no endpoint de consulta para um cliente específico, então…")
    public void get_a_specific_customer() {

        String respostaEsperada = "{\"id\":117911,\"name\":\"Vasundhara Joshi PhD\",\"email\":\"joshi_vasundhara_phd@koch.name\",\"gender\":\"male\",\"status\":\"active\"}";
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURL+endpoint+ cliente1_id)

                .then()
                .statusCode(200);

    }
    //Mudar algo no e-mail
    @Test
    @DisplayName("Dado que tenho um cliente para cadastrar, quando usar o método POST  no endpoint de cadastrar um cliente, então… ")
    public void post_a_customer() {

        // Este método só podera ser validado o StatusCode = 201
        String clienteParaCadastrar = " {\n" +
                "        \"name\": \"Henrique Calazans\",\n" +
                "        \"email\": \"henriquecalazans@test2sadi12.net\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"status\": \"active\"\n" +
                "    }";



        given()
                .headers(
                        "Authorization",
                        "Bearer " + token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(clienteParaCadastrar)
                .when()
                .post(baseURL+endpoint)


                .then()
                .statusCode(201);

    }
    // Atualizar alguma informação do body
    @Test
    @DisplayName("Dado que tenho cliente1 cadastrado no sistema,  quando usar o método PUT no endpoint de atualizar um cliente e informando seu ID, então…  ")
    public void put_a_customer() {

        String clienteParaAtualizado = "{\n" +
                "        \"id\": 117909,\n" +
                "        \"name\": \"Gotum Pillai\",\n" +
                "        \"email\": \"pillai_gotum@hartmann.org\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"status\": \"inactive\"\n" +
                "    }";
        String respostaEsperada = "";

        given()
                .headers(
                        "Authorization",
                        "Bearer " + token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(clienteParaAtualizado)
                .when()
                .put(baseURL+endpoint+cliente2_id)

                .then()
                .statusCode(200);


    }
    // Escolher um cliente novo
    @Test
    @DisplayName("Dado que tenho um cliente para cadastrar, quando usar o método POST  no endpoint de cadastrar um cliente, então… ")
    public void delete_a_customer() {

        given()
                .headers(
                        "Authorization",
                        "Bearer " + token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)

                .when()
                .delete(baseURL+endpoint+cliente3_id)

                .then()
                .statusCode(204);
    }



}
