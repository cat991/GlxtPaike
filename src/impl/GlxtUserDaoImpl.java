package impl;


import java.util.List;


import dao.BaseDao;
import dao.GlxtUserDao;
import entity.Glxtuser;

public class GlxtUserDaoImpl extends BaseDao implements GlxtUserDao {

    @Override
    public Glxtuser getByUserId(int id) {
        Glxtuser user = new Glxtuser();
        try {
            String sql = "select * from glxtUser where userId = ?";
            Object[] params = {id};
            user = selectOne(sql,params,Glxtuser.class);//有数据说明已注册
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int upUser(Glxtuser user) {
        int i = 0;
        try {
            i = modify(user);
        }catch (Exception e) {
            e.printStackTrace();
            return i;
        }
        return i;
    }

    @Override
    public int manageDe(int userI,int id) {
        int x = 0;
        try {
            String sql = "SELECT * FROM glxtuser WHERE userId = ?";
            Glxtuser userOne = new Glxtuser();
            Object[] params = {userI};
            userOne = selectOne(sql,params,Glxtuser.class);
            if (id==1){//升为管理员，或降为普通用户
                if (userOne.getUserType().equals("0")){
                    userOne.setUserType("1");
                }else {
                    userOne.setUserType("0");
                }

            }else if (id==2){//封号或解封
                if(userOne.getUserStatus().equals("1")){
                    userOne.setUserStatus("0");
                }else {
                    userOne.setUserStatus("1");
                }

            }
            x = modify(userOne);
        }catch (Exception e) {
            e.printStackTrace();
            return x;
        }
        return x;
    }

    @Override
    public List<Glxtuser> manageSelect() {
        Glxtuser user = new Glxtuser();
        try {
            List<Glxtuser> userList = simpleList(user);
            return userList;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int registUser(Glxtuser user){
        int i = 0;
        try {
            i = add(user);
        }catch (Exception e) {
            e.printStackTrace();
            return i;
        }
        return i;
    }

    @Override
    public int getByUserName(String userName) {
        int i = 0;
        Glxtuser user = new Glxtuser();
        // 判断用户是否已注册 返回0说明已注册
        try {
            String sql = "select * from glxtUser where userName = ?";
            Object[] params = {userName};
            user = selectOne(sql,params,Glxtuser.class);//有数据说明已注册
            if(user == null || user.equals("")){
                i = 1;
            }
            else i=0;

            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    @Override
    public Glxtuser userlogin(String userName, String userPwd) {
        Glxtuser user = new Glxtuser();
        try {
            String sql = "SELECT * FROM glxtUser WHERE userName = ? AND userPwd = ?";
            Object[] params = {userName,userPwd};
            user = selectOne(sql,params,Glxtuser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}
