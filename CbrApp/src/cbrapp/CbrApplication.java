

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
 * CBR-app class.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class CbrApplication implements StandardCBRApplication{
    
    /* Class fields. */
    /** CBR-query. */
    private CBRQuery query;
    /** CBR-result. */
    private Collection<RetrievalResult> eval;
    /** Case base. */
    private CBRCaseBase caseBase;
    /** Selected cases */
    private Collection<CBRCase> selectedCase;
    /** Owl connector. */
    private OntologyConnector connector;
    /** Object of current class. */
    private static CbrApplication instance;
    /** CBR-result (single value, not collection). */
    private CBRCase result;
    
    /**
     * Get CBR-result.
     * @return Value of CBR result.
     */
    public CBRCase result() {
        return this.result;
    }
    
    /**
     * Default constructor.
     */
    public CbrApplication(){}

    /**
     * Configuration.
     */
    @Override
    public void configure() throws ExecutionException {
        
        this.connector = new OntologyConnector();
        
        this.connector.initFromXMLfile(FileIO.findFile("configurate.xml"));
        
        this.caseBase = new LinealCaseBase();
    }

    /**
     * Pre cycle actions
     * @return Case base.
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
     * CBR cycle.
     * @param cbrq CBR query.
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
     * Post cycle actions
     */
    @Override
    public void postCycle() throws ExecutionException {}

    /**
     * Create similarity config.
     * @return Similarity config.
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
