import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//————————————————
//    版权声明：本文为CSDN博主「蛋蛋要学编程」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/u011024652/article/details/51753481
public class option {
//    查询所有的数据，在option中定义一个queryAll()方法：
//    这里使用Connection.createStatement()方法获取一个Statement对象，这个对象里面有很多的方法可以操作数据库，
// 使用excuteQuery(String sql)执行查询操作，查询结果为一个结果集ResultSet，可以通过这个结果集获取相关的信息。

    public List<UserVo> queryAll() throws Exception {
        Connection conn = connection.connectDB();
        String sql = "SELECT * FROM tbl_user_info";
        List<UserVo> userList = new ArrayList<UserVo>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        iteration(userList, rs);

        return userList;
    }
////    检查信息:发现重复的代码
////    方法，必须针对当前运行时对象调用。? ，合并为了方法iteration!!,就是个循环；
    private void iteration(List<UserVo> userList, ResultSet rs) throws SQLException {
        while(rs.next()) {
            UserVo user = new UserVo();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("user_name"));
            user.setAge(rs.getInt("age"));
            user.setSex(rs.getInt("sex"));
            user.setCreateDt(rs.getDate("create_dt"));

            userList.add(user);
        }
    }
//测试 用，可以直接运行   main进行


    //改成main来测试查询所有数据
    public static void main1(String[] args) {
        option dao = new option();

        try {
            List<UserVo> userList = dao.queryAll();
            for(UserVo user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    根据条件查询，定义一个 queryByParams 方法：
public List<UserVo> queryByParams(List<Map<String, Object>> params) throws Exception {
    Connection conn = connection.connectDB();
    StringBuilder sql = new StringBuilder("SELECT * FROM tbl_user_info WHERE 1=1 ");

    for(Map<String, Object> param : params) {
        sql.append(" and ");
        sql.append(" " + param.get("col") + " ");
        sql.append(" " + param.get("rel") + " ");
        sql.append(" " + param.get("value") + " ");
    }
    System.out.println(sql.toString());

    List<UserVo> userList = new ArrayList<UserVo>();

    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql.toString());
    iteration(userList, rs);
    return userList;
}
//    这个方法可以自由选择查询的条件，只需要向方法中传入一个条件的List即可，这些条件都是由Map组成的，
// 每一个Map包含三个元素，col表示查询条件对应哪一列，rel表示查询条件的关系是什么，
// value是指查询条件的值。这样写集成了多查询条件的方法，很多的业务下，查询的逻辑可能很多，
// 这样写只用一个统一的方法就可以解决多种不同查询条件的业务逻辑。

    public static void main2(String[] args) {
        option dao = new option();

        List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put("col", "user_name");
        param1.put("rel", "like");
        param1.put("value", "'%朱晓锋%'");
        params.add(param1);

        Map<String, Object> param2 = new HashMap<String, Object>();
        param2.put("col", "sex");
        param2.put("rel", "=");
        param2.put("value", 1);
        params.add(param2);
        try {
            List<UserVo> userList = dao.queryByParams(params);
            for(UserVo user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//
//    在这个main方法中设定了两个查询条件，一是user_name like %John%，另一个是sex=1，当然条件也可以是其他的，执行程序运行结果为：
//    SELECT * FROM tbl_user_info WHERE 1=1  and  user_name  like  '%John%'  and  sex  =  1 
//    UserVo [id=6, userName=John, age=19, sex=1, createDt=2016-06-24]
//
//    2 ）增加
//    现在在 option 中写一个 addUser 方法用于新增一条信息：
public void addUser(UserVo user) throws Exception {
    Connection conn = connection.connectDB();
    String sql = "INSERT INTO tbl_user_info(user_name, age, sex, create_dt) "
            + " VALUES(?, ?, ?, ?)";

    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, user.getUserName());
    pstmt.setInt(2, user.getAge());
    pstmt.setInt(3, user.getSex());
    pstmt.setDate(4, new Date(new java.util.Date().getTime()));

    pstmt.execute();
}
//这个方法使用Connection.prepareStatement(String sql)方法获取一个PreparedStatement对象，使用这个方法可以传入带参数的SQL语句，而参数的值可以通过PreparedStatement.setXXX(int index, XXX value)的方法指定，其中XXX为各种不同的类型，index指定第几个参数的下标。指定了参数的值之后，便可以执行excute()方法执行SQL语句了。
//    接下来写一个main方法来验证这个增加的方法：
//测试三增加记录
    public static void main3(String[] args) {
        option dao = new option();
        UserVo user = new UserVo();

        user.setUserName("Tom");
        user.setAge(20);
        user.setSex(1);
        try {
            dao.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//3）删除
//    接下来再写一个删除的方法，根据用户的id来删除数据：

    public void deleteUser(int id) throws Exception {
        Connection conn = connection.connectDB();
        String sql = "DELETE FROM tbl_user_info WHERE id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        pstmt.execute();
    }
//    然后写一个main方法来验证：
public static void main4(String[] args) {
    option dao = new option();

    try {
        dao.deleteUser(4);/*删除id为4的*/
    } catch (Exception e) {
        e.printStackTrace();
    }
}
//4）更新数据库
//    最后来看一下更新数据库：
public void updateUser(UserVo user) throws Exception {
    Connection conn = connection.connectDB();
    String sql = "UPDATE tbl_user_info SET user_name=?, age=?, sex=?"
            + " WHERE id=?";

    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, user.getUserName());
    pstmt.setInt(2, user.getAge());
    pstmt.setInt(3, user.getSex());
    pstmt.setInt(4, user.getId());

    pstmt.executeUpdate();
}
//    从SQL语句中可以看出更新也是根据用户的id进行选择性的更新的。
//    写一个main方法来验证：
public static void main(String[] args) {
    option dao = new option();
    UserVo user = new UserVo();

    user.setUserName("Mary");
    user.setAge(30);
    user.setSex(0);
    user.setId(5);

    try {
        dao.updateUser(user);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
