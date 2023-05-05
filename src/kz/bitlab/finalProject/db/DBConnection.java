package kz.bitlab.finalProject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {
    private static Connection connection;
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/final_news",
                    "root",
                    "root"
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Users getUser(String email){
        Users user=null;
        try{
            PreparedStatement statement=connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ? LIMIT 1"
            );
            statement.setString(1, email);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                user=new Users();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRole(resultSet.getInt("role_id"));
            }
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static boolean addUser(Users user){
        int rows=0;

        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "INSERT INTO users(email, password, full_name, role_id)" +
                    "VALUES(?, ?, ?, ?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRole());

            rows=statement.executeUpdate();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean addNews(News news){
        int rows=0;
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "INSERT INTO news (title, content, postdate, user_id, category_id)" +
                    "VALUES (?, ?, NOW(), ?, ?)");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getUser().getId());
            statement.setLong(4, news.getCategory().getId());

            rows=statement.executeUpdate();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static ArrayList<News> getNews(){
        ArrayList<News> news=new ArrayList<>();
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.postdate, n.user_id, n.category_id, u.full_name, c.name " +
                    "FROM news n " +
                    "INNER JOIN users u ON u.id=n.user_id " +
                    "INNER JOIN news_categories c ON c.id=n.category_id " +
                    "ORDER BY n.postdate DESC ");
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                News n=new News();
                n.setId(resultSet.getLong("id"));
                n.setTitle(resultSet.getString("title"));
                n.setContent(resultSet.getString("content"));
                n.setPostDate(resultSet.getTimestamp("postdate"));
                Users user=new Users();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                n.setUser(user);
                Categories category=new Categories();
                category.setId(resultSet.getLong("category_id"));
                category.setName(resultSet.getString("name"));
                n.setCategory(category);
                news.add(n);
            }
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public static News getNewsById(Long id){
        News news=null;
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.postdate, n.user_id, n.category_id, u.full_name, c.name " +
                    "FROM news n " +
                    "INNER JOIN users u ON u.id=n.user_id " +
                    "INNER JOIN news_categories c ON c.id=n.category_id " +
                    "WHERE n.id=? ");

            statement.setLong(1, id);

            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                news=new News();
                news.setId(resultSet.getLong("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setPostDate(resultSet.getTimestamp("postdate"));
                Users user=new Users();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                news.setUser(user);
                Categories category=new Categories();
                category.setId(resultSet.getLong("category_id"));
                category.setName(resultSet.getString("name"));
                news.setCategory(category);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public static boolean addComment(Comments comment){
        int rows=0;
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "INSERT INTO comments (user_id, news_id, comment, postdate) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setLong(1, comment.getUser().getId());
            statement.setLong(2, comment.getNews().getId());
            statement.setString(3, comment.getComment());

            rows=statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static ArrayList<Comments> getComments(Long newsId){
        ArrayList<Comments> comments=new ArrayList<>();
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT c.id, c.user_id, c.comment, c.news_id, c.postdate, u.full_name " +
                    "FROM comments c " +
                    "INNER JOIN users u ON c.user_id=u.id " +
                    "WHERE c.news_id = ? " +
                    "ORDER BY c.postdate DESC");

            statement.setLong(1, newsId);

            ResultSet resultSet= statement.executeQuery();
            while(resultSet.next()){
                Comments com=new Comments();
                com.setId(resultSet.getLong("id"));
                com.setComment(resultSet.getString("comment"));
                com.setPostDate(resultSet.getTimestamp("postdate"));
                Users user=new Users();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                com.setUser(user);
                News news=new News();
                news.setId(resultSet.getLong("news_id"));
                com.setNews(news);
                comments.add(com);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }

    public static ArrayList<Categories> getCategories(){
        ArrayList<Categories> categories=new ArrayList<>();
        try{
            PreparedStatement statement=connection.prepareStatement(
                    "SELECT * FROM news_categories"
            );
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Long id=resultSet.getLong("id");
                String name=resultSet.getString("name");
                Categories category=new Categories(id, name);
                categories.add(category);
            }
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return categories;
    }

    public static void updateNews(News news){
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "UPDATE news SET title = ?, content = ?, category_id = ? " +
                    "WHERE id=? ");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getCategory().getId());
            statement.setLong(4, news.getId());

            statement.executeUpdate();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteNews(Long id){
        try{
            PreparedStatement statement= connection.prepareStatement("" +
                    "DELETE FROM news WHERE id = ? ");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteComment(Long id){
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "DELETE FROM comments WHERE id = ? ");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
