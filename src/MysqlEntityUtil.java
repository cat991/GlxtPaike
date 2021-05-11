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
 
    private String tableName;// ����
    private String[] colNames; // ��������
    private String[] colTypes; // ������������
    private int[] colSizes; // ������С����
    private boolean needUtil = false; // �Ƿ���Ҫ�����java.util.*
    private boolean needSql = false; // �Ƿ���Ҫ�����java.sql.*
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String SQL = "SELECT * FROM ";// ���ݿ����
 
    // TODO ��Ҫ�޸ĵĵط�			
    private static final String URL = "jdbc:mysql://localhost:3306/guanli?useUnicode=true&characterEncoding=utf8";
    private static final String NAME = "sa";
    private static final String PASS = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private String packageOutPath = "entity";// ָ��ʵ���������ڰ���·��
    private String authorName = "��è";// ��������
    private String[] generateTables = null;//ָ����Ҫ���ɵı�ı�����ȫ����������Ϊnull
 
    /**
     * ��Ĺ��췽��
     */
    private MysqlEntityUtil() {
    }
 
    /**
     * ����class����������
     * @return
     */
    private String parse() {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + packageOutPath + ";\r\n");
        sb.append("\r\n");
        // �ж��Ƿ��빤�߰�
        if (needUtil) {
            sb.append("import java.util.Date;\r\n");
        }
        if (needSql) {
            sb.append("import java.sql.*;\r\n");
        }
        // ע�Ͳ���
        sb.append("/**\r\n");
        sb.append(" * table name:  " + tableName + "\r\n");
        sb.append(" * author name: " + authorName + "\r\n");
        sb.append(" * create time: " + SDF.format(new Date()) + "\r\n");
        sb.append(" */ \r\n");
        // ʵ�岿��
        sb.append("public class " + getTransStr(tableName, true) + "{\r\n\r\n");
        processAllAttrs(sb);// ����
        sb.append("\r\n");
        processAllMethod(sb);// get set����
        sb.append("}\r\n");
        return sb.toString();
    }
 
    /**
     * �������г�Ա����
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + getTransStr(colNames[i], false) + ";\r\n");
        }
    }
 
    /**
     * ��������get/set����
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
	 * �������ַ���������ĸת�ɴ�д
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
     * ��mysql�б������ֶ���ת�����շ���ʽ
     * @param before ԭ����
     * @param firstChar2Upper �����Ƿ���Ҫ����ĸ��д
     * @return �շ�������ʽ
     */
    private String getTransStr(String before, boolean firstChar2Upper) {
        //����"_"���ַ���,��ֱ������ĸ��д�󷵻�
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
	 * ����sql�ֶ���������Ӧ��Java����
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
     * ���ɷ���
     * @throws Exception
     */
    private void generate() throws Exception {
        //�����ݿ������
        Connection con;
        PreparedStatement pStemt = null;
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, NAME, PASS);
        //��ȡ���ݿ��Ԫ����(�����˸����ݿ������е���Ϣ)
        DatabaseMetaData db = con.getMetaData();
        //�Ƿ���ָ�����ɱ���ָ����ֱ����ָ����û����ȫ������
        List<String> tableNames = new ArrayList<String>();
        if (generateTables == null) {
            //��Ԫ�����л�ȡ�����еı���
            ResultSet rs = db.getTables(null, null, null, new String[] { "TABLE" });//����ר�Ų������ָ��
            while (rs.next()){ 
            	if(!rs.getString(3).equals("trace_xe_action_map") && !rs.getString(3).equals("trace_xe_event_map")){
            		tableNames.add(rs.getString(3));//�������ݿ������еı�����ѯ��������ӵ�tableNames������
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
            int size = rsmd.getColumnCount();//��ȡ������
            colNames = new String[size];//��¼�е�����
            colTypes = new String[size];//��¼�е�����
            colSizes = new int[size];//��¼�еĴ�С
            //��ȡ�������Ϣ
            for (int i = 0; i < size; i++) {
                colNames[i] = rsmd.getColumnName(i + 1);//��ȡ��Ӧ�е�����
                colTypes[i] = rsmd.getColumnTypeName(i + 1);//��ȡ��Ӧ�е�����
                if (colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("Timestamp"))
                    needUtil = true;//�ж��Ƿ���Ҫ����
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text"))
                    needSql = true;
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);//��ȡ�еĴ�С
            }
            //��������class����������
            String content = parse();
            //��������ļ�
            File directory = new File("");
            String dirName = directory.getAbsolutePath() + "/src/" + packageOutPath.replace(".", "/");
            File dir = new File(dirName);
            if (!dir.exists() && dir.mkdirs()) System.out.println("generate dir ��" + dirName + "��");
            String javaPath = dirName + "/" + getTransStr(tableName, true) + ".java";
            FileWriter fw = new FileWriter(javaPath);
            pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            System.out.println("������: ��" + tableName + "��");
        }
        if (pw != null)
            pw.close();
    }
    
    public static void main(String[] args) {
        try {
            INSTANCE.generate();
            System.out.println("ʵ�����ɳɹ�");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}