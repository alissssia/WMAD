<%-- 
    Document   : category
    Created on : Mar 8, 2026, 2:57:30 PM
    Author     : entel






       <%
        List<Product> products = (List<Product>) request.getAttribute("products");

        if(products != null){
        %>

        <h3>Products</h3>

        <table border="1">
        <%
        for(Product product : products){
        %>

        <tr>
        <td>
        <img src="img/products/<%=product.getName()%>.jpg" width="80">
        </td>

        <td>
        <%=product.getName()%>
        </td>

        <td>
        <%=product.getPrice()%> €
        </td>

        <td>
        <button>add to cart</button>
        </td>

        </tr>

        <%
        }
        %>
        </table>

        <%
        }
        %>
--%>

<%@ page import="entity.Category" %>
<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>

<%
List<Category> categories = (List<Category>) request.getAttribute("categories");
List<Product> products = (List<Product>) request.getAttribute("products");
%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>eCommerce Sample</title>
    </head>
    <body>
    </body>
</html>

<table width="70%" border="1" cellspacing="0" cellpadding="5">
    <tr>
        
        <!-- Columna izquierda: categorías -->
        <td width="20%" valign="top">
            <table width="100%" border="1" cellspacing="0" cellpadding="3">
                <%
                if (categories != null) {
                    for (Category category : categories) {
                %>
                <tr>
                    <td align="center">
                        <a href="<%=request.getContextPath()%>/category.do?cat=<%=category.getId()%>">
                            <%=category.getName()%>
                        </a>
                    </td>
                </tr>
                <%
                    }
                }
                %>
            </table>
        </td>

        <!-- Columna derecha: productos -->
        <td width="80%" valign="top">
            <%
            if (products != null && !products.isEmpty()) {
            %>
            <table width="100%" border="1" cellspacing="0" cellpadding="5">
                <%
                for (Product product : products) {
                %>
                <tr>
                    <td width="25%" align="center">
                        <img src="<%=request.getContextPath()%>/img/products/<%=product.getName()%>.png"
                             alt="<%=product.getName()%>"
                             width="100">
                    </td>

                    <td width="25%" align="center">
                        <b><%=product.getName()%></b>
                        <br>
                        <%=product.getDescription()%>
                    </td>

                    <td width="25%" align="center">
                        <%=product.getPrice()%> €
                    </td>

                    <td width="25%" align="center">
                        <form action="#" method="post">
                            <input type="submit" value="add to cart">
                        </form>
                    </td>
                </tr>
                <%
                }
                %>
            </table>
            <%
            } else {
            %>
                No products found for this category.
            <%
            }
            %>
        </td>
    </tr>
    
</table>