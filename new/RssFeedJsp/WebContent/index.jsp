<%@page import="org.json.JSONArray"%>
<%@page import="io.zilker.application.Feed"%>
<%@page import="io.zilker.application.Feed"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Rss News Feed</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" type="text/css" href="rssfeed.css">
    </head>
        <%
    Feed feed=new Feed("https://news.google.com/news/rss/?ned=in&gl=IN&hl=en-IN");
        JSONArray array=feed.fetchFeed();
   %>
    <body>
        <div class="menu-button" >
            <div class="menu-button-part-1"></div>
            <div class="menu-button-part-1"></div>
            <div class="menu-button-part-1"></div>
        </div>
        <nav id="navigation">
            <h2><em>Other feeds..</em></h2>
                <ul id="nav-button">
                    <li><div class="nav-img-container"><img src="tech.jpg"></div><button id="news?id=tech">Technology</button></li>
                    <li><div class="nav-img-container"><img src="sports.jpg"></div><button id="news?id=sports">Sports</button></li>
                    <li><div class="nav-img-container"><img src="politics.jpg"></div><button id="news?id=politics">Politics</button></li>
                    <li><div class="nav-img-container"><img src="cinema.jpg"></div><button id="news?id=cinema">Cinema</button></li>
                </ul>
        </nav>
        <header>
            <h1>Top Stories</h1>
        </header>
        <section class="top-stories">
            <ul class="listItems">
            <%for(int i=0;i<=12;i++) { 
            
            	if(feed.fetchImage(i)=="false") {
            		continue;
            	}
            %>
            
            <!--      <li><div class="top-stories-img-container"><img src="<%=feed.fetchImage(i)%>">
                    </div><a href="<%=feed.fetchLink(i)%>"><%=feed.fetchTitle(i) %></a></li>  -->
         	        <%=feed.fetchTitle(i) %>
         	        <br><br><br>
                    <%} %>
                    
            </ul>
        </section>
        <script src="rssfeed.js"></script>
    </body>


</html>    