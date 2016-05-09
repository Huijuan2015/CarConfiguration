/**   
* @Title: PriceServlet.java 
* @Description: Define class PriceServlet
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/28/2016  
*/
package unit5;


import client.Client.DefaultSocketClient;
import Model.Automobile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
@WebServlet(description = "PriceServlet", urlPatterns = { "/getPrice"})
public class PriceServlet extends HttpServlet{

    public static DefaultSocketClient client;

    public void init(ServletConfig config){
        client = new DefaultSocketClient("localhost", 6543);
        System.out.println("client established");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LinkedHashMap<String, Automobile> modelList = new LinkedHashMap<String, Automobile>(client.getAllModels());
        response.setContentType("text/html");
        String modelName = request.getParameter("modelName");

        for(Automobile auto : modelList.values()) {
            if (auto.getModel().equals(modelName)) {
                for (int i = 0; i < auto.getOptionSet().size(); i++) {
                    String optionset = auto.getOptionSet().get(i).getName();
                    String option = request.getParameter(optionset);
                    auto.setOptionChoice(optionset, option);
                }
            }
        }

        for(Automobile auto : modelList.values()) {
            if (auto.getModel().equals(modelName)) {
                request.setAttribute("row10", modelName);
                request.setAttribute("row20", "Basic Price");
                request.setAttribute("row30", auto.getBaseprice());
                int i = 0;
                for (i = 0; i < auto.getOptionSet().size(); i++) {
                    String optionsetName = auto.getOptionSet().get(i).getName();
                    request.setAttribute(new String("row1" + i), optionsetName);
                    request.setAttribute(new String("row2" + i), auto.getOptionChoice(optionsetName));
                    request.setAttribute(new String("row3" + i), Float.toString(auto.getOptionChoicePrice(optionsetName)));
                    System.out.println(optionsetName);
                    System.out.println(auto.getOptionChoice(optionsetName));
                    System.out.println(Float.toString(auto.getOptionChoicePrice(optionsetName)));
                }
                request.setAttribute(new String("row1" + i), "Total Cost");
                request.setAttribute(new String("row2" + i), " ");
                request.setAttribute(new String("row3" + i), auto.getTotalPrice());
            }
        }

        String target = "ChoicePrice.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
        client.closeSession();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
