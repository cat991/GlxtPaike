package dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 数据库连接与关闭类
 * @author Cat
 */
public class BaseDao {
    private String driver = "com.mysql.jdbc.Driver";//加载驱动包
    private String url = "jdbc:mysql://localhost:3306/guanli?useUnicode=true&characterEncoding=utf8";//数据库连接字符串
    private String user = "sa";//用户名
    private String pwd = "123456";//密码

    /**
     * 获取连接对象
     * @return 连接对象
     */
    public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 撤销相关资源
     * @param conn 连接对象
     * @param stmt stmt对象
     * @param rs 结果集对象
     */
    public void closeAll(Connection conn,Statement stmt,ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行增删改的SQL语句
     * @param sql 需要执行的SQL语句
     * @param objs 参数列表
     * @return 受影响的行数
     */
    public int runNonQuerySQL(String sql,Object[] objs){
        int count = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);//预编译SQL语句
            //循环赋参数
            if(objs!=null){
                for (int i = 0; i < objs.length; i++) {
                    pstmt.setObject((i+1), objs[i]);
                }
            }
            //执行SQL语句
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            closeAll(conn, pstmt, null);
        }
        return count;
    }

    /**
     * 事务处理批处理语句
     * @param sqls 需要执行的SQL语句批处理
     * @return 各条语句执行后的受影响行数
     */
    public int[] transactionExcute(String... sqls){
        Connection conn = null;
        Statement st = null;
        int[] counts = null;
        try{
            conn = getConnection();
            conn.setAutoCommit(false);  //将自动提交设置为false
            st = conn.createStatement();
            for (String sql : sqls) {
                //添加要批量执行的SQL
                st.addBatch(sql);
            }
            //执行批处理SQL语句
            counts = st.executeBatch();
            //清除批处理命令
            st.clearBatch();
            conn.commit();      //当多个操作成功后手动提交
        }catch (Exception e) {
            try {
                conn.rollback();//事务回滚
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            closeAll(conn, st,null);
        }
        return counts;
    }

    private static String getSetter(String fieldName){
        //传入属性名 拼接set方法
        return "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    }

    private static String getGetter(String fieldName){
        //传入属性名 拼接get方法
        return "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    }

    /**
     * 简单查询
     * @param t
     * @return
     */
    public <T>List<T> simpleList(T t) throws Exception{
        // 获取实体类的所有属性，返回Field数组
        StringBuffer sql = new StringBuffer("select * from "+t.getClass().getSimpleName());
        Field[] field = t.getClass().getDeclaredFields();
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
        boolean flag = false;
        List<Object> objsList = new ArrayList<Object>();
        for (int i = 0; i < field.length; i++) {
            String fieldName = field[i].getName();
            Method method=t.getClass().getMethod(getGetter(fieldName));
            String type = field[i].getType().toString();
            Object obj = method.invoke(t);
            if(obj != null){
                if(!flag){
                    sql.append(" where "+fieldName+" = ? ");
                    flag = true;
                }else{
                    sql.append("and "+fieldName+" = ? ");
                }
                objsList.add(String.valueOf(obj));
            }
        }
        Object[] paras = objsList.toArray();
        List<T> alist = (List<T>) queryList(sql.toString(),paras,t.getClass());
        return alist;
    }

    /**
     * 新增对象信息
     * @param t 需要新增的对象
     * @return 受影响的行数
     * @throws Exception
     */
    public <T> int add(T t) throws Exception{
        // 获取实体类的所有属性，返回Field数组
        StringBuffer sql = new StringBuffer("INSERT INTO "+t.getClass().getSimpleName());
        Field[] field = t.getClass().getDeclaredFields();
        List<Object> objsList = new ArrayList<Object>();//参数列表
        StringBuffer colsql = new StringBuffer("(");//列部分
        StringBuffer valsql = new StringBuffer(" values(");//参数部分

        for (int i = 0; i < field.length; i++) {
            String fieldName = field[i].getName();
            Method method=t.getClass().getMethod(getGetter(fieldName));
            String type = field[i].getType().toString();
            Object obj = method.invoke(t);
            if(obj != null){//该列有值
                colsql.append(fieldName+",");
                valsql.append("?,");
                objsList.add(String.valueOf(obj));//将参数累加起来
            }
        }
        colsql.deleteCharAt(colsql.length() - 1);//移除最后一个字符
        colsql.append(")");
        valsql.deleteCharAt(valsql.length() - 1);
        valsql.append(")");
        sql.append(colsql);
        sql.append(valsql);

        return runNonQuerySQL(sql.toString(), objsList.toArray());
    }

    /**s
     * 删除对象信息
     * @param t 需要新增的对象
     * @return 受影响的行数
     * @throws Exception
     */
    public <T> int del(T t) throws Exception{
        StringBuffer sql = new StringBuffer("DELETE FROM "+t.getClass().getSimpleName());
        Field[] field = t.getClass().getDeclaredFields();

        boolean flag = false;
        List<Object> objsList = new ArrayList<Object>();
        for (int i = 0; i < field.length; i++) {
            String fieldName = field[i].getName();
            Method method=t.getClass().getMethod(getGetter(fieldName));
            String type = field[i].getType().toString();
            Object obj = method.invoke(t);
            if(obj != null){
                if(!flag){
                    sql.append(" where "+fieldName+" = ? ");
                    flag = true;
                }else{
                    sql.append("and "+fieldName+" = ? ");
                }
                objsList.add(String.valueOf(obj));
            }
        }
        if(objsList.size() == 0){
            //无参数值，直接删除整张表数据，不安全
            throw new Exception("不允许删除整张表的操作,删除操作必须要有条件");
        }
        Object[] paras = objsList.toArray();
        return runNonQuerySQL(sql.toString(), objsList.toArray());
    }

    /**
     * 修改对象信息(必须要有主键且主键不能被修改)
     * @param t 需要修改的对象
     * @return 受影响的行数
     * @throws Exception
     */
    public <T> int modify(T t) throws Exception{
        StringBuffer sql = new StringBuffer("UPDATE "+t.getClass().getSimpleName());
        Field[] field = t.getClass().getDeclaredFields();
        List<Object> objsList = new ArrayList<Object>();
        boolean flag = false;//记录是否为第一个属性
        StringBuffer colsql = new StringBuffer("");//列部分
        StringBuffer valsql = new StringBuffer();
        String primaryKey = "";
        String primaryValue = "";//主键值

        String className = t.getClass().getSimpleName();//获取类名

        //1.获取对象t的主键
        primaryKey = getMysqlTablePK(className);
        if(primaryKey == null){
            //没有主键
            throw new Exception("该数据库表尚未标注主键");
        }
        //2.反射主键的值
        Method pkmethod=t.getClass().getMethod(getGetter(primaryKey));
        primaryValue = pkmethod.invoke(t).toString();
        if(primaryValue == null){
            //主键值为null
            throw new Exception("该数据库主键值为null");
        }
        //3.查询出对应的数据库对象
        String searchSql = "select * from " + className + " where "+primaryKey+" = ?";
        Object[] params = {primaryValue};
        T model = (T) queryList(searchSql, params,t.getClass()).get(0);

        //4.与对象t进行对比 并生成SQL语句
        for (int i = 0; i < field.length; i++) {
            String fieldName  = field[i].getName();
            Method method=t.getClass().getMethod(getGetter(fieldName));
            String type = field[i].getType().toString();
            Object obj = method.invoke(t);

            Method modelMethod=model.getClass().getMethod(getGetter(fieldName));
            Object modelObj = modelMethod.invoke(model);

            //如果两个值不相同，则需要修改
            if((obj == null && modelObj != null) || (obj != null && modelObj == null) || (!obj.equals(modelObj))){
                if(!flag){
                    colsql.append(" set "+fieldName+" = ? ,");
                    flag = true;
                }else{
                    colsql.append(fieldName+" = ? ,");
                }
                objsList.add(String.valueOf(obj));
            }


        }
        valsql.append("where "+primaryKey+" = ?");
        colsql.deleteCharAt(colsql.length() - 1);//移除最后一个字符
        sql.append(colsql).append(valsql);
        objsList.add(primaryValue);
        Object[] paras = objsList.toArray();
        int count = runNonQuerySQL(sql.toString(), paras);
        return count;
    }

    /**
     * 查询方法
     * @param sql 查询SQL语句
     * @param params 查询参数条件
     * @param T 查询的实体类
     * @return 该类型的列表
     */
    public <T>List<T> queryList(String sql,Object[] params,Class<T> T){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<T> list=new ArrayList<T>();
        String className = T.getSimpleName();//获取类名
        //包装类名列表
        List<String> pgs = Arrays.asList("String","Byte","Boolean","Short","Character","Integer","Long","Float","Double");
        try {
            conn=getConnection();
            ps=conn.prepareStatement(sql);
            //传参数
            if(params!=null&& params.length>0){
                for(int i=0;i<params.length;i++){
                    ps.setObject(i+1, params[i]);
                }
            }
            rs=ps.executeQuery();
            ResultSetMetaData metaData=rs.getMetaData();//读取该SQL语句返回的列
            int count=metaData.getColumnCount();//得到SQL语句返回的列数
            while(rs.next()){
                T temp = null;
                if(pgs.contains(className)){
                    //包装类使用构造函数复制
                    //取得带参构造方法实例
                    Constructor<T> constructor = T.getDeclaredConstructor(String.class);
                    //使用带参构造方法创建对象
                    temp = constructor.newInstance(rs.getObject(1).toString());
                }else{
                    //非包装类使用更灵活的set方式绑定
                    temp =T.newInstance();//动态生成T的对象
                    for(int i=0;i<count;i++){
                        String fieldName=metaData.getColumnName(i+1);
                        Field field=T.getDeclaredField(fieldName);
                        Method method=T.getMethod(getSetter(fieldName), field.getType());
                        method.invoke(temp, rs.getObject(i+1));
                    }

                }
                //将生成的对象保存在列表中
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeAll(conn, ps, rs);
        }
        return list;
    }

    /**
     * 执行查询的SQL语句并返回单个值
     * @param sql 需要执行的SQL语句
     * @param params 查询参数条件
     * @param T 查询的实体类
     * @return 该类型的对象 如果该对象不存在则返回null
     */
    public <T>T selectOne(String sql,Object[] params,Class<T> T){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        T temp = null;
        String className = T.getSimpleName();//获取类名
        //包装类名列表
        List<String> pgs = Arrays.asList("Byte","Boolean","Short","Character","Integer","Long","Float","Double");
        try {
            conn=getConnection();
            ps=conn.prepareStatement(sql);
            //传参数
            if(params!=null&& params.length>0){
                for(int i=0;i<params.length;i++){
                    ps.setObject(i+1, params[i]);
                }
            }
            rs=ps.executeQuery();
            ResultSetMetaData metaData=rs.getMetaData();//读取该SQL语句返回的列
            int count=metaData.getColumnCount();//得到SQL语句返回的列数
            if(rs.next()){
                if(pgs.contains(className)){
                    //包装类使用构造函数复制
                    //取得带参构造方法实例
                    Constructor<T> constructor = T.getDeclaredConstructor(String.class);
                    //使用带参构造方法创建对象
                    temp = constructor.newInstance(rs.getObject(1).toString());
                }else{
                    //非包装类使用更灵活的set方式绑定
                    temp =T.newInstance();//动态生成T的对象
                    for(int i=0;i<count;i++){
                        String fieldName=metaData.getColumnName(i+1);
                        Field field=T.getDeclaredField(fieldName);
                        Method method=T.getMethod(getSetter(fieldName), field.getType());
                        method.invoke(temp, rs.getObject(i+1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeAll(conn, ps, rs);
        }
        return temp;
    }

    /**
     * 获取指定表的主键
     * @param tableName 指定的表名
     * @throws Exception
     */
    public String getMysqlTablePK(String tableName) throws Exception {

        Connection conn = getConnection();
        ResultSet rs;
        String colName = null;//主键列名
        // 适用mysql
        rs = conn.getMetaData().getPrimaryKeys(conn.getCatalog().toUpperCase(),null, tableName.toUpperCase());
        int i = 0;
        if (rs.next()) {
            colName = getTransStr(rs.getString("COLUMN_NAME"),false);
        }
        closeAll(conn, null, rs);
        return colName;
    }

    /**
     * 将mysql中表名和字段名转换成驼峰形式
     * @param before 原名称
     * @param firstChar2Upper 设置是否需要首字母大写
     * @return 驼峰命名格式
     */
    private String getTransStr(String before, boolean firstChar2Upper) {
        //不带"_"的字符串,则直接首字母大写后返回
        if (!before.contains("_"))
            return firstChar2Upper ? initCap(before) : before;
        String[] strs = before.split("_");
        StringBuffer after = null;
        if (firstChar2Upper) {
            after = new StringBuffer(initCap(strs[0]));
        } else {
            after = new StringBuffer(strs[0]);
        }
        if (strs.length > 1) {
            for (int i=1; i<strs.length; i++)
                after.append(initCap(strs[i]));
        }
        return after.toString();
    }

    /**
     * 将传入字符串的首字母转成大写
     * @param str
     * @return
     */
    private String initCap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z')
            ch[0] = (char) (ch[0] - 32);
        return new String(ch);
    }
}