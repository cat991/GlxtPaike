package test;


import dao.BaseDao;
import entity.Glxtpaike;

/**
 * @Auther£ºBlackCat
 * @Data£º 2021/4/25 - 0:04
 * @Descripton: test
 */
public class user extends BaseDao {

    public static void main(String[] args) {

        new user().usernnn();
    }
     void usernnn(){
        Glxtpaike alist = null;
        alist = selectOne("select * from glxtPaike where paikeId = ?",new String[] {"1"},Glxtpaike.class);
        System.out.println(alist);
    }
}
