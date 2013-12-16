

package cbrapp;

import java.util.Collection;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.connector.OntologyConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EqualsStringIgnoreCase;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeepBasic;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.util.FileIO;

/**
 * Класс приложения CBR.
 * @author M. Navrotskiy
 */
public class CbrApplication implements StandardCBRApplication{
    
    /* Поля класса. */
    /** Запрос в базу прецедентов. */
    private CBRQuery query;
    /** Результат работы CBR. */
    private Collection<RetrievalResult> eval;
    /** База прецедентов. */
    private CBRCaseBase caseBase;
    /** Набор найденных прецедентов. */
    private Collection<CBRCase> selectedCase;
    /** Связыватель с онтологией. */
    private OntologyConnector connector;
    /** Объект класса. */
    private static CbrApplication instance;
    /** Найденный по запросу прецедент. */
    private CBRCase result;
    
    /**
     * Метод получения результата CBR.
     * @return Результат CBR.
     */
    public CBRCase result() {
        return this.result;
    }
    
    /**
     * Конструктор по умолчанию.
     */
    public CbrApplication(){}

    /**
     * Метод подготовки приложения (конфигурирование).
     * @throws ExecutionException 
     */
    @Override
    public void configure() throws ExecutionException {
        
        this.connector = new OntologyConnector();
        
        this.connector.initFromXMLfile(FileIO.findFile("configurate.xml"));
        
        this.caseBase = new LinealCaseBase();
    }

    /**
     * Метод подготовки CBR цикла.
     * @return Прецеденты.
     * @throws ExecutionException 
     */
    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        
        this.caseBase.init(this.connector);
        
        Collection<CBRCase> cases = this.caseBase.getCases();
        
        for (CBRCase c: cases)
            System.out.println(c);
        
        return this.caseBase;
    }

    /**
     * Метод CBR цикла.
     * @param cbrq Запрос к решателю.
     * @throws ExecutionException 
     */
    @Override
    public void cycle(CBRQuery cbrq) throws ExecutionException {
        NNConfig config = getSimilarityConfig();
        
        config.setDescriptionSimFunction(new Average());
        
        query = cbrq;
        
        eval = NNScoringMethod.evaluateSimilarity(caseBase.getCases(), query, config);
        
        selectedCase = SelectCases.selectTopK(eval, 1);
        
        System.out.println("========================");
        for (CBRCase c: selectedCase) {
            System.out.println(c);
            System.out.println(((RetrievalResult)eval.toArray()[0]).getEval());
            result = c;
        }
    }

    /**
     * Метод выполняющийся после цикла.
     * @throws ExecutionException 
     */
    @Override
    public void postCycle() throws ExecutionException {}

    /**
     * Метод формирования весов и мер близости.
     * @return Список аттрибутов поиска с весами и мерами близости.
     */
    private static NNConfig getSimilarityConfig() {
        NNConfig result = new NNConfig();
        Attribute attribute;
        Double weight = new Double(1.0);
        
        // danger
        attribute = new Attribute("danger", CbrDescription.class);
        result.addMapping(attribute, new Equal());
        result.setWeight(attribute, weight);
        
        // count
        attribute = new Attribute("count", CbrDescription.class);
        result.addMapping(attribute, new Equal());
        result.setWeight(attribute, weight);
        
        // state
        attribute = new Attribute("state", CbrDescription.class);
        result.addMapping(attribute, new Equal());
        result.setWeight(attribute, weight);

        // time
        attribute = new Attribute("time", CbrDescription.class);
        result.addMapping(attribute, new Equal());
        result.setWeight(attribute, weight);
        
        // type
        attribute = new Attribute("types", CbrDescription.class);
        result.addMapping(attribute, new Equal());
        result.setWeight(attribute, weight);
        
        return result;
    }
    
}
