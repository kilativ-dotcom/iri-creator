package model;

import java.util.Arrays;
import java.util.List;

public enum IriTemplate {
    PredicateTemplate(
            "concept_%s\n" +
                    "<- sc_node_class;\n" +
                    "=> nrel_iri: [%s];\n" +
                    "<- concept_cim_parameter;;", false),
    CimTypeTemplate(
            "concept_%s\n" +
                    "<- sc_node_class;\n" +
                    "=> nrel_iri: [%s];\n" +
                    "<- concept_cim_type;;", false),
    LogicCimTypeTemplate(
            "concept_%s\n" +
                    "<- sc_node_class;\n" +
                    "=> nrel_iri: [%s];\n" +
                    "<- concept_logic_cim_type;\n" +
                    "<- concept_cim_type;;", false),
    BinaryRelationTemplate(
            "nrel_%s\n" +
                    "<- sc_node_norole_relation;\n" +
                    "=> nrel_iri: [%s];\n" +
                    "<- binary_relation;\n" +
                    "<- concept_cim_relation;;", true),
    BinaryRelationIntoLiteralTemplate(
            "nrel_%s\n" +
                    "<- sc_node_norole_relation;\n" +
                    "=> nrel_iri: [%s];\n" +
                    "<- binary_relation;\n" +
                    "=> nrel_second_domain: concept_cim_literal;\n" +
                    "<- concept_cim_relation;;", false),
    QuasyBinaryRelationTemplate(
            "nrel_%s\n" +
                    "<- sc_node_norole_relation;\n" +
                    "=> nrel_iri: [%s];\n" +
                    "<- quasybinary_relation;\n" +
                    "<- concept_cim_relation;;", true);

    private final String template;
    private final boolean canBeReversed;
    private static final int LINE_WITH_IRI = 2;
    private static final String REVERSED_IRI_TEMPLATE = "=> nrel_iri: nrel_reversed_iri: [%s];\n";

    IriTemplate(String template, boolean canBeReversed) {
        this.template = template;
        this.canBeReversed = canBeReversed;
    }

    public String createIri(Iri iri, boolean isReversedInAnswer) {
        String systemId = iri.getSystemId();
        String plainIri = iri.getIri();
        StringBuilder tempAnswer = new StringBuilder(String.format(template, systemId, plainIri));
        if (canBeReversed && isReversedInAnswer) {
            List<String> lines = Arrays.asList(tempAnswer.toString().split("\n"));
            tempAnswer = new StringBuilder();
            lines.stream().limit(LINE_WITH_IRI + 1).map(s -> s + "\n").forEach(tempAnswer::append);
            tempAnswer.append(String.format(REVERSED_IRI_TEMPLATE, iri.getReversedIri()));
            lines.stream().skip(LINE_WITH_IRI + 1).map(s -> s + "\n").forEach(tempAnswer::append);
        }
        return tempAnswer.toString();
    }
}
