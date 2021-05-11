package dao;

import java.util.List;

import entity.Glxtclass;
import entity.Glxtlab;
import entity.Glxtpaike;

public interface GlxtPaikeDao {

	/**
	 *
	 * @return 返回所有的课表
	 */
	public	List<Glxtpaike> getPaikeList();

	/**
	 * 根据id获取课程信息
	 * @param id
	 * @return 返回课程信息
	 */
	public Glxtpaike getListById(int id);

	/**
	 * 班级
	 * @return	返回班级列表
	 */
	public	List<Glxtclass> GlxtpaikeClass();


	/**
	 *
	 * @return 返回课室列表
	 */
	public	List<Glxtlab> GlxtpaikeLab();

	/**
	 *
	 * @param id
	 * @return	返回班级课表
	 */
	public	List<Glxtpaike> GlxtpaikeClass(String id);


	/**
	 *
	 * @return 返回课室课表
	 */
	public	List<Glxtpaike> GlxtpaikeLab(String id);



	/**
	 * 新增排课表信息
	 * @param info 需要新增的排课表信息
	 * @return 受影响的行数
	 */
	public int addGlxtPaike(Glxtpaike info);
	
	/**
	 * 删除排课表信息
	 * @param trainNo 需要删除的排课表信息
	 * @return
	 */
	public int delGlxtPaike(int id);
	
	/**
	 * 修改排课表信息
	 * @param info需要修改的排课表信息
	 * @return 受影响的行数
	 */
	public int upGlxtPaike(Glxtpaike info);
}
