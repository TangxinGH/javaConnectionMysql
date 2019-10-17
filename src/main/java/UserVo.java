   // 定义一个 Bean，与数据库表中的各个字段对应：

   import java.util.Date;
    public class UserVo {
 private int id;
 private String userName;
 private int age;
 private int sex;
 private Date createDt;
        public int getId() {
         return id;
         }

        public void setId(int id) {
   this.id = id; }
         public String getUserName() {
        return userName;
       }

 public void setUserName(String userName) {
 this.userName = userName;
 }

   public int getAge() {
        return age;
  }

  public void setAge(int age) {
            this.age = age;
         }

         public int getSex() {
         return sex;
        }

   public void setSex(int sex) {
         this.sex = sex;
      }

     public Date getCreateDt() {
        return createDt;
        }

     public void setCreateDt(Date createDt) { this.createDt = createDt;
       }

       @Override
 public String toString() {
       return "UserVO [id=" + id + ", userName=" + userName + ", age=" + age
       + ", sex=" + sex + ", createDt=" + createDt + "]";
       }

    }