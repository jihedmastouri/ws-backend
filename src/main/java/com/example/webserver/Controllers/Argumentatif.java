package com.example.webserver.Controllers;

import com.example.webserver.Tools.JenaEngine;
import org.apache.jena.rdf.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Argumentatif")
public class Argumentatif {

    @GetMapping
    public String getAllArgumentatif() {
        Model model = JenaEngine.readModel("data/Literature.owl");

        if (model != null) {
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
            return JenaEngine.executeQueryFile(inferedModel, "data/query.txt");
        } else {
            return "Error when reading model from ontology" ;
        }
    }
}
