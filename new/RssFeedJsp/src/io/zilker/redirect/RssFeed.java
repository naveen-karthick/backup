package io.zilker.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.application.Feed;

/**
 * Servlet implementation class RssFeed
 */
@WebServlet("/news")
public class RssFeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RssFeed() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter("id").equals("tech")) {
			Feed feed=new Feed("https://news.google.com/news/rss/headlines/section/topic/TECHNOLOGY.en_in/Technology?ned=in&hl=en-IN&gl=IN");
			response.getWriter().println(feed.fetchFeed().toString());
			
		}
		
		
		if(request.getParameter("id").equals("cinema")) {
			Feed feed=new Feed("https://news.google.com/news/rss/headlines/section/topic/ENTERTAINMENT.en_in/Entertainment?ned=in&hl=en-IN&gl=IN");
			response.getWriter().println(feed.fetchFeed().toString());
		}
			
		
		if(request.getParameter("id").equals("politics")) {
			Feed feed=new Feed("https://news.google.com/news/rss/headlines/section/topic/WORLD.en_in/World?ned=in&hl=en-IN&gl=IN");
			response.getWriter().println(feed.fetchFeed().toString());
		}
		
		if(request.getParameter("id").equals("sports")) {
			Feed feed=new Feed("https://news.google.com/news/rss/headlines/section/topic/SPORTS.en_in/Sport?ned=in&hl=en-IN&gl=IN");
			response.getWriter().println(feed.fetchFeed().toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
