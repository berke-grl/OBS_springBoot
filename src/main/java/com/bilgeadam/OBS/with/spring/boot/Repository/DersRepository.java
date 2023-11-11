package com.bilgeadam.OBS.with.spring.boot.Repository;

import com.bilgeadam.OBS.with.spring.boot.Entity.Ders;
import com.bilgeadam.OBS.with.spring.boot.Entity.Ogretmen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DersRepository implements BaseRepository<Ders> {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DersRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ders> getAll() {
        String sql = "Select * From \"DERS\"";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ders.class));
    }

    @Override
    public Ders findById(long id) {
        String sql = "Select * From \"DERS\" Where \"ID\" = :searchedId";
        Map<String, Object> param = new HashMap<>();
        param.put("searchedId", id);

        return jdbcTemplate.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Ders.class));
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "Delete From \"DERS\" Where \"ID\" = :deletedId";
        Map<String, Long> param = new HashMap<>();
        param.put("deletedId", id);

        return jdbcTemplate.update(sql, param) == 1;
    }

    @Override
    public boolean save(Ders ders) {
        String sql = "Insert Into \"DERS\"(\"OGRETMEN_ID\", \"KONU_ID\") Values(:ogretmen_id, :konu_id)";
        Map<String, Object> params = new HashMap<>();
        params.put("ogretmen_id", ders.getOgretmen_id());
        params.put("konu_id", ders.getKonu_id());

        return jdbcTemplate.update(sql, params) == 1;
    }
}