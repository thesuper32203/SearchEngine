package com.Accio;

//import jdk.tools.jlink.internal.Jlink;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
       //Getting key  word from frontend
        String kewWord = request.getParameter("keyword");

        try {
            //setting up connection from database
        Connection connection = DatabaseConnection.getConnection();
        //Store the query of user
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?,?);");
            preparedStatement.setString(1, kewWord);
            preparedStatement.setString(2, "http://localhost:8080/SearchEngine/Search?keyword="+kewWord);
            preparedStatement.executeUpdate();
       //getting results after running the ranking query
            ResultSet resultSet = connection.createStatement().executeQuery(
                    "select pageTitle, pageLink, (length(lower(pageText)) - length(replace(lower(pageText), '"+kewWord.toLowerCase()+"','')))/length('"+kewWord.toLowerCase()+"') as counteroccurence from pages order by counteroccurence desc limit 30;\n");
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();

            //transferring value from resultSet to results arraylist
            while(resultSet.next()){
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }

            //displaying results arraylist in console
            for(SearchResult result: results){
                System.out.println(result.getTitle()+ "\n"+result.getLink()+"\n");
            }

        request.setAttribute("results", results);
        request.getRequestDispatcher("search.jsp").forward(request, response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }






}
