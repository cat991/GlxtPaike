package dao;

import java.util.List;

import entity.Glxtclass;

public interface GlxtClassDao {
	/**
	 * 获取所有班级信息列表
	 * @return 班级信息列表
	 */
	public List<Glxtclass> getGlxtClassList();

	/**
	 * 根据id获取某个班级信息
	 * @return
	 */
	public Glxtclass getGlxtClassOne(int id);

	/**
	 * 新增班级信息
	 * @param info 需要新增的班级信息
	 * @return 受影响的行数
	 */
	public int addGlxtClass(Glxtclass info);

	/**
	 * 删除班级信息
	 * @param id 需要删除的班级信息
	 * @return
	 */
	public int delGlxtClass(int id);

	/**
	 * 修改班级信息
	 * @param info 需要修改的信息
	 * @return 受影响的行数
	 */
	public int upGlxtClass(Glxtclass info);
}
