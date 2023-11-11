package com.bilgeadam.OBS.with.spring.boot.Repository;

import com.bilgeadam.OBS.with.spring.boot.Entity.Konu;
import com.bilgeadam.OBS.with.spring.boot.Entity.Ogretmen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class KonuRepository implements BaseRepository<Konu> {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public KonuRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Konu> getAll() {
        String sql = "Select * From \"KONU\"";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Konu.class));
    }

    @Override
    public Konu findById(long id) {
        String sql = "Select * From \"KONU\" Where \"ID\" = :searchedId";
        Map<String, Object> param = new HashMap<>();
        param.put("searchedId", id);

        return jdbcTemplate.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Konu.class));
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "Delete From \"KONU\" Where \"ID\" = :deletedId";
        Map<String, Long> param = new HashMap<>();
        param.put("deletedId", id);

        return jdbcTemplate.update(sql, param) == 1;
    }

    @Override
    public boolean save(Konu konu) {
        String sql = "Insert Into \"KONU\"(\"NAME\") Values(:name)";
        Map<String, Object> params = new HashMap<>();
        params.put("name", konu.getName());

        return jdbcTemplate.update(sql, params) == 1;
    }
}
