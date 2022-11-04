package com.example.webserver.Controllers;

import com.example.webserver.Tools.JenaEngine;
import org.apache.jena.rdf.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping(path = "/a")
    public String getAllArgumentatif() {
        Model model = JenaEngine.readModel("data/Literature.owl");

        if (model != null) {
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
            return JenaEngine.executeQueryFile(inferedModel, "data/a.txt");
        } else {
            return "Error when reading model from ontology" ;
        }
    }

    @GetMapping(path = "/p")
    public String getAllp() {
        Model model = JenaEngine.readModel("data/Literature.owl");

        if (model != null) {
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
            return JenaEngine.executeQueryFile(inferedModel, "data/p.txt");
        } else {
            return "Error when reading model from ontology" ;
        }
    }


    @GetMapping(path = "/r")
    public String getAllr() {
        Model model = JenaEngine.readModel("data/Literature.owl");

        if (model != null) {
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
            return JenaEngine.executeQueryFile(inferedModel, "data/r.txt");
        } else {
            return "Error when reading model from ontology";
        }
    }


    @GetMapping(path = "/t")
    public String getAllt() {
        Model model = JenaEngine.readModel("data/Literature.owl");

        if (model != null) {
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
            return JenaEngine.executeQueryFile(inferedModel, "data/t.txt");
        } else {
            return "Error when reading model from ontology";
        }
    }

}
