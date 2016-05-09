/**   
* @Title: ModelServlet.java 
* @Description: Define class ModelServlet
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/28/2016  
*/

package unit5;

import client.Adapter.BuildClient;
import client.Client.DefaultSocketClient;
import Model.Automobile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
@WebServlet(description = "ModelServlet", urlPatterns = { "/getModels" })

public class ModelServlet extends HttpServlet{

    private static DefaultSocketClient client ;

    public void init(ServletConfig config0){
        client=new BuildClient("localhost",6543);
        System.out.println("client start");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get all models
        LinkedHashMap<String,Automobile> modelList = new LinkedHashMap<String, Automobile>(client.getAllModels());

        //front-end page : present models to choose
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(
                "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Models</title></head>"
                        + "<h1 align=\"center\">Select Model</h1>" + "<h3 align=\"center\">Please select the model you want and click \"submit\"</h3>"
                        + "<body>	<form action=\"getOptions\" method=\"get\" align=\"center\">"
                        + "</select> <select id=\"modelSelect\" name=\"modelSelect\" align=\"center\">"
        );
        //model options
        for(Automobile auto :modelList.values()){
            out.println("<option>" + auto.getModel() + "</option>");
        }

        out.print("</select><br><br><input type=\"submit\" value=\"submit\"></form></body></html>");
        client.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
