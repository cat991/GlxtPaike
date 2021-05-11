package test;

import entity.Glxtlab;
import entity.Glxtpaike;
import impl.GlxtPaikeDaoImpl;

import java.util.List;

/**
 * @Auther£ºBlackCat
 * @Data£º 2021/4/25 - 17:49
 * @Descripton: test
 */
public class paikeText {
    public static void main(String[] args) {
        paikeText paikeText = new paikeText();
        paikeText.hei();
    }
    public void hei(){
        GlxtPaikeDaoImpl glxtPaikeDao = new GlxtPaikeDaoImpl();
        List<Glxtpaike>  alist= glxtPaikeDao.getPaikeList();
        for (Glxtpaike glxtlab : alist) {
            System.out.println(glxtlab.getPaikeCourseName());
        }
    }
}
