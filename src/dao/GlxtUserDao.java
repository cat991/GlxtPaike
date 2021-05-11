package dao;

import java.util.List;

import entity.Glxtuser;

public interface GlxtUserDao {

    public Glxtuser getByUserId(int id);

    public int upUser(Glxtuser user);

    public int manageDe(int userI, int id);

    public List<Glxtuser> manageSelect();

    /**
     * ע��
     * @param user
     * @return
     */
    public int registUser(Glxtuser user);

    /**
     * �ж��û��Ƿ���ע��
     * @param userName
     * @return true��ʾ��ע��
     */
    public int getByUserName(String userName);

    /**
     * ��¼
     * @param userName
     * @param userPwd
     * @return
     */
    public Glxtuser userlogin(String userName, String userPwd);
}
