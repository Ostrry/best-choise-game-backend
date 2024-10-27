package com.bcg.bestchoisegamebackend.config;

import com.bcg.bestchoisegamebackend.dbservice.PokemonDBService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConfigurationBean {

    private static final Logger LOG = Logger.getLogger(ConfigurationBean.class);
    public static Connection conn;


    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        BasicConfigurator.configure();
        LOG.info("Yaaah, I am running........");
        try {
            getConnection();
            PokemonDBService pokemonDBService = new PokemonDBService();
            if (pokemonDBService.dbTableRecordIsEmpty()){
                LOG.info("There are no records in the table");
                pokemonDBService.initializeDBByJSON(parseJSONFile("C:\\Users\\Stanislavic\\IdeaProjects\\best-choise-game-backend\\src\\main\\resources\\static\\Pokemons.json"));
                LOG.info("Initialization completed");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getConnection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Reactapps", "postgres", "postgres");
        LOG.info("Open DB connection........" + conn.getClientInfo());
    }

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

}