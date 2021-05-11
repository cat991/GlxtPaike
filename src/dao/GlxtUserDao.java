package dao;

import java.util.List;

import entity.Glxtuser;

public interface GlxtUserDao {

    public Glxtuser getByUserId(int id);

    public int upUser(Glxtuser user);

    public int manageDe(int userI, int id);

    public List<Glxtuser> manageSelect();

    /**
     * 注册
     * @param user
     * @return
     */
    public int registUser(Glxtuser user);

    /**
     * 判断用户是否已注册
     * @param userName
     * @return true表示已注册
     */
    public int getByUserName(String userName);

    /**
     * 登录
     * @param userName
     * @param userPwd
     * @return
     */
    public Glxtuser userlogin(String userName, String userPwd);
}
