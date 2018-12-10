import java.sql.*;

public class DockerConnectDB {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/baza";

   static final String USER = "kz";
   static final String PASS = "kz";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      Class.forName("com.mysql.jdbc.Driver");

      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	  
	  stmt = conn.createStatement();
	  sqlCreate = "CREATE TABLE Persons (PersonID int, LastName varchar(255), FirstName varchar(255), Address varchar(255), City varchar(255) )";
	  stmt.executeQuery(sqlCreate);
	  stmt.close();
	  
	  stmt = conn.createStatement();
      System.out.println("Inserting Data to Table");
      sqlInsert = "INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (1, 'n1', 'i1', 'a1','c1'), (2, 'n2', 'i2', 'u2','c2'), (3, 'n3', 'i3', 'u3','c3')";
      stmt.execute(sqlInsert);
	  stmt.close();
		
      stmt = conn.createStatement();
      String sql;
      sqlSelect = "SELECT PersonID, FirstName, LastName, Address, City FROM Persons";
      ResultSet rs = stmt.executeQuery(sqlSelect);

      while(rs.next()){
         int id  = rs.getInt("PersonID");
         String first = rs.getString("FirstName");
         String last = rs.getString("LastName");
		 String address = rs.getString("Address");
		 String city = rs.getString("City");

         System.out.println("ID: " + id);
         System.out.println(", First: " + first);
         System.out.println(", Last: " + last);
		 System.out.println(", Address: " + address);
		 System.out.println(", City: " + city);
      }
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
 }
}