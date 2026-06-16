package edu.jxpu.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jxpu.dao.DepartDao;
import edu.jxpu.model.Depart;

@Service("departService")
public class DepartService {
	
	@Autowired
	private DepartDao departDao;
	
	/**
	 * 查询所有部门信息
	 */
	public List<Depart> findAllDepartments() {
		System.out.println("========== 查询所有部门信息 ==========");
		List<Depart> departList = departDao.findAll();
		if (departList == null || departList.isEmpty()) {
			System.out.println("当前没有部门数据。");
		} else {
			System.out.println("部门总数：" + departList.size());
			for (Depart depart : departList) {
				System.out.printf("ID: %d, 编号: %s, 名称: %s, 描述: %s%n",
						depart.getOid(), depart.getDno(),
						depart.getDname(), depart.getDinfo());
			}
		}
		System.out.println();
		return departList;
	}

	/**
	 * @param deptName  部门名称
	 * @param deptInfo  部门详情
	 * @return boolean 保存结果，true为成功，false为失败
	 */
	/**
	 * @param deptNo 自定义部门编号（可选，为空则自动生成）
	 * @param deptName  部门名称
	 * @param deptInfo  部门详情
	 * @return boolean 保存结果
	 */
	public boolean saveDepartment(String deptNo, String deptName, String deptInfo) {
	    System.out.println("========== 测试保存新部门 ==========");

	    // 如果用户传入了自定义编号，就用用户的；否则自动生成
	    String finalDeptNo;
	    if (deptNo != null && !deptNo.trim().isEmpty()) {
	        finalDeptNo = deptNo.trim();
	    } else {
	        finalDeptNo = "DEPT_" + LocalTime.now().toSecondOfDay();
	    }

	    Depart newDept = new Depart(finalDeptNo, deptName, deptInfo);

	    // 执行保存操作
	    boolean flag = departDao.save(newDept) > 0;
	    if (flag) {
	        System.out.println("部门保存成功！编号：" + finalDeptNo + "，名称：" + newDept.getDname());
	    } else {
	        System.out.println("部门保存失败！编号：" + finalDeptNo);
	    }

	    return flag;
	}
	
	/**
	 * @param id
	 * @param deptNo  部门编号
	 * @param deptName  部门名称
	 * @param deptInfo  部门详情
	 * @return boolean 保存结果，true为成功，false为失败
	 */
	public boolean updateDepartment(int id, String deptNo, String deptName, String deptInfo) {
		System.out.println("========== 测试更新部门 ==========");
		// 构造新部门对象
		Depart newDept = new Depart(id, deptNo, deptName, deptInfo);
		// 执行更新操作（MyBatis返回受影响行数，判断是否大于0）
		boolean flag = departDao.update(newDept) > 0;
		if (flag) {
			System.out.println("部门更新成功！编号：" + deptNo + "，名称：" + newDept.getDname());
		} else {
			System.out.println("部门更新失败！编号：" + deptNo);
		}
		return flag;
	}
	
	public boolean deleteDepartment(int id) {
		System.out.println("========== 测试删除部门 ==========");
		// 执行删除操作（MyBatis返回受影响行数，判断是否大于0）
		boolean flag = departDao.delete(id) > 0;
		if (flag) {
			System.out.println("部门删除成功！ID=" + id);
		} else {
			System.out.println("部门删除失败！ID=" + id);
		}
		return flag;
	}
}