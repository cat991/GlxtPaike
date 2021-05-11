import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MysqlEntityUtil {
    private static final MysqlEntityUtil INSTANCE = new MysqlEntityUtil();
 
    private String tableName;// 表名
    private String[] colNames; // 列名数组
    private String[] colTypes; // 列名类型数组
    private int[] colSizes; // 列名大小数组
    private boolean needUtil = false; // 是否需要导入包java.util.*
    private boolean needSql = false; // 是否需要导入包java.sql.*
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String SQL = "SELECT * FROM ";// 数据库操作
 
    // TODO 需要修改的地方			
    private static final String URL = "jdbc:mysql://localhost:3306/guanli?useUnicode=true&characterEncoding=utf8";
    private static final String NAME = "sa";
    private static final String PASS = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private String packageOutPath = "entity";// 指定实体生成所在包的路径
    private String authorName = "黑猫";// 作者名字
    private String[] generateTables = null;//指定需要生成的表的表名，全部生成设置为null
 
    /**
     * 类的构造方法
     */
    private MysqlEntityUtil() {
    }
 
    /**
     * 生成class的所有内容
     * @return
     */
    private String parse() {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + packageOutPath + ";\r\n");
        sb.append("\r\n");
        // 判断是否导入工具包
        if (needUtil) {
            sb.append("import java.util.Date;\r\n");
        }
        if (needSql) {
            sb.append("import java.sql.*;\r\n");
        }
        // 注释部分
        sb.append("/**\r\n");
        sb.append(" * table name:  " + tableName + "\r\n");
        sb.append(" * author name: " + authorName + "\r\n");
        sb.append(" * create time: " + SDF.format(new Date()) + "\r\n");
        sb.append(" */ \r\n");
        // 实体部分
        sb.append("public class " + getTransStr(tableName, true) + "{\r\n\r\n");
        processAllAttrs(sb);// 属性
        sb.append("\r\n");
        processAllMethod(sb);// get set方法
        sb.append("}\r\n");
        return sb.toString();
    }
 
    /**
     * 生成所有成员变量
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + getTransStr(colNames[i], false) + ";\r\n");
        }
    }
 
    /**
     * 生成所有get/set方法
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("\tpublic void set" + getTransStr(colNames[i], true) + "(" + sqlType2JavaType(colTypes[i]) + " "
                    + getTransStr(colNames[i], false) + "){\r\n");
            sb.append("\t\tthis." + getTransStr(colNames[i], false) + "=" + getTransStr(colNames[i], false) + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + getTransStr(colNames[i], true) + "(){\r\n");
            sb.append("\t\treturn " + getTransStr(colNames[i], false) + ";\r\n");
            sb.append("\t}\r\n");
        }
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
	 * 查找sql字段类型所对应的Java类型
	 * @param sqlType
	 * @return
	 */
    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int")|| sqlType.equalsIgnoreCase("integer")
				|| sqlType.equalsIgnoreCase("integer unsigned")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text") || sqlType.equalsIgnoreCase("uniqueidentifier")|| sqlType.equalsIgnoreCase("ntext")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("Timestamp")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image") || sqlType.equalsIgnoreCase("blob")) {
            return "Blob";
        } else if(sqlType.equalsIgnoreCase("varbinary")){
        	return "byte[]";
        }
        return null;
    }
 
    /**
     * 生成方法
     * @throws Exception
     */
    private void generate() throws Exception {
        //与数据库的连接
        Connection con;
        PreparedStatement pStemt = null;
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, NAME, PASS);
        //获取数据库的元数据(包含了该数据库下所有的信息)
        DatabaseMetaData db = con.getMetaData();
        //是否有指定生成表，有指定则直接用指定表，没有则全表生成
        List<String> tableNames = new ArrayList<String>();
        if (generateTables == null) {
            //从元数据中获取到所有的表名
            ResultSet rs = db.getTables(null, null, null, new String[] { "TABLE" });//生成专门查表名的指针
            while (rs.next()){ 
            	if(!rs.getString(3).equals("trace_xe_action_map") && !rs.getString(3).equals("trace_xe_event_map")){
            		tableNames.add(rs.getString(3));//将该数据库下所有的表名查询出来并添加到tableNames集合中
            	}
            }
        } else {
            for (String tableName : generateTables) tableNames.add(tableName);
        }
        String tableSql;
        PrintWriter pw = null;
        for (int j = 0; j < tableNames.size(); j++) {
            tableName = tableNames.get(j);
            tableSql = SQL + tableName;
            pStemt = con.prepareStatement(tableSql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();//获取列数量
            colNames = new String[size];//记录列的名称
            colTypes = new String[size];//记录列的类型
            colSizes = new int[size];//记录列的大小
            //获取所需的信息
            for (int i = 0; i < size; i++) {
                colNames[i] = rsmd.getColumnName(i + 1);//获取对应列的名称
                colTypes[i] = rsmd.getColumnTypeName(i + 1);//获取对应列的类型
                if (colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("Timestamp"))
                    needUtil = true;//判断是否需要导包
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text"))
                    needSql = true;
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);//获取列的大小
            }
            //解析生成class的所有内容
            String content = parse();
            //输出生成文件
            File directory = new File("");
            String dirName = directory.getAbsolutePath() + "/src/" + packageOutPath.replace(".", "/");
            File dir = new File(dirName);
            if (!dir.exists() && dir.mkdirs()) System.out.println("generate dir 【" + dirName + "】");
            String javaPath = dirName + "/" + getTransStr(tableName, true) + ".java";
            FileWriter fw = new FileWriter(javaPath);
            pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            System.out.println("生成类: 【" + tableName + "】");
        }
        if (pw != null)
            pw.close();
    }
    
    public static void main(String[] args) {
        try {
            INSTANCE.generate();
            System.out.println("实体生成成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}