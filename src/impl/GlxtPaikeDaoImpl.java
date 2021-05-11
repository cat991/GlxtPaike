package impl;

import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.GlxtPaikeDao;
import entity.Glxtclass;
import entity.Glxtlab;
import entity.Glxtpaike;

public class GlxtPaikeDaoImpl extends BaseDao implements GlxtPaikeDao {

	@Override
	public List<Glxtpaike> getPaikeList() {
		Glxtpaike glxtpaike = new Glxtpaike();
		List<Glxtpaike> alist = null;

		try {
			alist = simpleList(glxtpaike);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alist;
	}

	public Glxtpaike getListById(int id){
		Glxtpaike paiKe = new Glxtpaike();
		
		try {
			String sql = "select * from glxtPaike where paikeId = ?";
			Object[] params = {id};
			paiKe = selectOne(sql,params,Glxtpaike.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paiKe;
	}

	@Override
	public List<Glxtpaike> GlxtpaikeClass(String id) {
		List<Glxtpaike> alist = null;

		alist = queryList("select * from glxtpaike where paikeClassId = ?",new String[]{id},Glxtpaike.class);

		return alist;
	}

	@Override
	public List<Glxtpaike> GlxtpaikeLab(String id) {
		List<Glxtpaike> alist = null;

		alist = queryList("select * from glxtpaike where paikeLabId = ?",new String[]{id},Glxtpaike.class);

		return alist;
	}

	@Override
	public List<Glxtclass> GlxtpaikeClass() {
		Glxtclass glxtclass = new Glxtclass();
		List<Glxtclass> alist = null ;

		try {
			alist = simpleList(glxtclass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alist;
	}

	@Override
	public List<Glxtlab> GlxtpaikeLab() {
		Glxtlab glxtlab = new Glxtlab();
		List<Glxtlab> alist = null ;

		try {
			alist = simpleList(glxtlab);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alist;
	}




	@Override
	public int addGlxtPaike(Glxtpaike info) {
		int count = 0;
		try {
			count = add(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int delGlxtPaike(int id) {
		int count = 0;
		Glxtpaike info = new Glxtpaike();
		info.setPaikeId(id);
		try {
			count = del(info);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int upGlxtPaike(Glxtpaike info) {
		int count = 0;
		try {
			count = modify(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
