//定义一个数据库连接类，用于获取 MySQL 的连接



    import java.sql.Connection;
import java.sql.DriverManager;

     public class connection {
//user是数据库;
    private static final String URI = "jdbc:mysql://localhost:3306/user?useSSL=false&serverTimezone=UTC";
// + "user=tangxin&password=123456&useUnicode=true&characterEncoding=UTF-8";

//        private static final String DRIVER = "com.mysql.jdbc.Driver";//// MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
// MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
 private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
         static final String USER = "tangxin";
         static final String PASS = "123456";
        public static Connection connectDB() throws Exception {
         //1、加载数据库驱动
        Class.forName(DRIVER);
        //2、获取数据库连接
         Connection conn = DriverManager.getConnection(URI,USER,PASS);



         //
         return conn;
        }

   }
