package com.bcg.bestchoisegamebackend.dbservice;

import com.bcg.bestchoisegamebackend.config.ConfigurationBean;
import com.bcg.bestchoisegamebackend.entity.PokemonModel;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class PokemonDBService {
    private static final Logger LOG = Logger.getLogger(PokemonDBService.class);
    private ResultSet rs;

    public Optional<PokemonModel> getById(int id) throws SQLException {

        String sql = "SELECT * FROM \"Best-choice\".\"Pokemons\" Where \"Id\" = ?";

        PreparedStatement pstmt = ConfigurationBean.conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        System.out.println("Data return successfully.");
        PokemonModel pokemonModel = new PokemonModel();
        ResultSet rs = pstmt.executeQuery();
        pokemonModel.setId(rs.getInt("Id"));
        pokemonModel.setName(rs.getString("Name"));
        pokemonModel.setRating(rs.getInt("Rating"));


    return Optional.of(pokemonModel);
    }

    public void initializeDBByJSON(JSONObject jsonObject) throws SQLException {

        String sql = "INSERT INTO \"Best-choice\".\"Pokemons\"(\"Id\", \"Name\", \"Rating\")VALUES (?,?, ?)";

        JSONArray pokemons = jsonObject.getJSONArray("Pokemons");

        LOG.info(pokemons.length());

        PreparedStatement pstmt = ConfigurationBean.conn.prepareStatement(sql);

        for (Object pokemon : pokemons) {
            LOG.info(pokemon);
            pstmt.setInt(1, ((JSONObject) pokemon).getInt("id"));
            pstmt.setString(2, ((JSONObject) pokemon).getString("name"));
            pstmt.setInt(3, 1000);
            pstmt.executeUpdate();
        }
        LOG.info("DB initialized successfully.");
    }

    public boolean dbTableRecordIsEmpty() throws SQLException {
        int count=0;
        String sql = "SELECT count(*) FROM \"Best-choice\".\"Pokemons\"";
        PreparedStatement pstmt = ConfigurationBean.conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while( rs.next() )
        {
            count=rs.getInt("count");
        }
        return count==0;
    }


}
