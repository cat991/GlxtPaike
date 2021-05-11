package dao;

import entity.Glxtlab;

import java.util.List;



public interface GlxtLabDao {

	/**
	 * 获取所有机房表信息列表
	 * @return 机房表信息列表
	 */
	public List<Glxtlab> getGlxtLabList();

	/**
	 * 根据id获取机房信息
	 * @param id
	 * @return
	 */
	public Glxtlab getGlxtLabById(int id);

	/**
	 * 新增机房表信息
	 * @param info 需要新增的机房表信息
	 * @return 受影响的行数
	 */
	public int addGlxtLab(Glxtlab info);
	
	/**
	 * 删除机房表信息
	 * @param trainNo 需要删除的机房表信息
	 * @return
	 */
	public int delGlxtLab(int id);
	
	/**
	 * 修改机房表信息
	 * @param info需要修改的机房表信息
	 * @return 受影响的行数
	 */
	public int upGlxtLab(Glxtlab info);
}
