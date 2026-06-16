package edu.jxpu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jxpu.aspect.RequireRole;
import edu.jxpu.model.Depart;
import edu.jxpu.service.DepartService;

@RestController
@RequestMapping("/api/depts")
public class DepartController {
	@Autowired
	private DepartService departService;
	
	/**
	 * 查询所有部门
	 */
	@GetMapping
	@RequireRole("admin,user")
	public Map<String, Object> findAllDepts() {
		Map<String, Object> result = new HashMap<>();
		List<Depart> departs = departService.findAllDepartments();
		result.put("code", 200);
		result.put("data", departs);
		return result;
	}
	
	/**
	 * 添加部门
	 */
	/**
	 * 添加部门
	 */
	@PostMapping
	@RequireRole("admin,user")
	public Map<String, Object> addDept(@RequestBody Map<String, String> params) {
	    Map<String, Object> result = new HashMap<>();
	    String deptNo = params.get("dno"); // 接收自定义部门编号
	    String deptName = params.get("dname");
	    String deptInfo = params.get("dinfo");
	    
	    if(deptName == null || deptName.trim().isEmpty()) {
	        result.put("code", 400);
	        result.put("message", "部门名称不能为空");
	        return result;
	    }
	    
	    boolean success = departService.saveDepartment(deptNo, deptName, deptInfo);
	    if(success) {
	        result.put("code", 200);
	        result.put("message", "部门添加成功");
	    } else {
	        result.put("code", 500);
	        result.put("message", "部门添加失败");
	    }
	    return result;
	}
	
	/**
	 * 修改部门
	 */
	@PutMapping("/{id}")
	@RequireRole("admin")
	public Map<String, Object> updateDept(@PathVariable Integer id, @RequestBody Map<String, String> params) {
		Map<String, Object> result = new HashMap<>();
		String deptNo = params.get("dno");
		String deptName = params.get("dname");
		String deptInfo = params.get("dinfo");
		
		if(deptName == null || deptName.trim().isEmpty()) {
			result.put("code", 400);
			result.put("message", "部门名称不能为空");
			return result;
		}
		
		boolean success = departService.updateDepartment(id, deptNo, deptName, deptInfo);
		if(success) {
			result.put("code", 200);
			result.put("message", "部门更新成功");
		} else {
			result.put("code", 500);
			result.put("message", "部门更新失败");
		}
		return result;
	}
	
	/**
	 * 删除部门
	 */
	@DeleteMapping("/{id}")
	@RequireRole("admin")
	public Map<String, Object> deleteDept(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<>();
		boolean success = departService.deleteDepartment(id);
		if(success) {
			result.put("code", 200);
			result.put("message", "部门删除成功");
		} else {
			result.put("code", 500);
			result.put("message", "部门删除失败");
		}
		return result;
	}
}