
package cbrapp;

import com.google.gson.Gson;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Class which store result of CBR.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class CbrSolution implements CaseComponent {

    /* Class fields. */
    /** Main concept. */
    private Instance mainConcept;
    /** Value of result. */
    private Instance result;

    /**
     * Convert class object to JSON string..
     * @return JSON string.
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Set new value of result.
     * @param result_has_value New value.
     */
    public void setResult (Instance result_has_value) {
        this.result = result_has_value;
    }

    /**
     *Get value of result.
     * @return Value of result.
     */
    public Instance getResult() {
        return result;
    }
    
    /**
     * Get value of main concept.
     * @return Main concept.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }
    
    /**
     * Set value of main concept.
     * @param mainConcept main concept.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }
    
    /**
     * Get attributes id.
     * @return Attributes id.
     */
    @Override
    public Attribute getIdAttribute() {
        return new Attribute("mainConcept", this.getClass());
    }
    
}
