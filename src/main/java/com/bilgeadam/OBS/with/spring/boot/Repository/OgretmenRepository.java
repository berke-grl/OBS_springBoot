package com.bilgeadam.OBS.with.spring.boot.Repository;

import com.bilgeadam.OBS.with.spring.boot.Entity.Ogretmen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OgretmenRepository implements BaseRepository<Ogretmen> {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public OgretmenRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ogretmen> getAll() {
        String sql = "Select * From \"OGRETMEN\"";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ogretmen.class));
    }

    @Override
    public Ogretmen findById(long id) {
        String sql = "Select * From \"OGRETMEN\" Where \"ID\" = :searchedId";
        Map<String, Object> param = new HashMap<>();
        param.put("searchedId", id);

        return jdbcTemplate.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Ogretmen.class));
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "Delete From \"OGRETMEN\" Where \"ID\" = :deletedId";
        Map<String, Long> param = new HashMap<>();
        param.put("deletedId", id);

        return jdbcTemplate.update(sql, param) == 1;
    }

    @Override
    public boolean save(Ogretmen ogretmen) {
        String sql = "Insert Into \"OGRETMEN\"(\"NAME\", \"IS_GICIK\") Values(:name, :is_gıcık)";
        Map<String, Object> params = new HashMap<>();
        params.put("name", ogretmen.getName());
        params.put("is_gıcık", ogretmen.isGıcık());

        return jdbcTemplate.update(sql, params) == 1;
    }
}
