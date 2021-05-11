package impl;

import java.util.List;

import dao.BaseDao;
import dao.GlxtClassDao;
import entity.Glxtclass;
import entity.Glxtuser;

public class GlxtClassDaoImpl extends BaseDao implements GlxtClassDao {

	@Override
	public List<Glxtclass> getGlxtClassList() {
		List<Glxtclass> gList = null;
		Glxtclass gClass = new Glxtclass();
		try {
			gList = simpleList(gClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gList;
	}

	@Override
	public Glxtclass getGlxtClassOne(int id) {
		Glxtclass glxtclass = new Glxtclass();
		try {
			String sql = "SELECT * FROM glxtclass WHERE classId = ?";
			Object[] params = {id};
			glxtclass = selectOne(sql,params,Glxtclass.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return glxtclass;
	}


	@Override
	public int addGlxtClass(Glxtclass info) {
		int count = 0;
		try {
			count = add(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int delGlxtClass(int id) {
		int count = 0;
		Glxtclass info = new Glxtclass();
		info.setClassId(id);
		try {
			count = del(info);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}



	@Override
	public int upGlxtClass(Glxtclass info) {
		int count = 0;
		try {
			count = modify(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
