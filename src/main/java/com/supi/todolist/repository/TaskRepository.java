package com.supi.todolist.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.supi.todolist.entity.Task;

public class TaskRepository {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TaskRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Task> getTasks(Task task) {
		// SQL文の作成
		String sql = "SELECT";
		sql += " *";
		sql += " FROM";
		sql += " task";

		// SELECTを実行
		List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql);
		// データをEntityにセットして返却する
		List<Task> ret = new ArrayList<Task>();
		for (Map<String, Object> map : result) {
			Task t = new Task();
			t.setId((Integer) map.get("id"));
			t.setName((String) map.get("name"));
			t.setCreated_at((Timestamp) map.get("created_at"));
			t.setUpdated_at((Timestamp) map.get("updated_at"));
			ret.add(t);
		}
		return ret;
	}
}
