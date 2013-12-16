
package cbrapp;

import com.google.gson.Gson;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс результата CBR цикла.
 * @author M. Navrotskiy
 */
public class CbrSolution implements CaseComponent {

    /* Поля класса. */
    /** Концепт. */
    private Instance mainConcept;
    /** Значение результата. */
    private Instance result;

    /**
     * Метод преобразования в строку.
     * @return Значение всех полей класса в виде строки.
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Метод изменения значения результата.
     * @param result_has_value Значение результата.
     */
    public void setResult (Instance result_has_value) {
        this.result = result_has_value;
    }

    /**
     * Метод получения значения результата.
     * @return Значение результата.
     */
    public Instance getResult() {
        return result;
    }
    
    /**
     * Метод получения главного концепта.
     * @return Концепт.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }
    
    /**
     * Метод задания главного концепта.
     * @param mainConcept Концепт.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }
    
    /**
     * Метод получения идентификатора атрибута.
     * @return Значение идентификатора атрибута.
     */
    @Override
    public Attribute getIdAttribute() {
        return new Attribute("mainConcept", this.getClass());
    }
    
}
