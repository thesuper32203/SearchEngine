<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Accio.SearchResult"%>
<html>
<body>
<form action = "Search">
    <input = "text" name = "keyword"></input>
    <button type = "submit" style=display:block">Search</button>
</form>
    <table border = 2>
    <tr>
        <th>Title</th>
        <th>Link</th>
    </tr>
    <%
        ArrayList<SearchResult> results = (ArrayList<SearchResult>)request.getAttribute("results");
        for(SearchResult result : results){

    %>
    <tr>
        <td><%out.println(result.getTitle());%></td>
        <td><a href = "<%out.println(result.getLink());%>"><%out.println(result.getLink());%><a></td>

    </tr>
    <%
        }
    %>
    </table>

</body>
</html>