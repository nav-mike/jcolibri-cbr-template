package cbrapp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.datatypes.Instance;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.OntologyAccessException;

/**
 * The main class of app.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class CbrApp {

    /**
     * The main function of app.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String cbrResult = cbr();
        writeResult(cbrResult);
    }
    
    /**
     * Write CBR result to file.
     * @param result Result of CBR.
     */
    private static void writeResult (String result) {
        try {
            PrintWriter out = new PrintWriter("result.txt");
            out.println(result);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CbrApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Funciton which exec CBR..
     * @return Result of CBR.
     */
    private static String cbr () {
        try {
            String result = "";
            CbrApplication app = new CbrApplication();
            app.configure();
            app.preCycle();
            
            CbrDescription description = new CbrDescription();
            description.setCount(new Instance("tt_1")); // Request
            description.setDanger(new Instance("I_class"));
            description.setState(new Instance("as_1"));
            description.setTime(new Instance("fdt_1"));
            description.setTypes(new Instance("Журналы"));
            
            CBRQuery query = new CBRQuery();
            query.setDescription(description);
            app.cycle(query);
            CBRCase c = app.result();
            CbrSolution cd = (CbrSolution) c.getSolution();
            return result = cd.toString();
            
        } catch (ExecutionException | OntologyAccessException ex) {
            Logger.getLogger(CbrApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
