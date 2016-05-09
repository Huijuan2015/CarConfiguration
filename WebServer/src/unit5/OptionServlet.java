/**   
* @Title: OptionServlet.java 
* @Description: Define class OptionServlet
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/28/2016  
*/

package unit5;


import client.Adapter.BuildClient;
import client.Client.DefaultSocketClient;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import Model.Automobile;
import java.util.LinkedHashMap;
@WebServlet(description = "OptionServlet", urlPatterns = { "/getOptions" })
public class OptionServlet extends HttpServlet{


    public static DefaultSocketClient client;

    public void init(ServletConfig config) {
        client = new BuildClient("localhost", 6543);
        System.out.println("new client established");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String modelName = request.getParameter("modelSelect");
        System.out.println(modelName);
        LinkedHashMap<String, Automobile> modelList = new LinkedHashMap<String, Automobile>(client.getAllModels());
        out.println(
                "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Model Infomation</title></head>"
                        + "<h1 align=\"center\">" + modelName + "</h1>" + "<h3 align=\"center\">Please select the configuration</h3>"
                        + "<body>	<form action=\"getPrice\" align=\"center\">");


       // Automobile auto = client.getModelByName(modelName);
        for (Automobile auto : modelList.values()) {
            if(auto.getModel().equals(modelName)){

                for (int j = 0; j < auto.getOptionSet().size(); j++) {
                    out.print("<textparea ><b>" + auto.getOptionSet().get(j).getName() + "</b></textarea>");
                    out.print("<select id=\"" + auto.getOptionSet().get(j).getName() + "\" name=\""
                            + auto.getOptionSet().get(j).getName() + "\">");
                    for (int k = 0; k < auto.getOptionSet().get(j).getOpt().size(); k++) {
                        out.println("<option>" + auto.getOptionSet().get(j).getOpt().get(k).getName()
                                + "</option>");
                    }
                    out.print("</select><textparea><br><br></textparea>");

                }
                out.print("<textparea><br></textparea><input type=\"submit\" value=\"done\"></form></body></html>");
                client.closeSession();
            }
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
