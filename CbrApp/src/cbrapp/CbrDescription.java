
package cbrapp;

import com.google.gson.Gson;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Class which description case.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class CbrDescription implements CaseComponent {
    
    /* Class fields. */
    /** Hazard class. */
    private Instance danger;
    /** Amount of waste. */
    private Instance count;
    /** Aggregare state. */
    private Instance state;
    /** Time of full recycling (depricated). */
    private Instance time;
    /** Type of waste. */
    private Instance types;
    /** Main concept. */
    private Instance mainConcept;

    /**
     * Set type of waste.
     * @param types Value of type.
     */
    public void setTypes(Instance types) {
        this.types = types;
    }

    /**
     * Set time of full recycling.
     * @param time Value of time.
     */
    public void setTime(Instance time) {
        this.time = time;
    }

    /**
     * Set aggregate state.
     * @param state Value of state.
     */
    public void setState(Instance state) {
        this.state = state;
    }

    /**
     * Set hazard class.
     * @param danger Value of class.
     */
    public void setDanger(Instance danger) {
        this.danger = danger;
    }

    /**
     * Set amount of waste.
     * @param count Value of amount.
     */
    public void setCount(Instance count) {
        this.count = count;
    }

    /**
     * Get value of type of waste.
     * @return Type of waste.
     */
    public Instance getTypes() {
        return types;
    }

    /**
     * Get value of time of full recycling.
     * @return Time of full recycling.
     */
    public Instance getTime() {
        return time;
    }

    /**
     * Get value of aggregate state.
     * @return Значение агрегатного состояния.
     */
    public Instance getState() {
        return state;
    }

    /**
     * Get value of hazard class.
     * @return Hazard class.
     */
    public Instance getDanger() {
        return danger;
    }

    /**
     * Get value of amount of waste.
     * @return Amount of waste.
     */
    public Instance getCount() {
        return count;
    }

    /**
     * Set main concept value.
     * @param mainConcept value of main concept.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }

    /**
     * Get value of main concept.
     * @return main concept value.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }

    /**
     * To string method.
     * @return Class object to string.
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("mainConcept", this.getClass());
    }
    
}
