package com.example.webserver.Tools;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class JenaEngine {
    static public Model readModel(String inputDataFile) {
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputDataFile);
        if (in == null) {
            System.out.println("Ontology file: " + inputDataFile + " not found");
            return null;
        }
        model.read(in, "");
        try {
            in.close();
        } catch (IOException e) {
            return null;
        }
        return model;
    }

    static public Model readInferencedModelFromRuleFile(Model model, String inputRuleFile) {
        InputStream in = FileManager.get().open(inputRuleFile);
        if (in == null) {
            System.out.println("Rule File: " + inputRuleFile + " not found");
            return null;
        } else {
            try {
                in.close();
            } catch (IOException e) {
                return null;
            }
        }
        List rules = Rule.rulesFromURL(inputRuleFile);
        GenericRuleReasoner reasoner = new GenericRuleReasoner(rules);
        reasoner.setDerivationLogging(true);
        reasoner.setOWLTranslation(true); // not needed in RDFS case
        reasoner.setTransitiveClosureCaching(true);
        InfModel inf = ModelFactory.createInfModel(reasoner, model);
        return inf;
    }

    static public String executeQuery(Model model, String queryString) {
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        OutputStream output = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            public String toString() {
                return this.string.toString();
            }
        };
        ResultSetFormatter.out(output, results, query);
        return output.toString();
    }

    public static String executeQueryFile(Model model, String filepath) {
        File queryFile = new File(filepath);
        InputStream in = FileManager.get().open(filepath);
        if (in == null) {
            System.out.println("Query file: " + filepath + " not found");
            return null;
        } else {
            try {
                in.close();
            } catch (IOException e) {
                return null;
            }
        }
        String queryString = FileTool.getContents(queryFile);
        return executeQuery(model, queryString);
    }
}
