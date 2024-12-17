package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    // get all categories
    @Override
    public List<Category> getAllCategories()
    {
        List<Category> categories = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = preparedStatement.executeQuery()){

            while(rs.next()){
                categories.add(mapRow(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return categories;
    }
    // get category by id
    @Override
    public Category getById(int categoryId)
    {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories WHERE category_id = ?");
           ){
            preparedStatement.setInt(1,categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return mapRow(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    // create a new category
    @Override
    public Category create(Category category)
    {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories(name,description) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ){
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getDescription());
            int rs = preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            category.setCategoryId(keys.getInt(1));
        } catch (SQLException e){
            e.printStackTrace();}

        return category;
    }
    // update category
    @Override
    public void update(int categoryId, Category category)
    {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categories SET name = ?, description = ?");
        ){
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getDescription());
            int rs = preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();}
    }
    // delete category
    @Override
    public void delete(int categoryId)
    {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE category_id = ?");
        ){
            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();}
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
