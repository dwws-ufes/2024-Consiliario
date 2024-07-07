package br.inf.ufes.consiliario.config

import org.apache.jena.query.QueryExecutionFactory
import org.apache.jena.query.ResultSet
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.springframework.stereotype.Service

@Service
class ApacheJenaConfig(
    private val rdfModel: Model = ModelFactory.createDefaultModel(),
    private val wikidataEndpoint: String = "https://query.wikidata.org/sparql"
) {
    fun runQuery(query: String): ResultSet {
        val queryExecution = QueryExecutionFactory.sparqlService(wikidataEndpoint, query)
        return queryExecution.execSelect()
    }
}