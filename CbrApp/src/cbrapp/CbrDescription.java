
package cbrapp;

import com.google.gson.Gson;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс описания прецедента.
 * @author M. Navrotskiy
 */
public class CbrDescription implements CaseComponent {
    
    /* Поля класса. */
    /** Класс опасности. */
    private Instance danger;
    /** Количество отходов. */
    private Instance count;
    /** Агрегатное состояние. */
    private Instance state;
    /** Время полного разложения. */
    private Instance time;
    /** Тип отходов. */
    private Instance types;
    /** Главный консепт. */
    private Instance mainConcept;

    /**
     * Метод изменения значения типа отходов.
     * @param types Значение типа отходов.
     */
    public void setTypes(Instance types) {
        this.types = types;
    }

    /**
     * Метод изменения значения полного времени разложения.
     * @param time Значение полного времени разложения.
     */
    public void setTime(Instance time) {
        this.time = time;
    }

    /**
     * Метод изменения значения агрегатного состояния.
     * @param state Значение агрегатного состояния.
     */
    public void setState(Instance state) {
        this.state = state;
    }

    /**
     * Метод изменения значения класса опасности.
     * @param danger Значение класса опасности.
     */
    public void setDanger(Instance danger) {
        this.danger = danger;
    }

    /**
     * Метод изменения значения количества отходов.
     * @param count Значение количества отходов.
     */
    public void setCount(Instance count) {
        this.count = count;
    }

    /**
     * Метод получения значения типа отходов.
     * @return Значение типа отходов.
     */
    public Instance getTypes() {
        return types;
    }

    /**
     * Метод получения значения времени разложения.
     * @return Значение времени разложения.
     */
    public Instance getTime() {
        return time;
    }

    /**
     * Метод получения значения агрегатного состояния.
     * @return Значение агрегатного состояния.
     */
    public Instance getState() {
        return state;
    }

    /**
     * Метод получения значения класса опасности.
     * @return Значение класса опасности.
     */
    public Instance getDanger() {
        return danger;
    }

    /**
     * Метод получения количества отходов.
     * @return Значение количества отходов.
     */
    public Instance getCount() {
        return count;
    }

    /**
     * Метод изменения главного концепта.
     * @param mainConcept Новый главный концепт.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }

    /**
     * Метод получения значения главного концепта.
     * @return Значение главного концепта.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }

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
     * Метод получения идентификатора атрибута.
     * @return Идентификатор атрибута.
     */
    @Override
    public Attribute getIdAttribute() {
        return new Attribute("mainConcept", this.getClass());
    }
    
}
