package com.supi.todolist.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.supi.todolist.entity.Task;

@Controller
public class TodoController {

	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * タスク一覧画面
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// SQL文を設定する
		String sql = "select * from task";

		// データを取得する
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		// MapのListデータをTaskのListデータに再構築する
		List<Task> tasks = new ArrayList<Task>();
		for (Map<String, Object> map : list) {
			Task t = new Task();
			t.setId((Integer) map.get("id"));
			t.setName((String) map.get("name"));
			t.setCreated_at((Timestamp) map.get("created_at"));
			t.setUpdated_at((Timestamp) map.get("updated_at"));
			tasks.add(t);
		}
		// ビューで使用するためにセットする
		model.addAttribute("tasks", tasks);
		return "index";
	}

	/**
	 * タスク新規追加画面
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tasks/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {
		return "create";
	}

	/**
	 * タスク新規登録処理
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tasks/store", method = RequestMethod.POST)
	public String store(Locale locale, Model model, @RequestParam("name") String name) {
		// SQL文を設定する
		String sql = "INSERT INTO task (name) VALUES (?)";

		// データをINSERTする
		jdbcTemplate.update(sql, name);

		return "redirect:/tasks";
	}

	/**
	 * タスク情報編集画面
	 * 
	 * @param locale
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tasks/{id}/edit", method = RequestMethod.GET)
	public String edit(Locale locale, Model model, @PathVariable Integer id) {
		// SQL文を設定する
		String sql = "select * from task where id = ?";

		// データを取得する
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);

		// MapのデータをTaskに再構築する
		Task task = new Task();
		task.setId((Integer) map.get("id"));
		task.setName((String) map.get("name"));
		task.setCreated_at((Timestamp) map.get("created_at"));
		task.setUpdated_at((Timestamp) map.get("updated_at"));

		// ビューで使用するためにセットする
		model.addAttribute("task", task);
		return "edit";
	}

	/**
	 * タスク情報更新処理
	 * 
	 * @param locale
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
	public String update(Locale locale, Model model, @PathVariable Integer id, @RequestParam("name") String name) {
		// SQL文を設定する
		String sql = "UPDATE task SET name = ? WHERE id = ?";

		// データをUPDATEする
		jdbcTemplate.update(sql, name, id);
		return "redirect:/tasks";
	}

	/**
	 * タスク削除処理
	 * 
	 * @param locale
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
	public String delete(Locale locale, Model model, @PathVariable Integer id) {
		// SQL文を設定する
		String sql = "DELETE FROM task WHERE id = ?";

		// データをDELETEする
		jdbcTemplate.update(sql, id);

		return "redirect:/tasks";
	}

}
