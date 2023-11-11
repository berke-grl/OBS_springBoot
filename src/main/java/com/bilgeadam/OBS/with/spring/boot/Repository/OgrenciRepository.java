package com.bilgeadam.OBS.with.spring.boot.Repository;

import com.bilgeadam.OBS.with.spring.boot.Entity.Ogrenci;
import com.bilgeadam.OBS.with.spring.boot.Entity.Ogretmen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OgrenciRepository implements BaseRepository<Ogrenci> {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public OgrenciRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ogrenci> getAll() {
        String sql = "Select * From \"OGRENCI\"";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ogrenci.class));
    }

    @Override
    public Ogrenci findById(long id) {
        String sql = "Select * From \"OGRENCI\" Where \"ID\" = :searchedId";
        Map<String, Object> param = new HashMap<>();
        param.put("searchedId", id);

        return jdbcTemplate.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Ogrenci.class));
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "Delete From \"OGRENCI\" Where \"ID\" = :deletedId";
        Map<String, Long> param = new HashMap<>();
        param.put("deletedId", id);

        return jdbcTemplate.update(sql, param) == 1;
    }

    @Override
    public boolean save(Ogrenci ogrenci) {
        String sql = "Insert Into \"OGRENCI\"(\"NAME\", \"OGR_NUMBER\", \"YEAR\") Values(:name, :ogr_number, :year)";
        Map<String, Object> params = new HashMap<>();
        params.put("name", ogrenci.getName());
        params.put("ogr_number", ogrenci.getOgrNumber());
        params.put("year", ogrenci.getYear());

        return jdbcTemplate.update(sql, params) == 1;
    }
}
