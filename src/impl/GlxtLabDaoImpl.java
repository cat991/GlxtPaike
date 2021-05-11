package impl;

import java.util.List;

import dao.BaseDao;
import dao.GlxtLabDao;
import entity.Glxtclass;
import entity.Glxtlab;

public class GlxtLabDaoImpl extends BaseDao implements GlxtLabDao {

	@Override
	public List<Glxtlab> getGlxtLabList() {
		List<Glxtlab> gList = null;
		Glxtlab gLab = new Glxtlab();
		try {
			gList = simpleList(gLab);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gList;
	}

	@Override
	public Glxtlab getGlxtLabById(int id) {
		Glxtlab glxtlab = new Glxtlab();
		try {
			String sql = "SELECT * FROM glxtlab WHERE labId = ?";
			Object[] params = {id};
			glxtlab  = selectOne(sql,params,Glxtlab.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return glxtlab;
	}

	@Override
	public int addGlxtLab(Glxtlab info) {
		int count = 0;
		try {
			count = add(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int delGlxtLab(int id) {
		int count = 0;
		Glxtlab info = new Glxtlab();
		info.setLabId(id);
		try {
			count = del(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int upGlxtLab(Glxtlab info) {
		int count = 0;
		try {
			count = modify(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
